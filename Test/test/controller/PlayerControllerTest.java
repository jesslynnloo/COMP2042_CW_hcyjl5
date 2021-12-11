package test.controller;

import org.junit.jupiter.api.Test;
import test.model.Player;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    Point point = new Point(300,430);
    Player player = new Player(point,150,10, new Rectangle(0,0,600,450));
    PlayerController playerController = new PlayerController(player);

    @Test
    void adjustPlayer() {
        Point p = new Point((int)(point.getX() - (200 / 2)), (int)point.getY());
        playerController.adjustPlayer(200,15);
        assertEquals(new Rectangle(p, new Dimension(200, 15)), Player.getPlayerFace());
    }
}