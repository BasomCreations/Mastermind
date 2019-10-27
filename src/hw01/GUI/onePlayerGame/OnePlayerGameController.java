/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 4:26 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: OnePlayerGameController
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.onePlayerGame;


import hw01.GUI.sceneTemplate.SceneViewTemplateController;
import hw01.game.MasterMindBoard;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.Arrays;


public class OnePlayerGameController extends SceneViewTemplateController {

    private int curRow;

    public OnePlayerGameController(Stage primaryStage, Scene prevScene, OnePlayerGameView view, OnePlayerGameModel model) {
        super(primaryStage, prevScene, view);

        curRow = 0;

        //Sound effect when clicking peg
        Media sound = new Media(new File("sound/sound1.wav").toURI().toString());
        MediaPlayer clickSound = new MediaPlayer(sound);

        for (int x = 0; x < MasterMindBoard.ROW_SIZE; x++) {
            for (int y = 0; y < MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS; y++) {

                int finalY = y;
                Circle curCircle = view.getPegGrid()[y][x];
                curCircle.setOnMouseClicked(event -> {

                    if (curRow == finalY) {
                        clickSound.stop();
                        Arrays.asList(PegColor.values());
                        curCircle.setFill(PegColor.getNextColor(curCircle.getFill()));
                        clickSound.play();
                    }

                });

            }
        }


    }
}
