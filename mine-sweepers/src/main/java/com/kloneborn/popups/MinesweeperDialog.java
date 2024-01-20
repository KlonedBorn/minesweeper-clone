package com.kloneborn.popups;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class MinesweeperDialog {

    public static void showGameGuideDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Minesweeper Guide");
        alert.setHeaderText("How to Play Minesweeper");

        // Create a Label to display formatted text
        Label guideLabel = new Label(getMinesweeperGuideText());
        guideLabel.setWrapText(true);

        // Create a ScrollPane to allow scrolling for long content
        ScrollPane scrollPane = new ScrollPane(guideLabel);
        scrollPane.setFitToWidth(true);

        // Create a GridPane to layout the content
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(scrollPane, 0, 0);
        GridPane.setVgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(scrollPane, javafx.scene.layout.Priority.ALWAYS);

        // Set content of the Alert dialog
        alert.getDialogPane().setContent(gridPane);

        // Show the dialog
        alert.showAndWait();
    }

    private static String getMinesweeperGuideText() {
        // You can structure the guide using plain text with formatting
        return "Objective:\n"
                + "Clear the minefield without detonating any mines.\n\n"
                + "Rules:\n"
                + " - Left-click to reveal a tile.\n"
                + " - Right-click to flag a potential mine.\n"
                + " - Numbers indicate the number of adjacent mines.\n"
                + " - A mine detonation ends the game.\n\n"
                + "Difficulty Levels:\n"
                + "Easy, Medium, and Hard options are available.";
    }
}
