/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/26/2019
 * Time: 4:14 PM
 *
 * Project: csci205_hw
 * Package: hw01.GUI
 * Class: OnePlayerGameModel
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.onePlayerGame;

import hw01.game.MasterMindBoard;
import hw01.game.MasterMindBoardException;
import hw01.game.Row;
import javafx.scene.paint.Paint;

import java.util.Hashtable;

public class OnePlayerGameModel {

    private MasterMindBoard board;

    public OnePlayerGameModel() {

        board = new MasterMindBoard();

        //TODO get rid of this
        System.out.println();
        for (int n:
             board.getSecretCode()) {
            System.out.print(n);
        }

    }

    public Row guess(int[] guesses) throws MasterMindBoardException {
        return board.guess(guesses);
    }

    public boolean checkWin() {
        return board.checkWin();
    }

    public void createNewGame() {
        board = new MasterMindBoard();

        //TODO get rid of this
        System.out.println();
        for (int n:
                board.getSecretCode()) {
            System.out.print(n);
        }
    }

    public String getResults() {
        String s;
        if (board.checkWin()) {
            s = "Congratulations! You guessed the correct code in " +
                    board.getGuesses() + " turns.";
        }
        else{
            s = "Game over, you ran out of turns!";
        }
        return s;
    }

    public int getCurrentTurn() {
        return board.getGuesses();
    }



    public MasterMindBoard getBoard() {
        return board;
    }


}
