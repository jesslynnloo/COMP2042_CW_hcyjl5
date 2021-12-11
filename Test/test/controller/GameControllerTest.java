package test.controller;

import game.controller.GameController;
import game.controller.HighScoreController;
import org.junit.jupiter.api.Test;
import game.model.HighScore;
import game.model.Player;
import game.model.Wall;
import game.view.GameFrame;
import game.view.GameView;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    GameFrame gameFrame = new GameFrame();
    GameView gameView = new GameView(gameFrame);
    GameController gameController = new GameController(gameView);
    HighScoreController highScoreController = new HighScoreController();

    @Test
    void onLostFocus() {
        gameController.onLostFocus();
        assertEquals("Focus Lost", gameController.getView().getMessage());
    }


    @Test
    void isShowPauseMenu() {
        gameController.setShowPauseMenu(true);
        assertTrue(gameController.isShowPauseMenu());
    }

    @Test
    void setShowPauseMenu() {
        gameController.setShowPauseMenu(true);
        assertTrue(gameController.isShowPauseMenu());
    }

    @Test
    void repainting() {
        gameController.repainting();
        assertNotNull(Wall.getPlayer());
    }

    @Test
    void reset() {
        gameController.reset();
        assertEquals(new Rectangle(new Point(225,430), new Dimension(150,10)), Player.getPlayerFace());
        assertEquals(new Ellipse2D.Double(295, 425, 10, 10), gameView.getWall().getBall().getBallFace());
        assertFalse(gameView.getWall().isBallLost());

        assertEquals(3, gameView.getWall().getBallCount());

        assertEquals(0, HighScore.getSCORE());

        Point point = new Point(300,430);
        Point p = new Point((int)(point.getX() - (150 / 2)), (int)point.getY());
        Wall.getPlayer().resetPlayerFace();
        assertEquals(new Rectangle(p, new Dimension(150,10)), Player.getPlayerFace());
    }
}