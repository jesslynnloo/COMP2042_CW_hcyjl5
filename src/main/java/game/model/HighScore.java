package game.model;

/**
 * This is the HighScore class.
 */
public class HighScore {

    private static int HIGHSCORE;
    private static int SCORE;

    /**
     * Get the HIGHSCORE.
     * @return An int value of the HIGHSCORE.
     */
    public static int getHighScore() {
        return HIGHSCORE;
    }

    /**
     * Set the HIGHSCORE.
     * @param highScore An int value which contains the value for the HIGHSCORE.
     */
    public static void setHighScore(int highScore) {
        HIGHSCORE = highScore;
    }

    /**
     * Get the SCORE.
     * @return An int value of the SCORE.
     */
    public static int getSCORE() {
        return SCORE;
    }

    /**
     * Set the SCORE.
     * @param SCORE An int value containing the value for SCORE.
     */
    public static void setSCORE(int SCORE) {
        HighScore.SCORE = SCORE;
    }
}
