package test.controller;

import game.controller.HighScoreViewController;
import org.junit.jupiter.api.Test;
import game.view.GameFrame;
import game.view.HighScoreView;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreViewControllerTest {
    GameFrame gameFrame = new GameFrame();
    HighScoreView highScoreView = new HighScoreView(gameFrame);
    HighScoreViewController highScoreViewController = new HighScoreViewController(highScoreView);

    @Test
    void isRestartClicked() {
        highScoreViewController.setRestartClicked(true);
        assertTrue(highScoreViewController.isRestartClicked());
    }

    @Test
    void setRestartClicked() {
        highScoreViewController.setRestartClicked(true);
        assertTrue(highScoreViewController.isRestartClicked());
    }

    @Test
    void isHomeMenuClicked() {
        highScoreViewController.setHomeMenuClicked(true);
        assertTrue(highScoreViewController.isHomeMenuClicked());
    }

    @Test
    void setHomeMenuClicked() {
        highScoreViewController.setHomeMenuClicked(true);
        assertTrue(highScoreViewController.isHomeMenuClicked());
    }

    @Test
    void isExitClicked() {
        highScoreViewController.setExitClicked(true);
        assertTrue(highScoreViewController.isExitClicked());
    }

    @Test
    void setExitClicked() {
        highScoreViewController.setExitClicked(true);
        assertTrue(highScoreViewController.isExitClicked());
    }
}