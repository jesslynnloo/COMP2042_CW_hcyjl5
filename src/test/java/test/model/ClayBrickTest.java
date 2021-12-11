package test.model;

import game.model.Ball;
import game.model.ClayBrick;
import game.model.Crack;
import game.model.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    ClayBrick clayBrick = new ClayBrick(new Point(0,0), new Dimension(6,2));

    @Test
    void setImpact() {
        assertTrue(clayBrick.setImpact(new Point(3,1), Crack.DOWN));
    }

    @Test
    void getBorderColor() {
        assertEquals("java.awt.Color[r=128,g=128,b=128]", clayBrick.getBorderColor().toString());
    }

    @Test
    void getInnerColor() {
        assertEquals("java.awt.Color[r=124,g=23,b=23]", clayBrick.getInnerColor().toString());
    }

    @Test
    void findImpact() {
        Ball b = new RubberBall(new Point(300,430));
        assertEquals(0, clayBrick.findImpact(b));

        Ball b1 = new RubberBall(new Point(0,1));
        assertEquals(300, clayBrick.findImpact(b1));
    }

    @Test
    void isBroken() {
        assertFalse(clayBrick.isBroken());
    }

    @Test
    void repair() {
        clayBrick.repair();
        assertEquals(new Rectangle(new Point(0,0), new Dimension(6,2)), clayBrick.getBrickFace());
    }

    @Test
    void impact() {
        clayBrick.impact();
        assertEquals(0, clayBrick.getStrength());
        assertTrue(clayBrick.isBroken());
    }

    @Test
    void getBrickFace() {
        assertEquals(new Rectangle(0,0,6,2), clayBrick.getBrickFace());
    }

    @Test
    void getStrength() {
        clayBrick.setStrength(1);
        assertEquals(1, clayBrick.getStrength());
    }

    @Test
    void setStrength() {
        clayBrick.setStrength(1);
        assertEquals(1, clayBrick.getStrength());
    }

    @Test
    void makeBrickFace() {
        Point p = new Point(0,0);
        Dimension size = new Dimension(6,2);
        assertEquals(new Rectangle(p,size), clayBrick.makeBrickFace(p,size));
    }

    @Test
    void getBrick() {
        assertEquals(new Rectangle(0,0,6,2), clayBrick.getBrick());
    }
}