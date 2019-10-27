/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 3:03 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: StartMenuController
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.startMenu;

import hw01.GUI.onePlayerGameView.OnePlayerGameController;
import hw01.GUI.onePlayerGameView.OnePlayerGameModel;
import hw01.GUI.onePlayerGameView.OnePlayerGameView;
import hw01.GUI.startMenu.StartMenuView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartMenuController {

    private StartMenuView startMenuView;
    private final Stage stage;

    public StartMenuController(StartMenuView startMenuView, Stage primaryStage) {
        this.startMenuView = startMenuView;
        stage = primaryStage;

        //Switch to one player view when button is pressed
        this.startMenuView.getSingleBtn().setOnAction(event -> {
            OnePlayerGameView onePlayerGameView = new OnePlayerGameView(startMenuView.getRoot().getWidth(), startMenuView.getRoot().getHeight(), new OnePlayerGameModel());
            OnePlayerGameController onePlayerGameController = new OnePlayerGameController(stage, stage.getScene(), onePlayerGameView);

            primaryStage.setScene(new Scene(onePlayerGameView.getRoot()));
        });
    }

}
