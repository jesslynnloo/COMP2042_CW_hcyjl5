package test.model;

import game.model.Ball;
import game.model.CementBrick;
import game.model.Crack;
import game.model.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static game.model.Brick.DEF_CRACK_DEPTH;
import static game.model.Brick.DEF_STEPS;

class CementBrickTest {
    CementBrick cementBrick = new CementBrick(new Point(0,0), new Dimension(6,2));
//    Ball b = new RubberBall();
    Crack crack =  new Crack(cementBrick, DEF_CRACK_DEPTH,DEF_STEPS);

    @Test
    void setImpact() {
        assertFalse(cementBrick.setImpact(new Point(3,1), Crack.DOWN));
        assertNotNull(crack);
    }

    @Test
    void getBorderColor() {
        assertEquals("java.awt.Color[r=217,g=199,b=175]", cementBrick.getBorderColor().toString());
    }

    @Test
    void getInnerColor() {
        assertEquals("java.awt.Color[r=147,g=147,b=147]", cementBrick.getInnerColor().toString());
    }

    @Test
    void findImpact() {
        Ball b = new RubberBall(new Point(300,430));
        assertEquals(0, cementBrick.findImpact(b));

        Ball b1 = new RubberBall(new Point(0,1));
        assertEquals(300, cementBrick.findImpact(b1));
    }

    @Test
    void isBroken() {
        assertFalse(cementBrick.isBroken());
    }

    //weird//
    @Test
    void repair() {
        cementBrick.repair();
        assertNotNull(crack);
        assertEquals(new Rectangle(new Point(0,0), new Dimension(6,2)), cementBrick.getBrickFace());
    }

    @Test
    void impact() {
        cementBrick.impact();
        assertEquals(1, cementBrick.getStrength());
        assertFalse(cementBrick.isBroken());
    }

    @Test
    void getBrickFace() {
        assertEquals(new Rectangle(0,0,6,2), cementBrick.getBrickFace());
    }

    @Test
    void getStrength() {
        cementBrick.setStrength(2);
        assertEquals(2, cementBrick.getStrength());
    }

    @Test
    void setStrength() {
        cementBrick.setStrength(2);
        assertEquals(2, cementBrick.getStrength());
    }

    @Test
    void makeBrickFace() {
        Point p = new Point(0,0);
        Dimension size = new Dimension(6,2);
        assertEquals(new Rectangle(p,size), cementBrick.makeBrickFace(p,size));
    }

    @Test
    void getBrick() {
        assertEquals(new Rectangle(0,0,6,2), cementBrick.getBrick());
    }

}