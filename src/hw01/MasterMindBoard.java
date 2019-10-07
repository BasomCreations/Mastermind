/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Names: Jonathan Basom and Sebastian Ascoli
 * Section: 9am / 11 am
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
import java.util.stream.Collectors;

public class MasterMindBoard {

    private int currentRow;

    final static int MAX_SLOT_VALUE = 6;
    final static int MIN_SLOT_VALUE = 1;
    final static int MAXIMUM_ROWS = 12;
    final static int ROW_SIZE = 4;

    private int[] secretCode = new int[ROW_SIZE];

    /**
     * Constructor
     */
    public MasterMindBoard() {
        this.currentRow = 1;
        this.generateRandomSecretCode();

    }

    /**
     * Alternate constructor in which you specify the secret code
     * @param secretCode
     */
    public MasterMindBoard(int[] secretCode) throws IllegalArgumentException {
        this.currentRow = 1;

        //Make sure secret code is valid
        if (secretCode.length != ROW_SIZE)
        {throw new IllegalArgumentException(String.format("Code must be %d digits", ROW_SIZE));}
        for (int digit:
             secretCode) {
            if (digit < MIN_SLOT_VALUE || digit > MAX_SLOT_VALUE) {
                throw new IllegalArgumentException(String.format("Code digits mus be between %d and %d", MIN_SLOT_VALUE, MAX_SLOT_VALUE));
            }
        }

        this.secretCode = secretCode;

    }



    public Row guess(int[] guesses) {
        //Verify dimension of guesses array
        if (guesses.length != ROW_SIZE) {
            throw new IllegalArgumentException("Number of guesses must be " + ROW_SIZE);
        }

        //Get list of temporary list of secret code elements
        List<Integer> tempCode = Arrays.stream(this.secretCode).boxed().collect(Collectors.toList());
        int correctPegs = 0;
        for (int i = 0; i < guesses.length; i++) {
            if (guesses[i] == this.secretCode[i]){
                correctPegs++;
                tempCode.remove((Object)guesses[i]);
                guesses[i] = -1;
            }
        }

        int pegsIncorrectPosition = 0;
        for (int guess:
             guesses) {
            if (tempCode.contains((Object)guess)){
                pegsIncorrectPosition++;
                tempCode.remove((Object)guess);
            }
        }

        this.currentRow++;
        return new Row(correctPegs, pegsIncorrectPosition, ROW_SIZE - correctPegs - pegsIncorrectPosition);

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
