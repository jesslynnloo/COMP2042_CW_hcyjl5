package test.controller;

import test.model.HighScore;
import test.view.HighScoreView;

import java.io.*;

public class HighScoreController {
    File file;


    public HighScoreController() {
        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void createFile () throws IOException {
        file  = new File("score.txt");
        boolean result = file.createNewFile();
    }

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
