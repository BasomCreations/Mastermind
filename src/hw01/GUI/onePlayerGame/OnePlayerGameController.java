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
import hw01.game.MasterMindBoardException;
import hw01.game.Row;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


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
                PegSphere curPeg = view.getPegGrid()[y][x];
                curPeg.setOnMouseClicked(event -> {

                    if (curRow == finalY) {
                        clickSound.stop();
                        curPeg.setColor(PegColor.getNextColor(curPeg.getColor()));
                        clickSound.play();
                    }

                });

            }
        }

        // Initialize guess button clicks
        Button[] buttons = view.getButtons().clone();
        for (int i = 0; i < buttons.length; i++) {

            int finalI = i;
            int finalI1 = i;
            buttons[i].setOnAction(event -> {
                // Get guesses
                int[] guesses = new int[MasterMindBoard.ROW_SIZE];
                PegSphere[] rowOfPegs = new PegSphere[curRow];
                rowOfPegs = view.getPegGrid()[curRow].clone();

                for (int j = 0; j < rowOfPegs.length; j++) {
                    PegColor pegColor = rowOfPegs[j].getCurrentPegColorProperty();
                    guesses[j] = pegColor.ordinal() + 1;
                }

                // Make guess
                try {
                    Row result = model.guess(guesses);
                    int count = 0;
                    for (int j = 0; j < result.getCorrectPegs(); j++) {
                        view.getResultsGrid()[curRow][count].setFill(Color.RED);
                        count++;
                    }
                    for (int j = 0; j < result.getPegsIncorrectPosition(); j++) {
                        view.getResultsGrid()[curRow][count].setFill(Color.WHITE);
                        count++;
                    }
                    for (int j = 0; j < result.getIncorrectPegs(); j++) {
                        view.getResultsGrid()[curRow][count].setFill(Color.BLACK);
                    }

                } catch (MasterMindBoardException e) {
                    view.getErrrorMsg().setContentText("Invalid number of guesses!");
                    view.getErrrorMsg().show();
                }

                // Make next button visible if not currently on last button
                if (finalI != buttons.length - 1) {
                    buttons[finalI + 1].setVisible(true);
                }
                buttons[finalI].setVisible(false);
            });

        }


    }
}
