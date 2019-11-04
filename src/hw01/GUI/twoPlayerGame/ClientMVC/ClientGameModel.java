/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli / Jonathan Basom
 * Section: 11 am / 9 am
 * Date: 10/29/2019
 * Time: 10:03 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: ClientGameMain
 *
 * Description:
 * Model for the client side of a two player game
 * ****************************************
 */
package hw01.GUI.twoPlayerGame.ClientMVC;

import hw01.GUI.onePlayerGame.OnePlayerGameModel;
import hw01.game.MasterMindBoard;
import hw01.game.Score;
import hw01.net.GameClient;

/**
 * Model for the client side of a two player game
 * @author Jonathan
 * @author Sebastian
 */
public class ClientGameModel extends OnePlayerGameModel {

    /** GameClient object */
    private GameClient gameClient;

    /** Name of player */
    private String playerName;

    /**
     * Constructor
     * @author Jonathan
     * @author Sebastian
     */
    public ClientGameModel() {
        super();
        gameClient = new GameClient();
        playerName = "Player2";

    }

    /**
     * Retrieve the GameClient object
     * @return GameClient
     */
    public GameClient getGameClient() {
        return gameClient;
    }

    /**
     * Gets the Client's end of game score
     * @return Score object containing the end results
     * @author Jonathan
     * @author Sebastian
     */
    @Override
    public Score getResults() {
        return new Score(getBoard().getGuesses(), getBoard().getPlayTime(), playerName, checkWin());
    }

    /**
     * Retrieves the player's name
     * @return String for the name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the player's name
     * @param playerName String for the name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Closes socket
     * @author Sebastian
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
     * @author Jonathan
     */
    public void setBoardSize(int numRows, int numGuesses) {
        MasterMindBoard.DEFAULT_MAXIMUM_ATTEMPTS = numRows;
        MasterMindBoard.ROW_SIZE = numGuesses;
    }

    /**
     * Retrieves the number of rows on the board
     * @return int for the number of rows
     * @author Jonathan
     */
    public int getNumRows() {
        return getBoard().DEFAULT_MAXIMUM_ATTEMPTS;
    }

    /**
     * Retrieves the number of guesses per row
     * @return int for the number of guesses
     * @author Jonathan
     */
    public int getNumGuesses() {
        return getBoard().ROW_SIZE;
    }
}
