package ng.edu.nileuniversity.tams;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Pos;

/**
 * Main Application class for Time and Attendance Management System
 * Nile University of Nigeria
 * 
 * This is the entry point for the JavaFX application.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a simple welcome label
        Label welcomeLabel = new Label("Welcome to TAMS");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Create a layout container
        StackPane root = new StackPane();
        root.getChildren().add(welcomeLabel);
        StackPane.setAlignment(welcomeLabel, Pos.CENTER);
        
        // Create the scene
        Scene scene = new Scene(root, 800, 600);
        
        // Configure the primary stage (window)
        primaryStage.setTitle("Time & Attendance Management System – Nile University of Nigeria");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main method to launch the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
