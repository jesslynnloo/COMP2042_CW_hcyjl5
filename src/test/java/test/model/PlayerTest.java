package test.model;

import game.model.Ball;
import game.model.Player;
import game.model.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Point point = new Point(300,430);
    Player player = new Player(point,150,10, new Rectangle(0,0,600,450));


    @Test
    void makeRectangle() {
        Point p = new Point((int)(point.getX() - (150 / 2)),(int)point.getY());
        assertEquals(new Rectangle(p, new Dimension(150,10)), player.makeRectangle(150,10));

    }

    @Test
    void updatePlayerFace() {
        Point p = new Point((int)(point.getX() - (200 / 2)), (int)point.getY());
        player.updatePlayerFace(200,10);
        assertEquals(new Rectangle(p, new Dimension(200, 10)), player.getPlayerFace());
    }

    @Test
    void resetPlayerFace() {
        Point p = new Point((int)(point.getX() - (150 / 2)), (int)point.getY());
        player.resetPlayerFace();
        assertEquals(new Rectangle(p, new Dimension(150,10)), player.getPlayerFace());
    }

    @Test
    void impact() {
        Ball b = new RubberBall(new Point(300,430));
        assertTrue(player.impact(b));
    }

    @Test
    void move() {
        player.setMoveAmount(10);
        player.move();
        assertEquals(new Rectangle(new Point(235,430), new Dimension(150, 10)), Player.getPlayerFace());

    }

    @Test
    void moveLeft() {
        player.moveLeft();
        assertEquals(-5, player.getMoveAmount());
    }

    @Test
    void moveRight() {
        player.moveRight();
        assertEquals(5, player.getMoveAmount());
    }

    @Test
    void stop() {
        player.stop();
        assertEquals(0, player.getMoveAmount());
    }

    @Test
    void getPlayerFace() {
        assertEquals(new Rectangle(new Point(225, 430), new Dimension(150,10)), Player.getPlayerFace());
    }

    @Test
    void getPlayerFaceWidth() {
        assertEquals(150, Player.getPlayerFaceWidth());
    }

    @Test
    void setPlayerFaceWidth() {
        Player.setPlayerFaceWidth(200);
        assertEquals(200, Player.getPlayerFaceWidth());
    }

    @Test
    void moveTo() {
        Point point = new Point(300,430);
        player.moveTo(point);
        assertEquals(new Rectangle(new Point(225,430), new Dimension(150,10)), Player.getPlayerFace());

    }

    @Test
    void getMoveAmount() {
        player.setMoveAmount(5);
        assertEquals(5, player.getMoveAmount());
    }

    @Test
    void setMoveAmount() {
        player.setMoveAmount(5);
        assertEquals(5, player.getMoveAmount());
    }
}