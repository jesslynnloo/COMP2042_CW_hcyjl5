package test.model;

public class HighScore {

    private static int HIGHSCORE;
    private static int SCORE;

    public static int getHighScore() {
        return HIGHSCORE;
    }

    public static void setHighScore(int highScore) {
        HIGHSCORE = highScore;
    }

    public static int getSCORE() {
        return SCORE;
    }

    public static void setSCORE(int SCORE) {
        HighScore.SCORE = SCORE;
    }
}
