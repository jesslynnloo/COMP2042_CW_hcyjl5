package game.model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * This is the Brick class.
 */
abstract public class Brick  {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;





    private static Random rnd;

    private String name;
    private Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * Class constructor.
     * @param name The name of the brick.
     * @param pos The position of the brick.
     * @param size The size of the brick.
     * @param border The border color of the brick.
     * @param inner The inner color of the brick.
     * @param strength The strength of the brick.
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * An abstract method that does not have any implementation.
     * @param pos The position of the brick.
     * @param size The size of the brick.
     * @return The brick shape.
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * If the brick is already broken, return false.
     * Else, call the impact() and return broken.
     * @param point The position of the ball impact with the brick.
     * @param dir An int value that represents the direction of brick that the ball impact with.
     * @return A boolean value containing the value of broken.
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * An abstract method that does not have any implementation.
     * @return The brick shape.
     */
    public abstract Shape getBrick();


    /**
     * Get the border color of the brick.
     * @return A Color value of the border color of the brick.
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * Get the inner color of the brick.
     * @return A Color value of the inner color of the brick.
     */
    public Color getInnerColor(){
        return inner;
    }


    /**
     * Find the direction of impact between ball and the brick.
     * @param b The Ball object.
     * @return An int value that represents the direction of brick that the ball impact with.
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Get the value of broken.
     * @return A boolean value of broken.
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * Repair the brick to its original state.
     * Set the broken variable to false and set the strength of the brick to its full strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }



    /**
     * Minus the strength of the brick by 1 and check if the brick is broken.
     * Update the broken variable.
     * If the strength of the brick is 0, then broken equals to true.
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    /**
     * Get the brick face of the brick.
     * @return A Shape value of the brickFace of the brick.
     */
    public Shape getBrickFace() {
        return brickFace;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}





