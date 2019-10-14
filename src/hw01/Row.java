/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom and Sebastian Ascoli
 * Section: 9am / 11 am
 * Date: 10/6/2019
 * Time: 6:01 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: Row
 *
 * Description:
 *
 * ****************************************
 */
package hw01;


/**
 * Row class
 * row objects store the number of correct pegs, pegs in incorrect position and incorrect pegs
 * from a user's guess in the MasterMind game
 */
public class Row {

    /**
     * Number of correct pegs
     */
    private int correctPegs;

    /**
     * Number of pegs in incorrect position
     */
    private int pegsInCorrectPosition;

    /**
     * Number of incorrect pegs
     */
    private int incorrectPegs;

    /**
     * Constructor
     * @param correctPegs number of correct pegs (red or *)
     * @param pegsInCorrectPosition number of pegs in incorrect position (white or +)
     * @param incorrectPegs number of incorrect pegs (-)
     */
    public Row(int correctPegs, int pegsInCorrectPosition, int incorrectPegs) {
        this.correctPegs = correctPegs;
        this.pegsInCorrectPosition = pegsInCorrectPosition;
        this.incorrectPegs = incorrectPegs;
    }

    public int getCorrectPegs() {
        return correctPegs;
    }

    public int getPegsInCorrectPosition() {
        return pegsInCorrectPosition;
    }

    public int getIncorrectPegs() {
        return incorrectPegs;
    }


    /**
     * Gets string representation of object
     * for example: a row object with 2 correct pegs, 1 peg in incorrect position and
     * 1 incorrect peg would be represented as **+-
     * @return
     */
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < this.correctPegs; i++) {
            s += "*";
        }
        for (int i = 0; i < this.pegsInCorrectPosition; i++) {
            s += "+";
        }
        for (int i = 0; i < this.incorrectPegs; i++) {
            s += "-";
        }
        return  s;
    }
}
