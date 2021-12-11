package test.controller;

import test.model.Player;
import test.model.Wall;

import java.util.TimerTask;

/**
 * This is the TimerTaskController.
 */
public class TimerTaskController extends TimerTask {

    /**
     * The task that is carried out at a fixed rate.
     */
    @Override
    public void run() {
        int shortenLength;
        if(Player.getPlayerFaceWidth() >= 90) {
            shortenLength = 20;
        }
        else if(Player.getPlayerFaceWidth() > 70) {
            shortenLength = Player.getPlayerFaceWidth() - 70;
        }
        else{
            shortenLength = 0;
        }

        PlayerController playerController = new PlayerController(Wall.getPlayer());
        playerController.adjustPlayer(Player.getPlayerFaceWidth() - shortenLength,10);
    }
}
