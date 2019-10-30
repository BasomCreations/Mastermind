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
    private String playerName;

    public HostGameModel() throws IOException {
        super();
        server = new GameServer();
        playerName = "Player1";
    }

    @Override
    public String getResults() {
        String s;
        if (getBoard().checkWin()) {
            s = playerName + " guessed the correct code in:\n" +
                    getBoard().getGuesses() + " Turns, " + getBoard().getPlayTime() + " Seconds";
        }
        else{
            s = playerName + " ran out of turns!\nPlaytime: " + getBoard().getPlayTime() + " seconds";
        }
        return s;
    }

    public int[] getSecretCode() {
        return getBoard().getSecretCode();
    }



    public GameServer getServer() {
        return server;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

