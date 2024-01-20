package com.kloneborn;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.kloneborn.popups.AppDialog;
import com.kloneborn.popups.MinesweeperDialog;
import com.kloneborn.popups.ResultDialog;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class AppController extends Application {

    @FXML
    private CheckMenuItem revealMines;

    @FXML
    private GridPane tiles;

    private Tile[][] matrix;

    private int tilesLeft = 0;

    private List<Tile> mined = new ArrayList<>();

    private Difficulty difficulty = Difficulty.EASY;

    private final ObjectProperty<MineField> gameProperty = new SimpleObjectProperty<>();

    @FXML
    void onActionSetDifficulty(ActionEvent event) {
        MenuItem item = (MenuItem) event.getTarget();
        difficulty = Difficulty.valueOf((String) item.getUserData());
        generate();
    }

    @FXML
    void onActionLoadPreviousGameState(ActionEvent evt) {

    }

    @FXML
    void onActionRenderAbout(ActionEvent evt) {
        AppDialog.showAbout();
    }

    @FXML
    void onActionRenderGameGuide(ActionEvent evt) {
        MinesweeperDialog.showGameGuideDialog();
    }

    @FXML
    void onActionRestartGame(ActionEvent evt) {
        generate();
    }

    @FXML
    void onActionSaveGameState(ActionEvent evt) {

    }

    @FXML
    void initialize() {
        generate();
    }

    @FXML
    private void onTileMouseClickedEventHandler(MouseEvent evt) {
        Tile tile = (Tile) evt.getTarget();
        MineField field = gameProperty.get();

        switch (evt.getButton()) {
            case PRIMARY:
                if (!tile.isClicked() && !tile.isFlagged()) {
                    tile.setClicked(true);
                    if (tile.isFlagged())
                        return;
                    int res = field.poke(tile.getGridX(), tile.getGridY());
                    if (res == 0) {
                        tile.getStyleClass().add("blank");
                        for (Integer[] neighbors : MineField.NEIGHBORS) {
                            int xf = tile.getGridX() + neighbors[0];
                            int yf = tile.getGridY() + neighbors[1];
                            // Check if neighbor indices are within bounds
                            if (xf >= 0 && xf < difficulty.getColumns() && yf >= 0
                                    && yf < difficulty.getRows()) {
                                Tile neighbor = matrix[yf][xf];
                                if (!neighbor.isClicked())
                                    neighbor.fireEvent(autoPoke());
                            }
                        }
                        --tilesLeft;
                        return;
                    } else if (res < 9) {
                        tile.setText(Integer.toString(res));
                        tile.getStyleClass().add("revealed");
                        tile.getStyleClass().addAll("number" + res);
                        tile.setText(Integer.toString(res));
                        if (--tilesLeft <= 0) {
                            ResultDialog.showResultDialog(true, this);
                        }
                    } else if (res == 9) {
                        for (Tile t : mined) {
                            t.setMined(true);
                        }
                        ResultDialog.showResultDialog(false, this);
                    }
                }
                break;
            case SECONDARY:
                tile.setFlagged(!tile.isFlagged());
                break;
            default:
                break;
        }
    }

    public static MouseEvent autoPoke() {
        return new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, false, false, false, false,
                true, false, false, false, false, false, null);
    }

    public void generate() {
        int columns = difficulty.getColumns();
        int rows = difficulty.getRows();
        int mines = difficulty.getMines();
        tilesLeft = columns * rows - mines;
        mined.clear();
        this.matrix = new Tile[rows][columns];
        MineField game = MineField.create(columns, rows, mines);
        gameProperty.set(game);

        tiles.getChildren().clear();
        tiles.getRowConstraints().clear();
        tiles.getColumnConstraints().clear();

        // Add column constraints
        for (int x = 0; x < columns; x++) {
            tiles.getColumnConstraints().add(new ColumnConstraints(TILE_SIZE_PX));
        }

        // Add row constraints
        for (int y = 0; y < rows; y++) {
            tiles.getRowConstraints().add(new RowConstraints(TILE_SIZE_PX));
        }

        // Populate the GridPane with tiles
        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows; y++) {
                Tile tile = new Tile(x, y);
                tile.setOnMouseClicked(this::onTileMouseClickedEventHandler);
                tiles.getChildren().add(tile);
                matrix[y][x] = tile;
                if (game.getMatrix()[x + columns * y] == 9)
                    mined.add(tile);
                GridPane.setColumnIndex(tile, x);
                GridPane.setRowIndex(tile, y);
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL indexPath = AppController.class.getResource("/com/kloneborn/index.fxml");
        FXMLLoader loader = new FXMLLoader(indexPath);
        Scene rootScene = (Scene) loader.load();
        stage.setScene(rootScene);
        stage.sizeToScene();
        stage.setTitle("Mine Sweepers");
        stage.show();
    }

    public static void main(String args[]) {
        AppController.launch(args);
    }

    public static final Double TILE_SIZE_PX = 32.0;

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
