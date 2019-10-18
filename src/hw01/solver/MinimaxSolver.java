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

import java.util.ArrayList;
import java.util.List;

public class MinimaxSolver extends Solver{

    /**
     * Set of all possible codes
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
        //TODO
        return new int[0];
    }

    @Override
    protected int play() throws Exception {


        //TODO
        return 0;
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
     * Helper mrecursive method for the generateAllPossibleCodes method
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
