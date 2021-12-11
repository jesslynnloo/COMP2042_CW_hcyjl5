package test.model;

import game.model.*;
import org.junit.jupiter.api.Test;
import game.controller.PlayerController;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class SpecialBrickTest {
    SpecialBrick specialBrick = new SpecialBrick(new Point(0,0), new Dimension(6,2));
    PlayerController playerController = new PlayerController(Wall.getPlayer());

    @Test
    void setImpact() {
        Point point = new Point(300,430);
        Player player = new Player(point,200,10, new Rectangle(0,0,600,450));
        assertEquals(new Rectangle(new Point(200,430), new Dimension(200, 10)), Player.getPlayerFace());
    }

    @Test
    void getBorderColor() {
        assertEquals("java.awt.Color[r=255,g=255,b=180]", specialBrick.getBorderColor().toString());
    }

    @Test
    void getInnerColor() {
        assertEquals("java.awt.Color[r=255,g=255,b=180]", specialBrick.getBorderColor().toString());
    }

    @Test
    void findImpact() {
        Ball b = new RubberBall(new Point(300,430));
        assertEquals(0, specialBrick.findImpact(b));

        Ball b1 = new RubberBall(new Point(0,1));
        assertEquals(300, specialBrick.findImpact(b1));
    }

    @Test
    void isBroken() {
        assertFalse(specialBrick.isBroken());
    }

    @Test
    void repair() {
        specialBrick.repair();
        assertEquals(new Rectangle(new Point(0,0), new Dimension(6,2)), specialBrick.getBrickFace());
    }

    @Test
    void impact() {
        specialBrick.impact();
        assertEquals(0, specialBrick.getStrength());
        assertTrue(specialBrick.isBroken());
    }

    @Test
    void getBrickFace() {
        assertEquals(new Rectangle(0,0,6,2), specialBrick.getBrickFace());
    }

    @Test
    void getStrength() {
        specialBrick.setStrength(1);
        assertEquals(1, specialBrick.getStrength());
    }

    @Test
    void setStrength() {
        specialBrick.setStrength(1);
        assertEquals(1, specialBrick.getStrength());
    }

    @Test
    void makeBrickFace() {
        Point p = new Point(0,0);
        Dimension size = new Dimension(6,2);
        assertEquals(new Rectangle(p,size), specialBrick.makeBrickFace(p,size));
    }

    @Test
    void getBrick() {
        assertEquals(new Rectangle(0,0,6,2), specialBrick.getBrick());
    }

    @Test
    void setExtendedWidth() {
        SpecialBrick.setExtendedWidth(20);
        assertEquals(20, SpecialBrick.getExtendedWidth());
    }

    @Test
    void getExtendedWidth() {
        SpecialBrick.setExtendedWidth(20);
        assertEquals(20, SpecialBrick.getExtendedWidth());
    }
}