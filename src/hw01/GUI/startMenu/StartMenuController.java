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

import hw01.GUI.onePlayerGame.OnePlayerGameController;
import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.GUI.onePlayerGame.OnePlayerGameView;
import hw01.GUI.settings.SettingsController;
import hw01.GUI.settings.SettingsView;
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
            OnePlayerGameModel onePlayerGameModel = new OnePlayerGameModel();
            OnePlayerGameView onePlayerGameView = new OnePlayerGameView(startMenuView.getRoot().getWidth(), startMenuView.getRoot().getHeight(), onePlayerGameModel);
            OnePlayerGameController onePlayerGameController = new OnePlayerGameController(stage, stage.getScene(), onePlayerGameView, onePlayerGameModel);

            primaryStage.setScene(new Scene(onePlayerGameView.getRoot()));
        });


        this.startMenuView.getConfigBtn().setOnAction(event -> {
            SettingsView settingsView = new SettingsView(startMenuView.getRoot().getWidth(), startMenuView.getRoot().getHeight());
            SettingsController settingsController = new SettingsController(stage, stage.getScene(), settingsView);

            primaryStage.setScene(new Scene(settingsView.getRoot()));

        });

    }

}
