/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/29/2019
 * Time: 10:03 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: HostGameMain
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.net.GameServer;

import java.io.IOException;

public class HostGameModel extends OnePlayerGameModel {
    private GameServer server;
    private boolean isConnected;

    public HostGameModel() throws IOException {
        super();
        server = new GameServer();
    }

    public int[] getSecretCode() {
        return getBoard().getSecretCode();
    }



    public GameServer getServer() {
        return server;
    }


}

