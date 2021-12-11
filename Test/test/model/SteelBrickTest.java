package test.model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class SteelBrickTest {
    SteelBrick steelBrick = new SteelBrick(new Point(0,0), new Dimension(6,2));

    @Test
    void setImpact() {
        if(steelBrick.getRnd().nextDouble() < SteelBrick.STEEL_PROBABILITY) {
            assertTrue(steelBrick.setImpact(new Point(3, 1), Crack.DOWN));
        }
        else{
            assertFalse(steelBrick.setImpact(new Point(3,1), Crack.DOWN));
        }
    }

    @Test
    void getBorderColor() {
        assertEquals("java.awt.Color[r=0,g=0,b=0]", steelBrick.getBorderColor().toString());
    }

    @Test
    void getInnerColor() {
        assertEquals("java.awt.Color[r=0,g=0,b=0]", steelBrick.getBorderColor().toString());
    }

    @Test
    void findImpact() {
        Ball b = new RubberBall(new Point(300,430));
        assertEquals(0, steelBrick.findImpact(b));

        Ball b1 = new RubberBall(new Point(0,1));
        assertEquals(300, steelBrick.findImpact(b1));
    }

    @Test
    void isBroken() {
        assertFalse(steelBrick.isBroken());
    }

    @Test
    void repair() {
        steelBrick.repair();
        assertEquals(new Rectangle(new Point(0,0), new Dimension(6,2)), steelBrick.getBrickFace());
    }

    @Test
    void impact() {
        steelBrick.impact();
        if(steelBrick.getRnd().nextDouble() < SteelBrick.STEEL_PROBABILITY) {
            assertEquals(0, steelBrick.getStrength());
            assertTrue(steelBrick.isBroken());
        }
        else{
            assertEquals(1, steelBrick.getStrength());
            assertFalse(steelBrick.isBroken());
        }
    }

    @Test
    void getBrickFace() {
        assertEquals(new Rectangle(0,0,6,2), steelBrick.getBrickFace());
    }

    @Test
    void getStrength() {
        steelBrick.setStrength(1);
        assertEquals(1, steelBrick.getStrength());
    }

    @Test
    void setStrength() {
        steelBrick.setStrength(1);
        assertEquals(1, steelBrick.getStrength());
    }

    @Test
    void makeBrickFace() {
        Point p = new Point(0,0);
        Dimension size = new Dimension(6,2);
        assertEquals(new Rectangle(p,size), steelBrick.makeBrickFace(p,size));
    }

    @Test
    void getBrick() {
        assertEquals(new Rectangle(0,0,6,2), steelBrick.getBrick());
    }
}