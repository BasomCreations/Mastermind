/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Sebastian Ascoli
 * Section: 11 am
 * Date: 10/19/2019
 * Time: 4:12 PM
 *
 * Project: csci205_hw
 * Package: hw01.solver
 * Class: CustomSolver
 *
 * Description:
 *
 * ****************************************
 */
package hw01.solver;

import java.util.List;

public class CustomSolver extends Solver{

    /**
     * first guess
     */
    private final static int[] FIRSTGUESS= {1, 1, 2, 2};


    /**
     * Set of all codes that are still possible
     */
    private List<int[]> s;

    /**
     * Constructor
     */
    public CustomSolver() {
        super();
        //generateAllPossibleCodes();
    }


    @Override
    protected int[] getNextMove() {
        return new int[0];
    }

    @Override
    protected int play() throws Exception {
        return 0;
    }
}
