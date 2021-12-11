package game.model;

import game.controller.HighScoreController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This is the WoodenBrick class which extends Brick class.
 */
public class WoodenBrick extends Brick {

    private static final String NAME = "Wooden Brick";
    private static final Color DEF_INNER = new Color(100,70,36);
    private static final Color DEF_BORDER = DEF_INNER.darker();
    private static final int WOODEN_STRENGTH = 1;
    public static final double WOODEN_PROBABILITY = 0.6;
    private static final int SCORE_FOR_WOODEN_BRICK = 15;

    private Random rnd;
    private Shape brickFace;

    private HighScoreController highScoreController;

    /**
     * Class constructor.
     * Create a HighScoreController object.
     * @param point The position of the wooden brick.
     * @param size The size of the wooden brick.
     */
    public WoodenBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,WOODEN_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
        highScoreController = new HighScoreController();
    }


    /**
     * Make the shape of the brick.
     * @param pos  The position of the brick.
     * @param size The size of the brick.
     * @return The wooden brick shape.
     */
    @Override
    public Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Get the value of the brickFace.
     * @return A Shape value of brickFace.
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * Perform actions when the ball is in impact with the wooden brick.
     * If the wooden brick is broken, return false.
     * Call the impact() method.
     * If the random number generated is less than the WOODEN_PROBABILITY, minus the strength of the wooden brick by 1
     * and check if the wooden brick is broken.
     * If the wooden brick is broken, then update the score by adding the score with the score for destroying a wooden brick,
     * then return broken.
     * @param point The position of the ball impact with the brick.
     * @param dir   An int value that represents the direction of brick that the ball impact with.
     * @return A boolean value which represents if the brick is broken.
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();

        if(super.isBroken()) {
            highScoreController.updateScore(SCORE_FOR_WOODEN_BRICK);
        }
        return super.isBroken();
    }

    /**
     * If the random number generated is less than the WOODEN_PROBABILITY, call the impact method in the Brick class.
     * Else, do nothing.
     */
    public void impact(){
        if(rnd.nextDouble() < WOODEN_PROBABILITY){
            super.impact();
        }
    }

    public Random getRnd() {
        return rnd;
    }

}
