package hw01.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class MastermindGameMain extends Application {


    private StartMenuView startView;
    private StartMenuController startMenuController;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed. An application may
     * override this method to perform initialization prior to the actual starting
     * of the application.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * <p>
     * NOTE: This method is not called on the JavaFX Application Thread. An
     * application must not construct a Scene or a Stage in this
     * method.
     * An application may construct other JavaFX objects in this method.
     * </p>
     *
     * @throws Exception if something goes wrong
     */
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


        //Start Scene
        Scene startScene = new Scene(startView.getRoot());
        primaryStage.setScene(startScene);




        // Display the scene
        primaryStage.setMinHeight(StartMenuView.HEIGHT);
        primaryStage.setMinWidth(StartMenuView.WIDTH);
        primaryStage.show();
    }
}
