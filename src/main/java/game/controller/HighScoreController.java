package game.controller;

import game.model.HighScore;

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
        boolean score = file.createNewFile();
    }

    /**
     * Read the highest score from the file and save the highest score to the HIGHSCORE variable in HighScore class.
     */
    public void readHighscoreFromFile () {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null)
            {
                try {
                    int score = Integer.parseInt(line.trim());
                    if (score > HighScore.getHighScore())
                    {
                        HighScore.setHighScore(score);
                    }
                } catch (NumberFormatException e1) {
                    System.err.println("This score is invalid: " + line);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (IOException ex) {
            System.err.println("Error occurred.");
        }
    }

    /**
     * Write the user's score to the file.
     */
    public void writeScoreToFile () {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            if(file.length() == 0){
                writer.append("" + HighScore.getSCORE());
                writer.close();
            }
            else {
                writer.newLine();
                writer.append("" + HighScore.getSCORE());
                writer.close();
            }

        } catch (IOException ex1) {
            System.out.printf("Error occurred.");
        }
    }
}
