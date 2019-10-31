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
 * Class: ClientGameMain
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;

import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.game.MasterMindBoard;
import hw01.game.MasterMindBoardException;
import hw01.game.Score;
import hw01.net.GameClient;

import java.io.IOException;

public class ClientGameModel extends OnePlayerGameModel {
    private GameClient gameClient;
    private String playerName;

    public ClientGameModel() throws MasterMindBoardException {
        super();
        gameClient = new GameClient();
        playerName = "Player2";

    }

    public GameClient getGameClient() {
        return gameClient;
    }

    @Override
    public Score getResults() {
        return new Score(getBoard().getGuesses(), getBoard().getPlayTime(), playerName, checkWin());
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    /**
     * Closes socket
     */
    public void closeSockets(){
        try {
            gameClient.close();
        } catch (Exception e) {
        }
    }



    /**
     * Sets the default size of the board
     * @param numRows int for the number of rows on the board
     * @param numGuesses int for the number of guesses per row
     */
    public void setBoardSize(int numRows, int numGuesses) {
        MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS = numRows;
        MasterMindBoard.ROW_SIZE = numGuesses;
    }

    /**
     * Retrieves the number of rows on the board
     * @return int for the number of rows
     */
    public int getNumRows() {
        return getBoard().DEFAULT_MAXIMUM_ATTEMPTS;
    }

    /**
     * Retrieves the number of guesses per row
     * @return int for the number of guesses
     */
    public int getNumGuesses() {
        return getBoard().ROW_SIZE;
    }
}
