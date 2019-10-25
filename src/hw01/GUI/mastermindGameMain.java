package hw01.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class mastermindGameMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //Set up root node for our scene graph
        StackPane root = new StackPane();

        // Add the scene graph to the stage
        primaryStage.setScene(new Scene(root, 400, 300));

        // Set the title for the main window
        primaryStage.setTitle("Hello, JavaFX!");


        // Display the scene
        primaryStage.show();
    }
}
