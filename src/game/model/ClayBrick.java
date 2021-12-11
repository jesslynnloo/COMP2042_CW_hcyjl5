package game.model;

import game.controller.HighScoreController;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * This is the ClayBrick class which extends Brick.
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;
    private static final int SCORE_FOR_CLAY_BRICK = 10;

    private HighScoreController highScoreController;

    /**
     * Class constructor.
     * Create a HighScoreController object.
     * @param point The position of the clay brick.
     * @param size The size of the clay brick.
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
        highScoreController = new HighScoreController();
    }

    /**
     * Make the shape of the brick.
     * @param pos  The position of the brick.
     * @param size The size of the brick.
     * @return The clay brick shape.
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Perform actions when the ball is in impact with the clay brick.
     * If the clay brick is broken, return false.
     * Call the impact() method from the Brick class, minus the strength of the clay brick by 1
     * and check if the clay brick is broken.
     * If the clay brick is broken, then update the score by adding the score with the score for destroying a clay brick,
     * then return broken.
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
            highScoreController.updateScore(SCORE_FOR_CLAY_BRICK);
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


}
