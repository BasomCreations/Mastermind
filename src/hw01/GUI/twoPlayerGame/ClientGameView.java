/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 10/30/2019
 * Time: 3:58 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: ClientGameView
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.GUI.onePlayerGame.OnePlayerGameView;

public class ClientGameView extends OnePlayerGameView {
    public ClientGameView(double w, double h, ClientGameModel model) {

        super(w, h, model);
        getResetBtn().setVisible(false);
        getResetBtn().setText("rematch");
        getTitle().setText("Multiplayer");
    }

}
