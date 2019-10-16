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
import java.util.Scanner;
import java.util.stream.Collectors;


/**
 * Master mind game class
 */
public class MasterMindBoard {

    /**
     * Current row the player is using
     */
    private int currentRow;

    /**
     * Boolean stating wether the player has won or not
     */
    private boolean win;

    /**
     * Max value of the slots (inclusive)
     */
    final static int MAX_SLOT_VALUE = 6;

    /**
     * Min value of the slot (inclusive)
     */
    final static int MIN_SLOT_VALUE = 1;

    /**
     * Max value of rows in the game
     */
    final static int MAXIMUM_ROWS = 12;

    /**
     * Size of each row
     */
    final static int ROW_SIZE = 4;

    /**
     * Secret code the player is supposed to guess
     */
    private int[] secretCode = new int[ROW_SIZE];

    /**
     * Start time
     */
    private long startTime;

    /**
     * Finish time
     */
    private long finishTime;

    /**
     * Default Constructor
     */
    public MasterMindBoard() {
        this.currentRow = 1;
        this.win = false;
        int [] code = MasterMindUtility.generateRandomSecretCode();
        this.secretCode = code;
    }

    /**
     * Alternate constructor in which you specify the secret code
     * @param secretCode Array of 4 int that represent the secret code
     */
    public MasterMindBoard(int[] secretCode) throws MasterMindBoardException {
        this.currentRow = 1;
        this.win = false;

        //Make sure secret code is valid
        if (!MasterMindUtility.isSecretCodeValid(secretCode)){throw new MasterMindBoardException("Invalid Secret code");}

        this.secretCode = secretCode;
    }

    /**
     * Method to guess the secret code, returns a row object containing the correct peg and position, correct peg but incorrect position,
     * and incorrect results from the guess
     *
     * Part of this code was based on information found at www.stackoverflow.com
     *
     * @see
     * <a href="https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java">
     *     https://stackoverflow.com/questions/1073919/how-to-convert-int-into-listinteger-in-java</a>
     *
     * @param guesses the array of ints the user is guessing
     * @return a row object which contains the number of correct, correct but wrong position and incorrect
     * guesses from the user
     * @throws Exception if the array does not have the correct size, or if the user tries to guess after
     * exceeding the maximum number of guesses
     */
    public Row guess(int[] guesses) throws Exception {
        if (this.currentRow > this.MAXIMUM_ROWS) {
            throw new MasterMindBoardException("Exceeded maximum number of guesses");
        }

        //Verify dimension of guesses array
        if (guesses.length != ROW_SIZE) {
            throw new MasterMindBoardException("Number of guesses must be " + ROW_SIZE);
        }

        //Get temporary list of guessed secret code elements
        List<Integer> guessList = Arrays.stream(this.secretCode).boxed().collect(Collectors.toList());

        int correctPegs = findNumberCorrectPegs(guessList, guesses);
        int pegsIncorrectPosition = findNumberIncorrectPegs(guessList, guesses);

        this.currentRow++;

        if (correctPegs == ROW_SIZE) {
            this.win = true;
        }

        return new Row(correctPegs, pegsIncorrectPosition, ROW_SIZE - correctPegs - pegsIncorrectPosition);

    }

    /**
     * Checks if the user has won the game
     * @return
     */
    public boolean checkWin() {
        return this.win;
    }

    /**
     * Method to play the game in a command line interface
     */
    public void playCommandLine() throws Exception {

        this.setStartTime();

        System.out.printf("Guess my code using numbers between %d and %d. You have %d guesses\n", MIN_SLOT_VALUE, MAX_SLOT_VALUE, MAXIMUM_ROWS);
        Scanner in = new Scanner(System.in);


        for (int i = 1; i <= this.MAXIMUM_ROWS; i++) {
            System.out.print("Guess " + i + ": ");
            String inputStr = in.nextLine();
            while (!MasterMindUtility.isValidInput(inputStr)) {
                System.out.println("Please provide valid input");
                System.out.print("Guess " + i + ": ");
                inputStr = in.nextLine();
            }

            int[] inputArray = MasterMindUtility.convertStrToArray(inputStr);
            Row result = this.guess(inputArray);
            System.out.print(inputStr + " --> " + result.toString());

            if(this.win){
                System.out.printf("%46s\n", "Congratulations you guessed correctly!");
                break;
            } else {
                if (this.currentRow > MAXIMUM_ROWS){
                    System.out.printf("%42s\n", "You Lost! You ran out of attempts!");
                    System.out.println("The code was " + Arrays.toString(this.secretCode).replaceAll("[\\s\\[\\],]", ""));
                } else {
                    System.out.printf("%18s %d guesses left\n", "Try again.", MAXIMUM_ROWS - this.currentRow + 1);
                }
            }
        }

        this.finishTime = System.nanoTime();

    }

    /**
     * Sets start time
     */
    private void setStartTime(){
        this.startTime = System.nanoTime();
    }

    /**
     * Get total elapsed time in seconds
     * @return time
     */
    public int getPlayTime(){
        int time = (int)((this.finishTime - this.startTime) * Math.pow(10, -9));
        return time;
    }

    /**
     * Returns the number of guesses the player has taken
     * @return number of guesses
     */
    public int getGuesses(){
        return this.currentRow - 1;
    }

    /**
     * Determine the number of correct pegs in the correct position
     * @param guessList List of int that contains the user's guesses
     * @param guesses int[] containing all the user's guesses
     * @return int representing number of correct pegs in the correct position
     */
    private int findNumberCorrectPegs(List<Integer> guessList, int[] guesses) {
        int correctPegs = 0;
        for (int i = 0; i < guesses.length; i++) {
            if (guesses[i] == this.secretCode[i]) {
                correctPegs++;
                //Cast to object to make sure it removes the actual value instead of the index
                guessList.remove((Object) guesses[i]);
                guesses[i] = -1;
            }
        }
        return correctPegs;
    }

    /**
     * Determine the number of correct pegs in the incorrect position
     * @param guessList List of int that contains user's guesses, excluding the correct pegs in the correct position
     * @param guesses int[] containing all the user's guesses
     * @return int representing number of correct pegs in the incorrect position
     */
    private int findNumberIncorrectPegs(List<Integer> guessList, int[] guesses) {
        int pegsIncorrectPosition = 0;
        for (int guess:
                guesses) {
            if (guessList.contains(guess)){
                pegsIncorrectPosition++;
                //Cast to object to make sure it removes the actual value instead of the index
                guessList.remove((Object)guess);
            }
        }
        return pegsIncorrectPosition;
    }
}


class MasterMindBoardException extends Exception{

    MasterMindBoardException(String m){
        super(m);
    }

}