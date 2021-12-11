package test.model;

import test.model.Brick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This is the Crack class.
 */
public class Crack{

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;



    private GeneralPath crack;

    private int crackDepth;
    private int steps;
    private Brick brick;
    private Random random;


    /**
     * Class constructor.
     * @param brick The brick object.
     * @param crackDepth An int value containing the crack depth value.
     * @param steps An int value containing the steps value.
     */
    public Crack(Brick brick, int crackDepth, int steps){

        this.brick = brick;
        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;

    }


    /**
     * Draw the crack.
     * @return A GeneralPath value of crack.
     */
    public GeneralPath draw(){

        return crack;
    }

    /**
     * Reset the crack.
     */
    public void reset(){
        crack.reset();
    }

    /**
     * Make a randomly generated path from the impact point in a direction.
     * @param point The impact point on the brick.
     * @param direction The direction of the crack.
     */
    protected void makeCrack(Point2D point, int direction){
        Rectangle bounds = brick.getBrickFace().getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    /**
     * Make a randomly generated path from the start point to the end point.
     * @param start The start point.
     * @param end The end point.
     */
    private void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            if(inMiddle(i,CRACK_SECTIONS,steps))
                y += jumps(jump,JUMP_PROBABILITY);

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * Generate a random number between bound and -bound.
     * @param bound The bound value.
     * @return An int value of the random generated integer.
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        random = new Random();
        return random.nextInt(n) - bound;
    }


    /**
     * Check if it is in the middle.
     * @param i An arbitrary value.
     * @param steps The crack sections of the crack.
     * @param divisions The steps of the crack.
     * @return A boolean value if it is in the middle.
     */
    private boolean inMiddle(int i,int steps,int divisions){
        int low = (steps / divisions);
        int up = low * (divisions - 1);

        return  (i > low) && (i < up);
    }

    /**
     * Create a random double value.
     * If the random value is larger than the probability, then return random number between bound and -bound.
     * Else, return 0.
     * @param bound The bound value.
     * @param probability The probability.
     * @return An int value which is either 0 or random numbers between bound and -bound.
     */
    private int jumps(int bound,double probability){
        random = new Random();
        if(random.nextDouble() > probability)
            return randomInBounds(bound);
        return  0;

    }

    /**
     * Make random point either horizontally or vertically.
     * @param from The start point.
     * @param to The end point.
     * @param direction The direction, either horizontally or vertically.
     * @return A Point value which contains the value of the random made point.
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;
        random = new Random();

        switch(direction){
            case HORIZONTAL:
                pos = random.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = random.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}