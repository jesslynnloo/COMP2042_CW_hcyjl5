package test.controller;

import game.controller.TimerTaskController;
import game.model.Player;
import game.model.Wall;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class TimerTaskControllerTest {
    TimerTaskController timerTaskController = new TimerTaskController();
    Wall wall = new Wall(new Rectangle(0,0,600,450),30,3,6/2,new Point(300,430));

    @Test
    void run() {
        Player.setPlayerFaceWidth(100);
        timerTaskController.run();
        assertEquals(80, Player.getPlayerFaceWidth());

        Player.setPlayerFaceWidth(80);
        timerTaskController.run();
        assertEquals(70, Player.getPlayerFaceWidth());

        Player.setPlayerFaceWidth(70);
        timerTaskController.run();
        assertEquals(70, Player.getPlayerFaceWidth());
    }
}