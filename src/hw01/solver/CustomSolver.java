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

import hw01.game.MasterMindBoard;
import hw01.game.MasterMindUtility;
import hw01.game.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomSolver extends SmartSolver{


    /**
     * Set of all codes that are still possible
     */
    private List<int[]> s;

    /**
     * Constructor
     */
    public CustomSolver() {
        super();
    }


    @Override
    protected int[] getNextMove() {
        Random rand = new Random();
        int index = rand.nextInt(s.size());
        return s.get(index);

    }

    @Override
    protected int play() throws Exception {
        s = new ArrayList<>(allPossibleCodes);


        MasterMindBoard board = new MasterMindBoard(true);

        int[] curGuess = getNextMove();
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


}

