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
import hw01.GUI.sceneTemplate.SceneViewTemplate;
import hw01.game.GameResults;
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

public class ClientGameController extends OnePlayerGameController {
    ClientGameModel model;
    Stage primaryStage;
    Scene prevScene;
    public ClientGameController(Stage primaryStage, Scene prevScene, ClientGameView view, ClientGameModel model) {
        super(primaryStage, prevScene, view, model);
        this.model = (ClientGameModel)super.getModel();
        this.primaryStage = primaryStage;
        this.prevScene = prevScene;
        setUpGoBackBtn(view);
        handleRematchBtn(view);

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

                    getTheView().getResetBtn().setVisible(true);
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

    /**
     * Goes back to menu and closes sockets
     */
    private void goBackToMenu(){
        primaryStage.setScene(prevScene);
        model.closeSockets();
    }

    /**
     * Sets action to go back button to ensure it closes sockets properly
     * @param view
     */
    private void setUpGoBackBtn(ClientGameView view) {
        view.getGoBackBtn().setOnAction(event -> {
            goBackToMenu();
        });
    }



    private void handleRematchBtn(ClientGameView view){


        Runnable rematch = () -> {
            boolean success = true;
            try {
                model.getGameClient().sendObject(Protocol.READY);
                Protocol serverResponse = (Protocol) model.getGameClient().readObject();
                if (serverResponse.equals(Protocol.QUIT)){
                    success = false;
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
                        int[] secretCode = (int[]) model.getGameClient().readObject();
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
