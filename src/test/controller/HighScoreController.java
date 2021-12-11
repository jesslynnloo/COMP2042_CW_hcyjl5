package test.controller;

import test.model.HighScore;

import java.io.*;

/**
 * This is the HighScoreController class.
 */
public class HighScoreController {
    File file;


    /**
     * Class constructor.
     * Create file.
     */
    public HighScoreController() {
        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the score in the HighScore class.
     * @param score The score obtained by the user.
     */
    public void updateScore (int score) {
        HighScore.setSCORE(HighScore.getSCORE() + score);
    }

    /**
     * Update high score in the HighScore class if the user's score is higher than the previous high score.
     */
    public void updateHighScore () {
        if (HighScore.getSCORE() > HighScore.getHighScore()) {
            HighScore.setHighScore(HighScore.getSCORE());
        }
    }

    /**
     * Reset score in the HighScore class to 0.
     */
    public void restartScore () {
        HighScore.setSCORE(0);
    }

    /**
     * Create a file named "score.txt" if the file does not exist.
     * @throws IOException If an input or output exception occurred.
     */
    private void createFile () throws IOException {
        file  = new File("score.txt");
        boolean result = file.createNewFile();
    }

    /**
     * Read the highest score from the file and save the highest score to the HIGHSCORE variable in HighScore class.
     */
    public void readHighscoreFromFile () {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)                 // read the score file line by line
            {
                try {
                    int score = Integer.parseInt(line.trim());   // parse each line as an int
                    if (score > HighScore.getHighScore())                       // and keep track of the largest
                    {
                        HighScore.setHighScore(score);
                    }
                } catch (NumberFormatException e1) {
                    // ignore invalid scores
                    //System.err.println("ignoring invalid score: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("ERROR reading scores from file");
        }
    }

    /**
     * Write the user's score to the file.
     */
    public void writeScoreToFile () {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(file, true));
            output.newLine();
            output.append("" + HighScore.getSCORE());
            output.close();

        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
    }
}
