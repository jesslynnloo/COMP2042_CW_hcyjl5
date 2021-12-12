package game.model;

import game.controller.HighScoreController;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;


/**
 * This is the CementBrick class which extends Brick class.
 */
public class CementBrick extends Brick {

    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = new Color(147, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;
    private static final int SCORE_FOR_CEMENT_BRICK = 20;

    private Crack crack;
    private Shape brickFace;
    private HighScoreController highScoreController;

    /**
     * Class constructor.
     * Initialize a Crack object.
     * Create a HighScoreController object.
     * @param point The position of the cement brick.
     * @param size The size of the cement brick.
     */
    public CementBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(this, DEF_CRACK_DEPTH,DEF_STEPS);
        brickFace = super.getBrickFace();
        highScoreController = new HighScoreController();
    }

    /**
     * Make the shape of the brick.
     * @param pos  The position of the brick.
     * @param size The size of the brick.
     * @return The cement brick shape.
     */
    @Override
    public Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * Perform actions when the ball is in impact with the cement brick.
     * If the cement brick is broken, return false.
     * Call the impact() method from the Brick class, minus the strength of the cement brick by 1
     * and check if the cement brick is broken.
     * If the cement brick is not broken, make crack on the cement brick and draw the crack on the cement brick,
     * then return false.
     * If the cement brick is broken, then update the score by adding the score with the score for destroying a cement brick,
     * then return true.
     * @param point The position of the ball impact with the brick.
     * @param dir   An int value that represents the direction of brick that the ball impact with.
     * @return A boolean value which represents if the brick is broken.
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
            crack.makeCrack(point,dir);
            updateBrick();
            return false;
        }
        highScoreController.updateScore(SCORE_FOR_CEMENT_BRICK);
        return true;
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
     * Draw crack on the brick.
     */
    private void updateBrick(){
        if(!super.isBroken()){
            GeneralPath gp = crack.draw();
            gp.append(super.getBrickFace(),false);
            brickFace = gp;
        }
    }

    /**
     * Repair the brick to its original state.
     * Set the broken variable to false and set the strength of the brick to its full strength.
     * Reset the crack and brickFace.
     */
    public void repair(){
        super.repair();
        crack.reset();
        brickFace = super.getBrickFace();
    }
}
