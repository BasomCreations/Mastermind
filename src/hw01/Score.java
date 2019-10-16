/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom and Sebastian Ascoli
 * Section: 9 am / 11 am
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

/**
 * Class for score objects
 */
public class Score implements Serializable {

    /**
     * Number of turns taken to beat the game
     */
    private int turns;

    /**
     * Time (in seconds) taken to beat the game
     */
    private int time;

    /**
     * Name of the player
     */
    private String name;

    /**
     * Wether the game was beaten or not
     */
    private boolean win;

    /**
     * Constructor
     * @param turns turns to beat the game
     * @param time time taken (s)
     * @param name name of player
     * @param win whether the game was beaten or not
     */
    public Score(int turns, int time, String name, boolean win) {
        this.turns = turns;
        this.time = time;
        this.name = name;
        this.win = win;
    }

    public int getTurns() {
        return turns;
    }

    public int getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    /**
     * Nice String representation of a players results
     * @return
     */
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
