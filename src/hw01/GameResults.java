/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom and Sebastian Ascoli
 * Section: 9 am / 11 am
 * Date: 10/10/2019
 * Time: 7:50 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: GameResults
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameResults implements Serializable {

    /**
     * List of scores
     */
    private List<Score> scores;

    /**
     * Constructor
     */
    public GameResults() {
        scores = new ArrayList<>();
    }

    /**
     * Add a new score to the list
     * @param score Score object
     */
    public void addScore(Score score) {
        scores.add(score);
    }

    /**
     * Sort scores by number of moves
     */
    public void sortByMoves() {
        scores.sort((Score s1, Score s2) -> {
            if (s1.getTurns() != s2.getTurns()) {
                return Integer.compare(s1.getTurns(), s2.getTurns());
            } else {
                return Integer.compare(s1.getTime(), s2.getTime());
            }
        });

    }

    /**
     * Sort scores by time
     */
    public void sortByTime() {
        scores.sort((Score s1, Score s2) -> {
            if (s1.getTime() != s2.getTime()) {
                return Integer.compare(s1.getTime(), s2.getTime());
            } else {
                return Integer.compare(s1.getTurns(), s2.getTurns());
            }
        });

    }


    /**
     * Convert to string displaying the results in a nice looking way
     * @return
     */
    @Override
    public String toString() {
        String s = "";

        int counter = 1;
        for (Score sc:
             this.scores) {
            s += counter++ + ". " + sc.toString() + "\n";
        }
        return s;
    }

    /**
     * Retrieves the List of Scores
     * @return List of Scores
     */
    public List<Score> getScores() {
        return scores;
    }
}
