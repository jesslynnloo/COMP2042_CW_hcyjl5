package test.model;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CrackTest {
    CementBrick cementBrick = new CementBrick(new Point(0,0), new Dimension(6,2));
    Crack crack = new Crack(cementBrick, 1,35);

    @Test
    void draw() {
        crack.draw();
        assertNotNull(crack);
    }

    @Test
    void reset() {
        crack.reset();
        assertNotNull(crack);
    }

    @Test
    void makeCrack() {
        Point point = new Point(3,1);
        int dir = Crack.DOWN;
        cementBrick.setImpact(point, dir);
        crack.makeCrack(point,dir);
        assertNotNull(crack);
    }
}