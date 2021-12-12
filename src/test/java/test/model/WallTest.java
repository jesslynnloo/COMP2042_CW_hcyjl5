package test.model;

import game.model.*;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));

    @Test
    void move() {
        Wall.getPlayer().setMoveAmount(10);
        Wall.getPlayer().move();
        assertEquals(new Rectangle(new Point(235,430), new Dimension(150, 10)), Player.getPlayerFace());

        wall.getBall().setXSpeed(10);
        wall.getBall().setYSpeed(10);
        wall.getBall().move();
        assertEquals(new Ellipse2D.Double(305,435,10,10), wall.getBall().getBallFace());
    }

    @Test
    void findImpacts() {
        Wall.getPlayer().moveTo(new Point(300, 430));
        wall.getBall().moveTo(new Point(300,430));
        wall.getBall().setYSpeed(10);
        wall.findImpacts();
        assertEquals(-10, wall.getBall().getSpeedY());

    }

    @Test
    void getBrickCount() {
        wall.setBrickCount(30);
        assertEquals(30, wall.getBrickCount());
    }

    @Test
    void getBallCount() {
        wall.setBallCount(3);
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void isBallLost() {
        wall.setBallLost(false);
        assertFalse(wall.isBallLost());
    }

    @Test
    void ballReset() {
        wall.ballReset();
        assertEquals(new Rectangle(new Point(225,430), new Dimension(150,10)), Wall.getPlayer().getPlayerFace());
        assertEquals(new Ellipse2D.Double(295, 425, 10, 10), wall.getBall().getBallFace());
        assertFalse(wall.isBallLost());
    }

    @Test
    void ballEnd() {
        wall.setBallCount(3);
        assertFalse(wall.ballEnd());
    }

    @Test
    void isDone() {
        wall.setBrickCount(30);
        assertFalse(wall.isDone());
    }

    @Test
    void setBallXSpeed() {
        wall.setBallXSpeed(5);
        assertEquals(5, wall.getBall().getSpeedX());
    }

    @Test
    void setBallYSpeed() {
        wall.setBallYSpeed(10);
        assertEquals(10, wall.getBall().getSpeedY());
    }

    @Test
    void resetBallCount() {
        wall.resetBallCount();
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void getBricks() {
        Brick[] bricks = new CementBrick[30];
        wall.setBricks(bricks);
        assertEquals(bricks, wall.getBricks());
    }

    @Test
    void getBall() {
        Ball ball = new RubberBall(new Point(300, 430));
        wall.setBall(ball);
        assertEquals(ball.toString(), wall.getBall().toString());
    }

    @Test
    void getPlayer() {
        Player player = new Player(new Point(300,430),150,10, new Rectangle(0,0,600,450));
        Wall.setPlayer(player);
        assertEquals(player, Wall.getPlayer());
    }

    @Test
    void setBrickCount() {
        wall.setBrickCount(30);
        assertEquals(30, wall.getBrickCount());
    }

    @Test
    void setBallCount() {
        wall.setBallCount(3);
        assertEquals(3, wall.getBallCount());
    }

    @Test
    void setBallLost() {
        wall.setBallLost(false);
        assertFalse(wall.isBallLost());
    }

    @Test
    void setBricks() {
        Brick[] bricks = new CementBrick[20];
        wall.setBricks(bricks);
        assertEquals(bricks, wall.getBricks());
    }

    @Test
    void setBall() {
        Ball ball = new RubberBall(new Point(300, 430));
        wall.setBall(ball);
        assertEquals(ball.toString(), wall.getBall().toString());
    }

    @Test
    void setPlayer() {
        Player player = new Player(new Point(300,430),150,10, new Rectangle(0,0,600,450));
        Wall.setPlayer(player);
        assertEquals(player, Wall.getPlayer());
    }
}