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

import hw01.net.GameServer;

import java.io.IOException;

public class HostGameMain {
    private GameServer server;

    public HostGameMain() throws IOException {
        server = new GameServer();
    }

    public GameServer getServer() {
        return server;
    }
}
