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
import hw01.game.MasterMindUtility;
import hw01.game.Score;
import hw01.net.Protocol;
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
    boolean clientConnected;

    public HostGameController(Stage primaryStage, Scene prevScene, HostGameView view, HostGameModel model) {
        super(primaryStage, prevScene, view, model);
        this.model = (HostGameModel)getModel();
        this.primaryStage = primaryStage;
        this.prevScene = prevScene;

        setUpGoBackBtn(view);
        handleRematchBtn(view);

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

                    //new
                    getTheView().getResetBtn().setVisible(true);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Other player disconnected");
                    alert.show();
                    super.displayResults();
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
     * Sets action to go back btn to ensure it closes sockets properly
     * @param view
     */
    private void setUpGoBackBtn(HostGameView view) {
        view.getGoBackBtn().setOnAction(event -> {
            goBackToMenu();
        });
    }


    /**
     * When rematch button is clicked (this button is only visible at the end of the a game),
     * it waits for other players response, if the other
     * player wants to play again, a new game is created
     * @param view view
     */
    private void handleRematchBtn(HostGameView view) {

        Runnable rematch = () -> {
            boolean success = true;
            try {
                Protocol clientRequest = (Protocol)model.getServer().readObject();
                if (clientRequest.equals(Protocol.READY)){
                    model.getServer().sendObject(Protocol.READY);
                }

            } catch (Exception e) {
                success = false;
            }
            boolean finalSuccess = success;
            Platform.runLater(()->{
                if (!finalSuccess){
                    getTheView().getResultsLbl().setText("Other player disconnected");
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Other player disconnected");
                    alert.show();
                } else {
                    getTheView().getResultsLbl().setText("");
                    try {
                        int[] secretCode = MasterMindUtility.generateRandomSecretCode();
                        model.getServer().sendObject(secretCode);
                        clearBoard();
                        model.createNewGame(secretCode);
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "Unknown error occurred");
                        alert.show();
                        goBackToMenu();
                    }

                }

            });
        };
        view.getResetBtn().setOnAction(event -> {
            view.getResetBtn().setVisible(false);
            view.getResultsLbl().setText("Waiting...");
            Thread thread = new Thread(rematch);
            thread.start();
        });
    }


}
