package test.model;

import test.controller.HighScoreController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class WoodenBrick extends Brick {

    private static final String NAME = "Wooden Brick";
    private static final Color DEF_INNER = new Color(100,70,36);
    private static final Color DEF_BORDER = DEF_INNER.darker();
    private static final int WOODEN_STRENGTH = 1;
    private static final double WOODEN_PROBABILITY = 0.6;
    private static final int SCORE_FOR_WOODEN_BRICK = 15;

    private Random rnd;
    private Shape brickFace;

    private HighScoreController highScoreController;

    public WoodenBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,WOODEN_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
        highScoreController = new HighScoreController();
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return brickFace;
    }

    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();

        if(super.isBroken()) {
            highScoreController.updateScore(SCORE_FOR_WOODEN_BRICK);
        }
        return super.isBroken();
    }

    public void impact(){
        if(rnd.nextDouble() < WOODEN_PROBABILITY){
            super.impact();
        }
    }

}
