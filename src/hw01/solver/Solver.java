/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jonathan Basom / Sebastian Ascoli
 * Section: 9am / 11 am
 * Date: 10/17/2019
 * Time: 1:45 PM
 *
 * Project: csci205_hw
 * Package: hw01.solver
 * Class: Solver
 *
 * Description:
 *
 * ****************************************
 */
package hw01.solver;

import java.util.IntSummaryStatistics;


/**
 * Parent abstract class for all solvers
 */
public abstract class Solver {
    /**
     * Stats object
     */
    private IntSummaryStatistics stats;


    /**
     * Constructor
     * @author Sebastian
     * @author Jonathan
     */
    public Solver() {
        stats = new IntSummaryStatistics();
    }

    /**
     * Reset stats
     * @author Sebastian
     * @author Jonathan
     */
    public void reset() {
        stats = new IntSummaryStatistics();
    }


    /**
     * Simulate game a fixed amount of times
     * @param numSimulations number of simulations
     * @author Sebastian
     * @author Jonathan
     * @throws Exception
     */
    public void simulate(int numSimulations) throws Exception{
        for (int i = 0; i < numSimulations; i++) {
            this.stats.accept((this.play()));
        }
    };

    /**
     * Get next move
     * @return int array wih the next move
     */
    protected abstract int[] getNextMove();

    /**
     * Play a game
     * @return score
     * @throws Exception
     */
    protected abstract int play() throws Exception;


    /**
     * Get stats object
     * @author Sebastian
     * @author Jonathan
     * @return IntSummaryStatistics object
     */
    public IntSummaryStatistics getStats() {
        return stats;
    }

}
