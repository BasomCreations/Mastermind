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
import hw01.game.GameResults;
import hw01.game.Score;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HostGameController extends OnePlayerGameController {
    HostGameModel model;
    Stage primaryStage;
    Scene prevScene;
    public HostGameController(Stage primaryStage, Scene prevScene, HostGameView view, HostGameModel model) {
        super(primaryStage, prevScene, view, model);
        this.model = (HostGameModel)getModel();
        this.primaryStage = primaryStage;
        this.prevScene = prevScene;
        setUpGoBackBtn(view);

    }



    /**
     * Method that deals when the game is finished and host has to wait for client
     */
    @Override
    public void finishGame() {

        getTheView().getResultsLbl().setText("Waiting for other player...");
        getTheView().getResultsLbl().setVisible(true);

        Runnable pleaseDontFreezeGUI = () -> {

            boolean success = false;
            Score clientScore = null;
            try {
                clientScore = (Score)model.getServer().readObject();
                model.getServer().sendObject(getModel().getResults());
                success = true;
            } catch (Exception e) {
            }
            boolean successFinal = success;

            Score finalClientScore = clientScore;
            Platform.runLater(() ->{
                if(successFinal){
                    displayTwoPlayerResults(finalClientScore);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Other player disconnected");
                    alert.show();
                    goBackToMenu();
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

    /**
     * Goes back to menu and closes sockets
     */
    private void goBackToMenu(){
        primaryStage.setScene(prevScene);
        model.closeSockets();
    }

    /**
     * Makes sure sockets are properly closed when going back to main scene
     * @param view
     */
    private void setUpGoBackBtn(HostGameView view) {
        view.getGoBackBtn().setOnAction(event -> {
            goBackToMenu();
        });
    }

}
