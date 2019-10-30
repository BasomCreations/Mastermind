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

import javafx.scene.control.Alert;

public class NetworkSetUpController {

    public NetworkSetUpController(NetworkSetUpView view) {

        view.getJoinBtn().setOnAction(event -> {
            if(view.getNameInputField().getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "You need a name");
                alert.show();
                return;
            }
            view.getHostBtn().setVisible(false);
            view.getJoinBtn().setVisible(false);
            view.setHostModeProperty(true);
        });

        view.getHostBtn().setOnAction(event -> {
            if(view.getNameInputField().getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR, "You need a name");
                alert.show();
                return;
            }
            view.getHostBtn().setVisible(false);
            view.getJoinBtn().setVisible(false);
            view.setJoinModeProperty(true);
        });

        view.getOkButton().setOnAction(event -> {

        });
    }
}
