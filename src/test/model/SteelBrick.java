/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test.model;

import test.controller.HighScoreController;
import test.model.Brick;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * This is the SteelBrick class which extends Brick class.
 */
public class SteelBrick extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    public static final double STEEL_PROBABILITY = 0.4;
    private static final int SCORE_FOR_STEEL_BRICK = 30;



    private Random rnd;
    private Shape brickFace;

    private HighScoreController highScoreController;

    /**
     * Class constructor.
     * Create a HighScoreController object.
     * @param point The position of the steel brick.
     * @param size The size of the steel brick.
     */
    public SteelBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        rnd = new Random();
        brickFace = super.getBrickFace();
        highScoreController = new HighScoreController();
    }


    /**
     * Make the shape of the brick.
     * @param pos  The position of the brick.
     * @param size The size of the brick.
     * @return The steel brick shape.
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
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
     * Perform actions when the ball is in impact with the steel brick.
     * If the steel brick is broken, return false.
     * Call the impact() method.
     * If the random number generated is less than the STEEL_PROBABILITY, minus the strength of the steel brick by 1
     * and check if the steel brick is broken.
     * If the steel brick is broken, then update the score by adding the score with the score for destroying a steel brick,
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
            highScoreController.updateScore(SCORE_FOR_STEEL_BRICK);
        }
        return super.isBroken();
    }

    /**
     * If the random number generated is less than the STEEL_PROBABILITY, call the impact method in the Brick class.
     * Else, do nothing.
     */
    public void impact(){
        if(rnd.nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

    public Random getRnd() {
        return rnd;
    }

}
