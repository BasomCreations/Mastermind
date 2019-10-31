/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/31/2019
 * Time: 1:33 AM
 *
 * Project: csci205_hw
 * Package: hw01.GUI.twoPlayerGame
 * Class: twoPlayerGameUtilities
 *
 * Description:
 *
 * ****************************************
 */
package hw01.GUI.twoPlayerGame;


import hw01.game.GameResults;
import hw01.game.Score;

import java.util.List;

/**
 * Useful functions to deal with the 2 player game logistics
 */
public final class twoPlayerGameUtilities {

    public static List<Score> getOrderedListOfScores(Score score1, Score score2){

        GameResults results = new GameResults();
        results.addScore(score1);
        results.addScore(score2);
        results.sortByMoves();
        return results.getScores();
    }

}
