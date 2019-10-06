/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom
 * Section: 9am
 * Date: 10/6/2019
 * Time: 5:45 PM
 *
 * Project: csci205_hw
 * Package: hw01
 * Class: MasterMindBoard
 *
 * Description:
 *
 * ****************************************
 */
package hw01;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MasterMindBoard {

    private int currentRow;

    final static int MAX_SLOT_VALUE = 6;
    final static int MIN_SLOT_VALUE = 1;
    final static int MAXIMUM_ROWS = 12;
    final static int ROW_SIZE = 4;

    private int[] secretCode = new int[ROW_SIZE];

    public MasterMindBoard() {
        this.currentRow = 1;
        this.generateRandomSecretCode();

    }

    public Row guess(int[] guesses) {
        if (guesses.length != ROW_SIZE) {
            throw new IllegalArgumentException("Number of guesses must be " + ROW_SIZE);
        }

        int correctPegs = 0;
        for (int i = 0; i < guesses.length; i++) {
            if (guesses[i] == this.secretCode[i]){
                correctPegs++;
                guesses[i] = -1;
            }
        }

        int pegsIncorrectPositon = 0;
        List<Object> tempCode = Arrays.asList(this.secretCode);

        for (int guess:
             guesses) {
            if (tempCode.contains((Object)guess)){
                pegsIncorrectPositon++;
                tempCode.remove((Object)guess);
            }
        }

        currentRow++;
        return new Row(correctPegs, pegsIncorrectPositon, ROW_SIZE - correctPegs - pegsIncorrectPositon);

    }

    private void generateRandomSecretCode() {
        Random rand = new Random();
        for (int i = 0; i < this.secretCode.length; i++) {
            this.secretCode[i] = rand.nextInt(MAX_SLOT_VALUE) + MIN_SLOT_VALUE;
        }
    }


    public void playCommandLine(){
        System.out.printf("Guess my code using numbers between %d and %d. You have %d guesses", MIN_SLOT_VALUE, MAX_SLOT_VALUE, MAXIMUM_ROWS);


    }


}
