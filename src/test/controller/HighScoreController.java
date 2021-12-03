package test.controller;

import test.model.HighScore;

public class HighScoreController {

    public HighScoreController() {

    }

    public void updateScore (int score) {
        HighScore.setSCORE(HighScore.getSCORE() + score);
    }

    public void updateHighScore () {
        if (HighScore.getSCORE() > HighScore.getHighScore()) {
            HighScore.setHighScore(HighScore.getSCORE());
        }
    }

    public void restartScore () {
        HighScore.setSCORE(0);
    }
}
