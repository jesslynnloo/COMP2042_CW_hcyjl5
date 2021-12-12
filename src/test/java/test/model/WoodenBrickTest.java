package test.model;

import game.model.Ball;
import game.model.RubberBall;
import game.model.WoodenBrick;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WoodenBrickTest {
    WoodenBrick woodenBrick = new WoodenBrick(new Point(0,0), new Dimension(6,2));

    @Test
    void getBorderColor() {
        assertEquals("java.awt.Color[r=70,g=49,b=25]", woodenBrick.getBorderColor().toString());
    }

    @Test
    void getInnerColor() {
        assertEquals("java.awt.Color[r=70,g=49,b=25]", woodenBrick.getBorderColor().toString());
    }

    @Test
    void findImpact() {
        Ball b = new RubberBall(new Point(300,430));
        assertEquals(0, woodenBrick.findImpact(b));

        Ball b1 = new RubberBall(new Point(0,1));
        assertEquals(300, woodenBrick.findImpact(b1));
    }

    @Test
    void isBroken() {
        assertFalse(woodenBrick.isBroken());
    }

    @Test
    void repair() {
        woodenBrick.repair();
        assertEquals(new Rectangle(new Point(0,0), new Dimension(6,2)), woodenBrick.getBrickFace());
    }

    @Test
    void getBrickFace() {
        assertEquals(new Rectangle(0,0,6,2), woodenBrick.getBrickFace());
    }

    @Test
    void getStrength() {
        woodenBrick.setStrength(1);
        assertEquals(1, woodenBrick.getStrength());
    }

    @Test
    void setStrength() {
        woodenBrick.setStrength(1);
        assertEquals(1, woodenBrick.getStrength());
    }

    @Test
    void makeBrickFace() {
        Point p = new Point(0,0);
        Dimension size = new Dimension(6,2);
        assertEquals(new Rectangle(p,size), woodenBrick.makeBrickFace(p,size));
    }

    @Test
    void getBrick() {
        assertEquals(new Rectangle(0,0,6,2), woodenBrick.getBrick());
    }
}