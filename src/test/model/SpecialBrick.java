package test.model;

import test.controller.PlayerController;

import java.awt.*;
import java.awt.geom.Point2D;

public class SpecialBrick extends Brick{

    private static final String NAME = "Special Brick";
    private static final Color DEF_INNER = new Color(144, 238, 144);
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int SPECIAL_BRICK_STRENGTH = 1;
    private PlayerController playerController;

    public SpecialBrick(Point point, Dimension size) {
        super(NAME,point,size,DEF_BORDER,DEF_INNER,SPECIAL_BRICK_STRENGTH);
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
            playerController = new PlayerController(Wall.getPlayer());
            playerController.extendPlayer(200,10);
        }
        return super.isBroken();
    }

    @Override
    public Shape getBrick() {
        return super.getBrickFace();
    }
}
