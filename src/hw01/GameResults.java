/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
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

import java.util.ArrayList;
import java.util.List;

public class GameResults {

    private List<Score> scores;

    public GameResults() {
        scores = new ArrayList<>();
    }

    public void addScore(Score score) {
        scores.add(score);
    }

    public void sortByMoves() {
        scores.sort((Score s1, Score s2) -> {
            if (s1.getTurns() != s2.getTurns()) {
                return Integer.compare(s1.getTurns(), s2.getTurns());
            } else {
                return Integer.compare(s1.getTime(), s2.getTime());
            }
        });

    }


    public void sortByTime() {
        scores.sort((Score s1, Score s2) -> {
            if (s1.getTime() != s2.getTime()) {
                return Integer.compare(s1.getTurns(), s2.getTurns());
            } else {
                return Integer.compare(s1.getTurns(), s2.getTurns());
            }
        });

    }


    @Override
    public String toString() {
        String s = "";

        int counter = 1;
        for (Score sc:
             this.scores) {
            s += counter++ + sc.toString() + "\n";
        }
        return s;
    }
}
