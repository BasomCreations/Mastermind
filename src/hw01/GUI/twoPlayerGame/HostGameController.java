/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 10/30/2019
 * Time: 3:26 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: HostGameController
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import hw01.GUI.onePlayerGame.OnePlayerGameController;
import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.GUI.onePlayerGame.OnePlayerGameView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HostGameController extends OnePlayerGameController {
    public HostGameController(Stage primaryStage, Scene prevScene, HostGameView view, HostGameModel model) {
        super(primaryStage, prevScene, view, model);
    }
}
