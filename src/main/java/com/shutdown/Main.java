package com.shutdown;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the main FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/shutdown/view/main.fxml"));
        Parent root = loader.load();

        // Set the controller for the main FXML file
        ShutdownController controller = loader.getController();

        // Create the scene and show the stage
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shutdown Timer");
        primaryStage.show();
    }

    public static void main(String[] args) {
        System.out.println("Starting application...");
        launch(args);
    }
}
