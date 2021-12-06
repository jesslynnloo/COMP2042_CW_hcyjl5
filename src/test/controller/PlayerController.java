package test.controller;

import test.model.Player;

public class PlayerController {
    private Player player;


    public PlayerController(Player player) {
        this.player = player;
    }

    public void adjustPlayer(int extendedWidth, int height) {
        player.updatePlayerFace(extendedWidth,height);
    }

}
