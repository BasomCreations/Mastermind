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

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class NetworkSetUpController {

    private Thread hostWaitThread;
    private Stage windowStage;

    private HostGameMain hostGameMain;
    private ClientGameMain clientGameMain;

    public NetworkSetUpController(Stage primaryStage, Scene prevScene, Stage windowStage ,NetworkSetUpView view) {

        hostWaitThread = null;
        hostGameMain = null;
        clientGameMain = null;
        this.windowStage = windowStage;

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

            }
        });

        view.getJoinOkButton().setOnAction(event -> {
            try {
                clientGameMain = new ClientGameMain();
                String ip = view.getIpTextField().getText();
                int port = Integer.parseInt(view.getPortTextField().getText());

                clientGameMain.getGameClient().connectToServer(ip, port);
                //System.out.println("connected");
                windowStage.close();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error: Make sure your IP and port values are correct");
                alert.show();
            }
        });


        /**
         * Make sure the sockets are properly closed if window is exited,
         * so that there are no errors when window is opened again
         */
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

    private void handleHostThread(){
        Runnable connect = () -> {


            try {
                hostGameMain.getServer().connectToClient();
                //System.out.println("connected");
                windowStage.close();

                //TODO Open new Scene for 2 player Game
            } catch (IOException e) {
                //This should not happen
                //e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                alert.show();
            }


        };

        hostWaitThread = new Thread(connect);
    }

}

