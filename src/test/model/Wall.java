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

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * This is the Wall class.
 */
public class Wall {

    private static final int LEVELS_COUNT = 7;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    private static final int WOODEN = 4;
    private static final int SPECIAL = 5;

    private Random rnd;
    private Rectangle area;



    private Brick[] bricks;
    private Ball ball;
    private static Player player;

    private Brick[][] levels;



    private int level;



    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * Class constructor.
     * Make levels.
     * Set the speed of the ball to random number.
     * Initialize a Player object.
     * @param drawArea The area that all the components can be drawn.
     * @param brickCount The number of bricks.
     * @param lineCount The number of lines the bricks align.
     * @param brickDimensionRatio The brick dimension ratio.
     * @param ballPos The ball position.
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);

        player = new Player((Point) ballPos.clone(),150,10, drawArea);

        area = drawArea;


    }

    /**
     * Make level with only one type of brick.
     * Make all the bricks based on the type of the brick.
     * Align all the bricks.
     * @param drawArea The area that all the components can be drawn.
     * @param brickCnt The number of bricks.
     * @param lineCnt The number of lines the bricks align.
     * @param brickSizeRatio The brick size ratio.
     * @param type The type of brick.
     * @return A Brick array.
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * Make level in chessboard style with two types of brick.
     * Make all the bricks based on the type of the brick.
     * Align all the bricks.
     * @param drawArea The area that all the components can be drawn.
     * @param brickCnt The number of bricks.
     * @param lineCnt The number of lines the bricks align.
     * @param brickSizeRatio The brick size ratio.
     * @param typeA The type of the first brick.
     * @param typeB The type of the second brick.
     * @return A Brick array.
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * Make level in chessboard style with three types of brick.
     * Make all the bricks based on the type of the brick.
     * Align all the bricks.
     * @param drawArea The area that all the components can be drawn.
     * @param brickCnt The number of bricks.
     * @param lineCnt The number of lines the bricks align.
     * @param brickSizeRatio The brick size ratio.
     * @param typeA The type of the first brick.
     * @param typeB The type of the second brick.
     * @param typeC The type of the third brick.
     * @return A Brick array.
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB, int typeC){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeC);
        }
        return tmp;
    }

    /**
     * Make a new rubber ball using the ball position passed.
     * @param ballPos The ball position.
     */
    private void makeBall(Point2D ballPos){
        ball = new RubberBall(ballPos);
    }

    /**
     * Make all the levels using different types of brick.
     * @param drawArea The area that all the components can be drawn.
     * @param brickCount The number of bricks.
     * @param lineCount The number of lines the bricks align.
     * @param brickDimensionRatio The brick dimension ratio.
     * @return A two-dimensional Brick array.
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        tmp[4] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WOODEN,CEMENT);
        tmp[5] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT,SPECIAL);
        tmp[6] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,WOODEN,STEEL,SPECIAL);

        return tmp;
    }

    /**
     * Move the player and the ball.
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * Do the corresponding actions when the ball is in impact with all the components.
     * If the ball is in impact with the player, reverse speedY of the ball.
     * Else if the ball is in impact with the wall, which is the bricks, minus the number of bricks by 1 if the brick is broken.
     * Else if the ball is in impact with the border, reverse speedX of the ball.
     * Else if the ball is in impact with the upper border of the frame, reverse speedY of the ball.
     * Else if the ball drops below the frame, minus ballCount by 1 and set ballLost to true;
     */
    public void findImpacts(){
        if(player.impact(ball)){
            ball.reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
            System.out.println(HighScore.getSCORE()); //DELETE ME
            System.out.println(HighScore.getHighScore()); //DELETE ME
        }
        else if(impactBorder()) {
            ball.reverseX();
        }
        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * Check if there is impact by the ball for every brick.
     * Check the direction of the impact.
     * If the impact is from the up and down direction, reverse the speedY of the ball.
     * If the impact is from the left and right direction, reverse the speedX of the ball.
     * If there is no impact between the ball and the brick, then return false.
     * @return A boolean value whether the brick is broken.
     */
    private boolean impactWall(){
        for(Brick b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDown(), Crack.UP);
                case Brick.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUp(),Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRight(),Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeft(),Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * Check if the ball is in impact with the border.
     * @return A boolean value whether the ball is in impact with the border.
     */
    private boolean impactBorder(){
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }



    /**
     * Get the value of number of bricks, brickCount.
     * @return An int value of brickCount.
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * Get the value of number of ball, ballCount.
     * @return An int value of ballCount.
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * Get the value of ballLost.
     * @return A boolean value of ballLost.
     */
    public boolean isBallLost(){
        return ballLost;
    }

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    public void setBallCount(int ballCount) {
        this.ballCount = ballCount;
    }

    public void setBallLost(boolean ballLost) {
        this.ballLost = ballLost;
    }

    /**
     * Move the player to the start position.
     * Move the ball to the start position.
     * Randomly assign integer between -2 and 3 to speedX and
     * randomly assign integer between 0 to -3 to speedY of the ball.
     * Set the speed of the ball.
     * Set ballLost to false.
     */
    public void ballReset(){
        player.moveTo(startPoint);
        ball.moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * Reset all the bricks to their original state.
     * Set the brickCount to the length of the brick array.
     */
    public void wallReset(){
        for(Brick b : bricks)
            b.repair();
        brickCount = bricks.length;
        //ballCount = 3;
    }


    /**
     * Check if the there is no more ball.
     * If ballCount equals to 0, then return true.
     * Else, return false.
     * @return A boolean value whether the ballCount equals to 0.
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * Check if the level is done by the user. Check if all the bricks are destroyed by the user.
     * If brickCount equals to 0, then return true.
     * Else, return false.
     * @return A boolean value whether the brickCount equals to 0.
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * Go to the next level.
     * Set the brickCount to the length of the brick array.
     */
    public void nextLevel(){
        bricks = levels[level++];
        this.brickCount = bricks.length;
    }



    /**
     * Check if there is any level left.
     * If level is less than the length of the levels array, then return true.
     * Else, return false.
     * @return A boolean value whether there is any levels left.
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * Set the value of speedX of the ball.
     * @param s An int value containing the value for speedX of the ball.
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * Set the value of speedY of the ball.
     * @param s An int value containing the value for speedY of the ball.
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * Reset the number of balls, ballCount.
     * Set the ballCount to 3.
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * Make the corresponding brick based on the type of the brick passed in to the method.
     * @param point The position of the brick.
     * @param size The size of the brick.
     * @param type The type of the brick.
     * @return A Brick object.
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            case WOODEN:
                out = new WoodenBrick(point,size);
                break;
            case SPECIAL:
                out = new SpecialBrick(point,size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }



    /**
     * Get all the bricks.
     * @return A Brick array containing all the bricks.
     */
    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    /**
     * Get the ball.
     * @return A Ball object of the ball.
     */
    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    /**
     * Get the player.
     * @return A player object of the player.
     */
    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player p) {
        player = p;
    }



}
