/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/19/2019
 * Time: 4:16 PM
 *
 * Project: csci205_hw
 * Package: hw01.solver
 * Class: SmartSolver
 *
 * Description:
 *
 * ****************************************
 */
package hw01.solver;

import hw01.game.MasterMindBoard;

import java.util.ArrayList;
import java.util.List;

public abstract class SmartSolver extends Solver {

    protected List<int[]> allPossibleCodes;

    /**
     * Constructor
     */
    public SmartSolver() {
        super();
        generateAllPossibleCodes();
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
