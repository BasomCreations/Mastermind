package hw01.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class mastermindGameMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Mastermind");
        primaryStage.getIcons().add(new Image("file:images/icon1.png"));



        StackPane layout2 = new StackPane();
        Scene scene2 = new Scene(layout2,400,300);
        Label lb1 = new Label("Welcome ");
        layout2.getChildren().add(lb1);


        Button btn1 = new Button("Scene1");
        btn1.setOnAction(event -> primaryStage.setScene(scene2));
        root.getChildren().add(btn1);

        

        // Display the scene
        primaryStage.show();
    }
}
