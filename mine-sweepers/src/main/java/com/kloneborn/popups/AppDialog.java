package com.kloneborn.popups;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

public class AppDialog {

    public static void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Minesweeper App");

        // Create a Label to display about information
        Label aboutLabel = new Label(getAboutText());
        aboutLabel.setWrapText(true);

        // Create a ScrollPane to allow scrolling for long content
        ScrollPane scrollPane = new ScrollPane(aboutLabel);
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

    private static String getAboutText() {
        return "Version: 1.1\n"
                + "Author: Kloneborn\n"
                + "Inspired by Microsoft's Minesweeper from 1998\n"
                + "Java Version: 21 (Compiler 1.9)\n"
                + "JavaFX Version: 21";
    }
}
