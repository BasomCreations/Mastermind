/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli and Jonathan Basom
 * Section: 11 am / 9 am
 * Date: 10/18/2019
 * Time: 11:20 AM
 *
 * Project: csci205_hw
 * Package: hw01.solver
 * Class: MinimaxSolver
 *
 * Description:
 *
 * ****************************************
 */
package hw01.solver;

import hw01.game.MasterMindBoard;
import hw01.game.MasterMindUtility;
import hw01.game.Row;
import hw01.game.Score;

import java.util.*;

public class MinimaxSolver extends Solver{

    /**
     * first guess
     */
    private final static int[] FIRSTGUESS= {1, 1, 2, 2};


    /**
     * Set of all codes that are still possible
     */
    private List<int[]> s;

    /**
     * All possible codes
     */
    private List<int[]> allPossibleCodes;


    /**
     * Constructor
     */
    public MinimaxSolver() {
        super();
        generateAllPossibleCodes();
    }

    @Override
    protected int[] getNextMove() {

        if(s.size() == 1){
            return s.get(0);
        }

        Hashtable<Row, Integer>[] scoreTable = new Hashtable[allPossibleCodes.size()];

        int index = 0;
        for (int[] possibleGuess:
             allPossibleCodes) {

            for (int[] solution:
                 s) {

                Row guess = MasterMindUtility.makeGuess(possibleGuess, solution);

                if (scoreTable[index] == null){
                    scoreTable[index] = new Hashtable<Row, Integer>();
                    scoreTable[index].put(guess, 1);
                }
                else if (scoreTable[index].containsKey(guess)){
                    scoreTable[index].put(guess, scoreTable[index].get(guess) + 1);
                }
                else {
                    scoreTable[index].put(guess, 1);
                }


            }

            index++;
        }

        int minMaxIndex = 0;
        int minMax = Integer.MAX_VALUE;
        index = 0;
        int[] maximums = new int[allPossibleCodes.size()];
        for (Hashtable<Row, Integer> column:
        scoreTable) {
            int max = 0;
            for (int element:
                 column.values()) {
                if (element > max) {
                    max = element;
                }
            }
            maximums[index] = max;

            if (max < minMax){
                minMax = max;
                minMaxIndex = index;
            }

            index++;
        }

        int[] besGuess = allPossibleCodes.get(minMaxIndex);

        return besGuess;
    }

    @Override
    protected int play() throws Exception {

        s = new ArrayList<>(allPossibleCodes);


        MasterMindBoard board = new MasterMindBoard(true);

        int[] curGuess = FIRSTGUESS;
        Row result = board.guess(curGuess);

        while (!board.checkWin()){

            List<int[]> newS = new ArrayList<>();
            for (int[] possibleSolution:
                 s) {
                Row resultForPossibleSolution = MasterMindUtility.makeGuess(curGuess, possibleSolution);
                if (resultForPossibleSolution.equals(result)){
                    newS.add(possibleSolution);
                }

            }
            s = new ArrayList<>(newS);


            curGuess = getNextMove();


            result = board.guess(curGuess);

        }

        return board.getGuesses();
    }


    /**
     * Method to generate all possible codes
     */
    private void generateAllPossibleCodes(){

        allPossibleCodes = new ArrayList<>();

        int[] curCode = new int[MasterMindBoard.ROW_SIZE];
        generateAllPossibleCodesHelper(curCode, 0);


    }

    /**
     * Helper recursive method for the generateAllPossibleCodes method
     * @param curCode current code being generated
     * @param index current index
     */
    private void generateAllPossibleCodesHelper(int[] curCode, int index){

        if (index == curCode.length){
            allPossibleCodes.add(curCode.clone());
        }
        else {
            for (int val = MasterMindBoard.MIN_SLOT_VALUE; val <= MasterMindBoard.MAX_SLOT_VALUE; val++) {
                curCode[index] = val;
                generateAllPossibleCodesHelper(curCode, index + 1);
            }
        }

    }
    

}
