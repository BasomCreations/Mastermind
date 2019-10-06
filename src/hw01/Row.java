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

public class Row {

    private int correctPegs;
    private int pegsInCorrectPosition;
    private int incorrectPegs;

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
