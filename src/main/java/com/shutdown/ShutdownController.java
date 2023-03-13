package com.shutdown;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.SpinnerValueFactory;

import java.io.IOException;

public class ShutdownController {
    @FXML
    private Button shutdownButton;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    public void initialize() {
        spinner.setEditable(true);
        toggleButton.setSelected(false);
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        toggleButton.setOnAction(event -> {
            boolean isSelected = toggleButton.isSelected();
            if (isSelected) {
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
            } else {
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
            }
        });
    }

    @FXML
    private void handleShutdown(ActionEvent event) {
        int time = spinner.getValue();
        if (toggleButton.isSelected()) {
            // Convert hours to seconds
            time *= 60 * 60;
        } else {
            // Convert minutes to seconds
            time *= 60;
        }

        String command;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            command = "shutdown -s -t " + time;
        } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            command = "shutdown -h +" + time / 60;
        } else {
            // Unsupported operating system
            System.out.println("Unsupported operating system.");
            return;
        }
        System.out.println("Command: " + command);
        try {
            String[] cmd = {"cmd", "/c", command};
            Process p = Runtime.getRuntime().exec(cmd);
            System.out.println("Process started.");
            p.waitFor();
            System.out.println("Process finished.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Shutdown button clicked.");
        System.exit(0);
    }

    @FXML
    private void handleToggle(ActionEvent event) {
        if (toggleButton.isSelected()) {
            // Set the Spinner value factory to use hours instead of minutes
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        } else {
            // Set the Spinner value factory to use minutes
            spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        }
    }
}
