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


import hw01.GUI.sceneTemplate.SceneViewTemplate;
import hw01.GUI.sceneTemplate.SceneViewTemplateController;
import hw01.game.MasterMindBoard;
import hw01.game.MasterMindBoardException;
import hw01.game.Row;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class OnePlayerGameController extends SceneViewTemplateController {

    private int curRow;
    private OnePlayerGameModel model;


    public OnePlayerGameController(Stage primaryStage, Scene prevScene, OnePlayerGameView view, OnePlayerGameModel model) {
        super(primaryStage, prevScene, view);
        this.model = model;

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

        // Initialize Restart Button
        getTheView().getResetBtn().setOnAction(event -> {
            clearBoard();
        });

        // Initialize guess button clicks
        Button[] buttons = view.getButtons().clone();
        for (int i = 0; i < buttons.length; i++) {


            int finalI = i;
            buttons[i].setOnAction(event -> {
                // Get guesses
                int[] guesses = new int[MasterMindBoard.ROW_SIZE];
                PegSphere[] rowOfPegs = view.getPegGrid()[curRow].clone();

                for (int j = 0; j < rowOfPegs.length; j++) {
                    int numericGuess = PegColor.getColorNumber(rowOfPegs[j].getColor());

                    //The method getColorNumber returns 0 if the color is not selected
                    if (numericGuess != 0) {
                        guesses[j] = numericGuess;
                    } else {
                        view.getErrrorMsg().setContentText("Invalid number of guesses! Guess must include " + MasterMindBoard.ROW_SIZE + " pegs.");
                        view.getErrrorMsg().show();
                        return;
                    }
                }

                // Make guess
                Row result = null;
                try {
                    result = this.model.guess(guesses);
                } catch (MasterMindBoardException e) {
                    return;
                }
                int count = 0;
                // Add correct pegs in the correct position as red result pegs
                for (int j = 0; j < result.getCorrectPegs(); j++) {
                    view.getResultsGrid()[curRow][count].setFill(Color.RED);
                    count++;
                }
                // Add correct pegs in the incorrect position as black result pegs
                for (int j = 0; j < result.getPegsIncorrectPosition(); j++) {
                    view.getResultsGrid()[curRow][count].setFill(Color.BLACK);
                    count++;
                }


                // Check win
                if (model.checkWin()) {
                    finishGame();
                    return;
                }

                // Make next button visible if not currently on last button
                if (finalI != buttons.length - 1) {
                    buttons[finalI + 1].setVisible(true);
                }
                buttons[finalI].setVisible(false);

                // Update current row
                curRow++;

            });

        }


    }

    public void finishGame() {
        Media winSoundMedia = new Media(new File("sound/fanfare_x.wav").toURI().toString());
        MediaPlayer winSoundMediaPlayer = new MediaPlayer(winSoundMedia);
        winSoundMediaPlayer.play();
        getTheView().getButtons()[curRow].setVisible(false);
        curRow = -1;
    }

    public void clearBoard() {
        //Clears peg spheres
        for (PegSphere[] row:
             getTheView().getPegGrid()) {
            for (PegSphere peg:
                 row) {
                peg.setColor(Color.WHITE);
            }
        }

        //Clears result pegs
        for (Circle[] resultRow:
             getTheView().getResultsGrid()) {
            for (Circle resultPeg: resultRow) {
                resultPeg.setFill(Color.web(OnePlayerGameView.BGCOLOR));
            }
        }

        try {
            getTheView().getButtons()[model.getCurrentTurn()].setVisible(false);
        } catch (IndexOutOfBoundsException e) {
            //If this is the last move this error will occur,
            //the button will be already hidden so there is nothing else to do
        }

        getTheView().getButtons()[0].setVisible(true);
        curRow = 0;
        this.model.createNewGame();

    }

    @Override
    public OnePlayerGameView getTheView() {
        return (OnePlayerGameView) super.getTheView();
    }
}
