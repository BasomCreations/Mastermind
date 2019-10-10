/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/10/2019
 * Time: 1:33 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: Score
 *
 * Description:
 *
 * ****************************************
 */
package hw01;

import java.io.Serializable;

public class Score implements Serializable {

    private int turns;
    private double time;
    private String name;
    private boolean win;

    public Score(int turns, double time, String name, boolean win) {
        this.turns = turns;
        this.time = time;
        this.name = name;
        this.win = win;
    }

    public int getTurns() {
        return turns;
    }

    public double getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        int intTime = (int) time;
        String s;

        if (this.win){
            s = name + " guessed the code in: " +  turns + " turns, " + intTime + " seconds";
        }
        else {
            s = name + " did not guess the code";
        }

        return s;
    }
}
