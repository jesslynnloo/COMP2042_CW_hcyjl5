package test.model;

import test.controller.HighScoreController;
import test.model.Brick;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;


/**
 * Created by filippo on 04/09/16.
 *
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;
    private static final int SCORE_FOR_CLAY_BRICK = 10;

    private HighScoreController highScoreController;

    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
        highScoreController = new HighScoreController();
    }

    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

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

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }


}
