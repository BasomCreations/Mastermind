/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 10/30/2019
 * Time: 4:02 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: ClientGameController
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import hw01.GUI.onePlayerGame.OnePlayerGameController;
import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.GUI.onePlayerGame.OnePlayerGameView;
import hw01.game.GameResults;
import hw01.game.Score;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

public class ClientGameController extends OnePlayerGameController {
    ClientGameModel model;
    public ClientGameController(Stage primaryStage, Scene prevScene, ClientGameView view, ClientGameModel model) {
        super(primaryStage, prevScene, view, model);
        this.model = (ClientGameModel)super.getModel();
    }

    /**
     * Method that deals with when the game is finished and has to wait for host
     */
    @Override
    public void finishGame() {

        getTheView().getResultsLbl().setText("Waiting for other player...");
        getTheView().getResultsLbl().setVisible(true);

        Runnable pleaseDontFreezeGUI = () -> {

            boolean success = false;
            Score hostScore = null;
            try {
                model.getGameClient().sendObject(getModel().getResults());
                hostScore = (Score)model.getGameClient().readObject();
                success = true;
            } catch (Exception e) {
            }

            boolean successFinal = success;
            Score finalHostScore = hostScore;
            Platform.runLater(() ->{
                if(successFinal){
                    displayTwoPlayerResults(finalHostScore);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Other player disconnected");
                    alert.show();
                }

            });

        };
        Thread thread = new Thread(pleaseDontFreezeGUI);
        thread.start();

        getTheView().getButtons()[getCurRow()].setVisible(false);
        setCurRow(-1);

    }

    /**
     * Displays scores
     * @param otherScore the score of the other player
     */
    public void displayTwoPlayerResults(Score otherScore){

        List<Score> scoreList = twoPlayerGameUtilities.getOrderedListOfScores(getModel().getResults(), otherScore);

        getTheView().getResultsLbl().setText("1. "+scoreList.get(0).toString() + "\n2. " + scoreList.get(1).toString());

        //Plays sounds
        if(scoreList.get(0).equals(getModel().getResults())){
            Media winSoundMedia = new Media(new File("sound/fanfare_x.wav").toURI().toString());
            MediaPlayer winSoundMediaPlayer = new MediaPlayer(winSoundMedia);
            winSoundMediaPlayer.play();
        } else {
            Media looseSoundMedia = new Media(new File("sound/whah_whah.wav").toURI().toString());
            MediaPlayer looseSoundPlayer = new MediaPlayer(looseSoundMedia);
            looseSoundPlayer.play();
        }

    }
}
