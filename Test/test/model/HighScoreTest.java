package test.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreTest {

    @Test
    void getHighScore() {
        HighScore.setHighScore(1125);
        assertEquals(1125, HighScore.getHighScore());
    }

    @Test
    void setHighScore() {
        HighScore.setHighScore(1000);
        assertEquals(1000, HighScore.getHighScore());
    }

    @Test
    void getSCORE() {
        HighScore.setSCORE(100);
        assertEquals(100, HighScore.getSCORE());
    }

    @Test
    void setSCORE() {
        HighScore.setSCORE(200);
        assertEquals(200, HighScore.getSCORE());
    }
}