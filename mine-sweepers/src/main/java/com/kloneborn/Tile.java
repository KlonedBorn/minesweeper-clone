package com.kloneborn;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tile extends Button {

    private int gridX;
    private int gridY;
    private boolean clicked = false, flagged = false, mined = false;

    public Tile(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.setPrefSize(AppController.TILE_SIZE_PX, AppController.TILE_SIZE_PX);
        this.getStyleClass().add("tile");
    }

    private void setFlaggedIcon() {
        if (!flagged) {
            // If already flagged, toggle off
            ImageView iv = new ImageView();
            iv.setMouseTransparent(true);
            setGraphic(iv); // Clear the graphic
            getStyleClass().remove("flagged");
        } else {
            // If not flagged, toggle on
            ImageView vie = new ImageView(
                    new Image(getClass().getResource("/com/kloneborn/icon/red-flag.png").toExternalForm(), 16, 16, true,
                            true));
            vie.setMouseTransparent(true);
            setGraphic(vie);
            getStyleClass().add("flagged");
        }
    }

    private void setMineIcon() {
        if (!mined) {
            // If already mine, toggle off
            ImageView iv = new ImageView();
            iv.setMouseTransparent(true);
            setGraphic(iv); // Clear the graphic
            getStyleClass().remove("mine");
        } else {
            // If not mine, toggle on
            ImageView iv = new ImageView(
                    new Image(getClass().getResource("/com/kloneborn/icon/bomb.png").toExternalForm(), 16, 16, true,
                            true));
            iv.setMouseTransparent(true);
            setGraphic(iv);
            getStyleClass().add("mine");
        }
    }

    public int getGridX() {
        return gridX;
    }

    public void setGridX(int gridX) {
        this.gridX = gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public void setGridY(int gridY) {
        this.gridY = gridY;
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
        setFlaggedIcon();
    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
        setMineIcon();
    }
}