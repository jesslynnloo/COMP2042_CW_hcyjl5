package test.controller;

import org.junit.jupiter.api.Test;
import test.model.HighScore;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreControllerTest {
    HighScoreController highScoreController = new HighScoreController();

    @Test
    void updateScore() {
        HighScore.setSCORE(50);
        int score = 20;
        highScoreController.updateScore(score);
        assertEquals(70, HighScore.getSCORE());
    }

    @Test
    void updateHighScore() {
        HighScore.setHighScore(50);
        HighScore.setSCORE(100);
        highScoreController.updateHighScore();
        assertEquals(100, HighScore.getHighScore());
    }

    @Test
    void restartScore() {
        highScoreController.restartScore();
        assertEquals(0, HighScore.getSCORE());
    }
}