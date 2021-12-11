package game.controller;

import game.model.Player;

/**
 * This is the PlayerController class.
 */
public class PlayerController {
    private Player player;


    /**
     * Class constructor.
     * @param player The Player object.
     */
    public PlayerController(Player player) {
        this.player = player;
    }

    /**
     * Call the updatePlayerFace method in the player class to make player face based on the width and height passed.
     * @param width The new width that is used to update the player face.
     * @param height The new height that is used to update the player face.
     */
    public void adjustPlayer(int width, int height) {
        player.updatePlayerFace(width,height);
    }

}
