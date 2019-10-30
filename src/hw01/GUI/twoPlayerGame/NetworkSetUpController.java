/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/29/2019
 * Time: 7:56 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: NetworkSetUpController
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class NetworkSetUpController {

    /**
     * Thread for dealing with host
     */
    private Thread hostWaitThread;

    /**
     * The stage in which this pop up window is
     */
    private Stage windowStage;

    /**
     * Main method for host game
     */
    private HostGameMain hostGameMain;

    /**
     * Main method for client game
     */
    private ClientGameMain clientGameMain;

    /**
     * View for the pop up window
     */
    private NetworkSetUpView view;

    /**
     * Constructor
     * @param primaryStage primary stage
     * @param mainMenuScene main menu scene
     * @param windowStage stage in which this window is
     * @param theView the view
     */
    public NetworkSetUpController(Stage primaryStage, Scene mainMenuScene, Stage windowStage ,NetworkSetUpView theView) {

        hostWaitThread = null;
        hostGameMain = null;
        clientGameMain = null;
        this.windowStage = windowStage;
        this.view = theView;


        createJoinBtnAction();
        createHostBtnAction();
        createJoinOkBtnAction();
        createOnCloseRequestAction();
    }

    /**
     * Action taken when user closes the networking pop up,
     * this makes sure sockets are properly closed to ensure
     * it works properly if user opens the pop up again
     */
    private void createOnCloseRequestAction() {
        windowStage.setOnCloseRequest(event -> {
            if (hostWaitThread != null){
                hostWaitThread.interrupt();
            }

            if (hostGameMain != null){
                try {
                    hostGameMain.getServer().closeServerSocket();
                } catch (IOException e) {}
                try {
                    hostGameMain.getServer().closeClientSocket();
                } catch (Exception e) {}
            }
        });
    }

    /**
     * Sets action to Ok Button (button where user clicks ok after writing servers IP and port)
     */
    private void createJoinOkBtnAction() {
        view.getJoinOkButton().setOnAction(event -> {
            try {
                clientGameMain = new ClientGameMain();
                String ip = view.getIpTextField().getText();
                int port = Integer.parseInt(view.getPortTextField().getText());

                clientGameMain.getGameClient().connectToServer(ip, port);
                windowStage.close();

                //TODO Create new server game

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Make sure your IP and port values are correct");
                alert.show();
            }
        });
    }


    /**
     * Creates action to Host Btn, it sets required fields visible, asserts
     * user entered a name, it displays the IP and Port the other player
     * will need to connect and it creates a thread in which the server socket waits
     * for a client
     */
    private void createHostBtnAction() {
        view.getHostBtn().setOnAction(event -> {
            if(view.getNameInputField().getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "You need a name");
                alert.show();
                return;
            }
            view.getHostBtn().setVisible(false);
            view.getJoinBtn().setVisible(false);
            view.setHostModeProperty(true);


            try {
                hostGameMain = new HostGameMain();

                String ip = hostGameMain.getServer().getFormattedIP();
                String port = Integer.toString(hostGameMain.getServer().getPort());
                view.getIpTextField().setText(ip);
                view.getPortTextField().setText(port);

                handleHostThread();



            } catch (IOException e) {
                //This should not happen
                //e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                alert.show();
                windowStage.close();

            }
        });
    }

    /**
     * Sets action for join button (when user decides to join a game), it
     * sets required view fields to visible and verifies the user entered name
     */
    private void createJoinBtnAction() {
        view.getJoinBtn().setOnAction(event -> {
            if(view.getNameInputField().getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "You need a name");
                alert.show();
                return;
            }
            view.getHostBtn().setVisible(false);
            view.getJoinBtn().setVisible(false);
            view.setJoinModeProperty(true);
        });
    }


    /**
     * Handles thread were host waits for client to connect
     */
    private void handleHostThread(){

        Runnable connect = () -> {
            boolean success = false;
            try {
                hostGameMain.getServer().connectToClient();
                //System.out.println("connected");
                success = true;

            } catch (IOException e) {
            }

            boolean successFinal = success;
            Platform.runLater(() ->{
                if(successFinal){
                    windowStage.close();
                    //TODO Open new Scene for 2 player Game
                }

            });

        };

        hostWaitThread = new Thread(connect);
        hostWaitThread.start();
    }





}

