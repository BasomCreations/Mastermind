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
import java.util.concurrent.TimeUnit;

public class NetworkSetUpController {


    public NetworkSetUpController(Stage primaryStage, Scene prevScene, NetworkSetUpView view) {

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
                HostGameMain hostGameMain = new HostGameMain();

                String ip = hostGameMain.getServer().getFormattedIP();
                String port = Integer.toString(hostGameMain.getServer().getPort());
                view.getIpTextField().setText(ip);
                view.getPortTextField().setText(port);

                TimeUnit.SECONDS.sleep(5);

                //hostGameMain.getServer().connectToClient();



            } catch (IOException | InterruptedException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Something went wrong...");
                alert.show();

            }
        });

        view.getOkButton().setOnAction(event -> {


        });
    }

}
