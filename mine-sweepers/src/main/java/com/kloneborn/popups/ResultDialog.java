package com.kloneborn.popups;

import com.kloneborn.AppController;
import com.kloneborn.Difficulty;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;

public class ResultDialog {

    public static void showResultDialog(boolean playerWon, AppController controller) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(playerWon ? "Congratulations!" : "Game Over");
        alert.setHeaderText(playerWon ? "You won Minesweeper!" : "You lost the game.");

        // Create custom buttons for Easy, Medium, and Hard
        ButtonType easyButton = new ButtonType("Easy");
        ButtonType mediumButton = new ButtonType("Medium");
        ButtonType hardButton = new ButtonType("Hard");

        alert.getButtonTypes().setAll(easyButton, mediumButton, hardButton);

        // Set actions for each button
        alert.setOnCloseRequest(event -> {
            ButtonType result = alert.getResult();
            if (result == easyButton) {
                controller.setDifficulty(Difficulty.EASY);
            } else if (result == mediumButton) {
                controller.setDifficulty(Difficulty.MEDIUM);
            } else if (result == hardButton) {
                controller.setDifficulty(Difficulty.HARD);
            }
            controller.generate();
        });

        // Customize the dialog pane layout if needed
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getButtonTypes().forEach(buttonType -> {
            Button button = (Button) dialogPane.lookupButton(buttonType);
            button.setMaxWidth(Double.MAX_VALUE);
        });

        alert.showAndWait();
    }

}
