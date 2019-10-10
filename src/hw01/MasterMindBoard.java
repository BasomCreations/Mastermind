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
        int [] code = this.generateRandomSecretCode();
        this.secretCode = code;

    }

    /**
     * Alternate constructor in which you specify the secret code
     * @param secretCode
     */
    public MasterMindBoard(int[] secretCode) throws IllegalArgumentException {
        this.currentRow = 1;
        this.win = false;

        //Make sure secret code is valid
        if (!this.isSecretCodeValid(secretCode)){throw new IllegalArgumentException("Invalid Secret code");}

        this.secretCode = secretCode;

    }

    /**
     * Cehck is secret code provided in the constructor is valid
     * @param secretCode secret code array of integers
     * @return boolean stating weather code is valid or not
     */
    private boolean isSecretCodeValid(int[] secretCode){
        if (secretCode.length != ROW_SIZE) {return false;}
        for (int digit:
                secretCode) {
            if (digit < MIN_SLOT_VALUE || digit > MAX_SLOT_VALUE) {
                return false;
            }
        }
        return true;
    }


    /**
     * Method to guess the secret code, returns a row object containing the correect/correct but incorrect position /
     * incorrect results from the guess
     * @param guesses the array of ints the user is guessing
     * @return a row object which contains the number of correct, correct but wrong position and incorrect
     * guesses from the user
     * @throws Exception if the array does not have the correct size, or if the user tries to guess after
     * exceeding the maximum number of guesses
     */
    public Row guess(int[] guesses) throws Exception {
        if (this.currentRow > this.MAXIMUM_ROWS) {
            throw new Exception("Exceeded maximum number of guesses");
        }

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
                //Cast to object to make sure it removes the actual value instead of the index
                tempCode.remove((Object)guesses[i]);
                guesses[i] = -1;
            }
        }

        int pegsIncorrectPosition = 0;
        for (int guess:
             guesses) {
            if (tempCode.contains(guess)){
                pegsIncorrectPosition++;
                //Cast to object to make sure it removes the actual value instead of the index
                tempCode.remove((Object)guess);
            }
        }

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
     * Generate random secret code
     */

    public static int[] generateRandomSecretCode() {

        int[] secretCode = new int[ROW_SIZE];

        Random rand = new Random();
        for (int i = 0; i < secretCode.length; i++) {
            secretCode[i] = rand.nextInt(MAX_SLOT_VALUE) + MIN_SLOT_VALUE;
        }
        return secretCode;
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
            while (!isValidInput(inputStr)) {
                System.out.println("Please provide valid input");
                System.out.print("Guess " + i + ": ");
                inputStr = in.nextLine();
            }

            int[] inputArray = convertStrToArray(inputStr);
            Row result = this.guess(inputArray);
            System.out.print(inputStr + " --> " + result.toString());

            if(this.win){
                System.out.println("    Congratulations you guessed!");
                break;
            } else {
                if (this.currentRow > MAXIMUM_ROWS){
                    System.out.println("    You Lost! You run out of attempts!");
                } else {
                    System.out.printf("    Try again. %d guesses left\n", MAXIMUM_ROWS - this.currentRow + 1);
                }
            }


        }

        this.finishTime = System.nanoTime();

    }

    /**
     * Checks if the guess code string is in the right format
     * @param input input string
     * @return boolean
     */
    private static boolean isValidInput(String input) {
        if (input.length() != ROW_SIZE) {
            return false;
        }
        String pattern = String.format("[%d-%d]{%d}", MIN_SLOT_VALUE, MAX_SLOT_VALUE, ROW_SIZE);
        return input.matches(pattern);
    }


    /**
     * Converts guess string (which should be already validated) into an array that the guess method
     * can take as a parameter
     * @param input string with user's guess
     * @return array of integers the guess method can take as parameter
     */
    private static int[] convertStrToArray(String input) {
        int[] intArray = new int[ROW_SIZE];
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            intArray[i] = Integer.parseInt(String.valueOf(c));
        }

        return intArray;
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




}
