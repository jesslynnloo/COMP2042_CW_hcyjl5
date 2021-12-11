package test.model;

import game.model.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {
    RubberBall rubberBall = new RubberBall(new Point(300,430));


    @Test
    void move() {

        rubberBall.setXSpeed(10);
        rubberBall.setYSpeed(10);
        rubberBall.move();
        assertEquals(new Ellipse2D.Double(305,435,10,10), rubberBall.getBallFace());
    }

    @Test
    void setSpeed() {
        rubberBall.setSpeed(10,20);
        assertEquals(10, rubberBall.getSpeedX());
        assertEquals(20, rubberBall.getSpeedY());
    }

    @Test
    void setXSpeed() {
        rubberBall.setXSpeed(20);
        assertEquals(20, rubberBall.getSpeedX());
    }

    @Test
    void setYSpeed() {
        rubberBall.setYSpeed(20);
        assertEquals(20, rubberBall.getSpeedY());
    }

    @Test
    void reverseX() {
        rubberBall.setXSpeed(10);
        rubberBall.reverseX();
        assertEquals(-10, rubberBall.getSpeedX());
    }

    @Test
    void reverseY() {
        rubberBall.setYSpeed(20);
        rubberBall.reverseY();
        assertEquals(-20, rubberBall.getSpeedY());
    }

    @Test
    void getBorderColor() {
        assertEquals("java.awt.Color[r=255,g=219,b=88]", rubberBall.getInnerColor().toString());
    }

    @Test
    void getInnerColor() {
        assertEquals("java.awt.Color[r=255,g=219,b=88]", rubberBall.getInnerColor().toString());
    }

    @Test
    void getPosition() {
        assertEquals(new Point(300,430), rubberBall.getPosition());
    }

    @Test
    void getBallFace() {
        assertEquals(new Ellipse2D.Double(295,425,10,10), rubberBall.getBallFace());
    }

    @Test
    void moveTo() {
        Point point = new Point(300,430);
        rubberBall.moveTo(point);
        assertEquals(new Ellipse2D.Double(295, 425, 10, 10), rubberBall.getBallFace());
    }

    @Test
    void getSpeedX() {
        rubberBall.setXSpeed(10);
        assertEquals(10, rubberBall.getSpeedX());
    }

    @Test
    void getSpeedY() {
        rubberBall.setYSpeed(10);
        assertEquals(10, rubberBall.getSpeedY());
    }

    @Test
    void getUp() {
        assertEquals(new Point(300, 425), rubberBall.getUp());
    }

    @Test
    void getDown() {
        assertEquals(new Point(300, 435), rubberBall.getDown());
    }

    @Test
    void getLeft() {
        assertEquals(new Point(295, 430), rubberBall.getLeft());
    }

    @Test
    void getRight() {
        assertEquals(new Point(305, 430), rubberBall.getRight());
    }

    @Test
    void makeBall() {
        Ellipse2D ball = new Ellipse2D.Double(295,425,10,10);
        Ellipse2D ball1 = (Ellipse2D) rubberBall.makeBall(new Point(300,430), 10, 10);
        assertEquals(ball, ball1);
    }
}