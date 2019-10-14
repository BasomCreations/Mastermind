package hw01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    /**
     * Verify toString method of Score object
     */
    @Test
    void testToString() {
        Score score1 = new Score(5, 40, "User1", true);
        assertEquals("User1 guessed the code in: 5 turns, 40 seconds", score1.toString());

        Score score2 = new Score(12, 120, "User2", false);
        assertEquals("User2 did not guess the code", score2.toString());
    }
}