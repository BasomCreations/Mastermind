/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli / Jonathan Basom
 * Section: 11 am / 9 am
 * Date: 10/26/2019
 * Time: 4:14 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: OnePlayerGameModel
 *
 * Description:
 * Class for the Model of a One Player Game
 * ****************************************
 */
package hw01.GUI.onePlayerGame;

import hw01.game.MasterMindBoard;
import hw01.game.MasterMindBoardException;
import hw01.game.Row;
import hw01.game.Score;

/**
 * Class for the Model of a One Player Game
 */
public class OnePlayerGameModel {

    /** MasterMindBoard for the game */
    private MasterMindBoard board;

    /**
     * No parameter Constructor
     */
    public OnePlayerGameModel() {

        board = new MasterMindBoard();

        //TODO get rid of this
        System.out.println();
        for (int n:
             board.getSecretCode()) {
            System.out.print(n);
        }

    }

    /**
     * Make a guess on the board
     * @param guesses int[] representing the guesses for the board
     * @return Row object for the result of the guess
     * @throws MasterMindBoardException
     */
    public Row guess(int[] guesses) throws MasterMindBoardException {
        return board.guess(guesses);
    }

    /**
     * Check the board for a win
     * @return boolean - true if win
     */
    public boolean checkWin() {
        return board.checkWin();
    }

    /**
     * Create a new game
     */
    public void createNewGame() {
        board = new MasterMindBoard();

        //TODO get rid of this
        System.out.println();
        for (int n:
                board.getSecretCode()) {
            System.out.print(n);
        }
    }

    /**
     * Create a new game with a specified secret code
     * @param secretCode int[] representing the secret code
     * @throws MasterMindBoardException
     */
    public void createNewGame(int[] secretCode) throws MasterMindBoardException {
        board = new MasterMindBoard(secretCode);

        //TODO get rid of this
        System.out.println();
        for (int n:
                board.getSecretCode()) {
            System.out.print(n);
        }
    }

    /**
     * Retrieves the results at the end of a game
     * @return Score Object containing the results of the game
     */
    public Score getResults() {
        return new Score(getBoard().getGuesses(), getBoard().getPlayTime(), "You", checkWin());
    }

    /**
     * Creates a new MasterMindBoard with a specified secret code
     * @param secretCode int[] representing the secret code of the game
     * @throws MasterMindBoardException
     */
    public void createNewBoard(int[] secretCode) throws MasterMindBoardException {
        board = new MasterMindBoard(secretCode);
    }

    /**
     * Retrieve the current turn of the game
     * @return
     */
    public int getCurrentTurn() {
        return board.getGuesses();
    }

    /**
     * Retrieve the board for the game
     * @return MasterMindBoard
     */
    public MasterMindBoard getBoard() {
        return board;
    }

}
