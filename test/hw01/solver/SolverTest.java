package hw01.solver;

import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;

import static org.junit.jupiter.api.Assertions.*;

class SolverTest {

    @Test
    void getSimTime() throws Exception {

        Solver customSolver = new CustomSolver();

        long startTime = System.nanoTime();
        customSolver.simulate(1000);
        long endTime = System.nanoTime();

        int ellapsedTime = (int)((startTime - endTime) * Math.pow(10, -9));

        assertEquals(ellapsedTime, customSolver.getSimTime());
    }

    @Test
    void getStats() throws Exception {

        Solver randSolver = new RandomSolver();

        randSolver.simulate(1000);

        IntSummaryStatistics stats = randSolver.getStats();
        assertEquals(1000, stats.getCount());

    }
}