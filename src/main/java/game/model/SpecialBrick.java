package game.model;

import game.controller.PlayerController;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * This is the SpecialBrick class which extends Brick class.
 */
public class SpecialBrick extends Brick{

    private static final String NAME = "Special Brick";
    private static final Color DEF_INNER = new Color(144, 238, 144);
    private static final Color DEF_BORDER = new Color(255,255,180);
    private static final int SPECIAL_BRICK_STRENGTH = 1;

    private static int extendedWidth;

    /**
     * Class constructor.
     * @param point The position of the special brick.
     * @param size The size of the special brick.
     */
    public SpecialBrick(Point point, Dimension size) {
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SPECIAL_BRICK_STRENGTH);
    }

    /**
     * Make the shape of the brick.
     * @param pos  The position of the brick.
     * @param size The size of the brick.
     * @return The special brick shape.
     */
    @Override
    public Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Perform actions when the ball is in impact with the special brick.
     * If the special brick is broken, return false.
     * Call the impact() method from the Brick class, minus the strength of the special brick by 1
     * and check if the special brick is broken.
     * If the special brick is broken, then extend the player by 50.
     * @param point The position of the ball impact with the brick.
     * @param dir   An int value that represents the direction of brick that the ball impact with.
     * @return A boolean value which represents if the brick is broken.
     */
    @Override
    public  boolean setImpact(Point2D point , int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(super.isBroken()) {
            extendedWidth += 50;
            PlayerController playerController = new PlayerController(Wall.getPlayer());
            playerController.adjustPlayer(Player.getPlayerFaceWidth() + extendedWidth,10);
        }
        return super.isBroken();
    }

    /**
     * Get the value of the brickFace.
     * @return A Shape value of brickFace.
     */
    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }

    /**
     * Set the value of extendedWidth.
     * @param extendedWidth An int value containing the value of extendedWidth.
     */
    public static void setExtendedWidth(int extendedWidth) {
        SpecialBrick.extendedWidth = extendedWidth;
    }

    /**
     * Get the value of the extendedWidth.
     * @return An int value of the extendedWidth.
     */
    public static int getExtendedWidth() {
        return extendedWidth;
    }
}
