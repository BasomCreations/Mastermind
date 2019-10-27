package hw01.GUI;

import hw01.GUI.startMenu.StartMenuController;
import hw01.GUI.startMenu.StartMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MastermindGameMain extends Application {


    private StartMenuView startView;
    private StartMenuController startMenuController;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
        startView = new StartMenuView();
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        startMenuController = new StartMenuController(startView, primaryStage);

        //Set title and image icon
        primaryStage.setTitle("Mastermind");
        primaryStage.getIcons().add(new Image("file:images/icon1.png"));

        // Set min dimensions
        primaryStage.setMinHeight(StartMenuView.HEIGHT);
        primaryStage.setMinWidth(StartMenuView.WIDTH);

        //Start Scene
        Scene scene = new Scene(startView.getRoot());
        primaryStage.setScene(scene);




        //Display scene
        primaryStage.show();
    }
}
