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


/**
 * This is the Player class.
 */
public class Player {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    private static Rectangle playerFace;

    private Point ballPoint;



    private int moveAmount;



    private int min;
    private int max;
    private Rectangle container;


    /**
     * Class constructor.
     * Set moveAmount to 0.
     * Make a rectangle as the playerFace.
     * Calculate the min and max value.
     * @param ballPoint The point of the ball position.
     * @param width The width of the player.
     * @param height The height of the player.
     * @param container The container where the player is placed.
     */
    public Player(Point ballPoint,int width,int height,Rectangle container) {
        this.ballPoint = ballPoint;
        this.container = container;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * Make a rectangle based on the width and height passed
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @return A Rectangle that is created using the width and the height passed.
     */
    public Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**Update the width and the height of the player face based on the width and height passed.
     * Calculate the min and max based on the updated width.
     * @param width The updated width of the player face.
     * @param height The updated height of the player face.
     */
    public void updatePlayerFace(int width,int height) {
        playerFace = makeRectangle(width,height);
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    /**
     * Reset the player face to its original state.
     * Reset the min and max value.
     */
    public void resetPlayerFace() {
        int width = 150;
        int height = 10;
        playerFace = makeRectangle(width,height);
        min = container.x + (width / 2);
        max = min + container.width - width;
    }

    /**
     * Check if there is impact between the ball and the player.
     * @param b The Ball object.
     * @return A boolean value whether the ball is in impact with the player.
     */
    public boolean impact(Ball b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.getDown()) ;
    }

    /**
     * Move the player.
     * Do not allow the user to move the player beyond the border by using min and max value.
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * Move the player to the left.
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * Move the player to the right.
     */
    public void moveRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * Stop the player.
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * Get the playerFace.
     * @return A Shape value of playerFace.
     */
    public static Shape getPlayerFace(){
        return playerFace;
    }

    /**
     * Get the width of the playerFace.
     * @return An int value of the width of the playerFace.
     */
    public static int getPlayerFaceWidth() {
        return playerFace.width;
    }


    /**
     * Move the player to a specific point.
     * @param p The point where the player should be moved to.
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    public int getMoveAmount() {
        return moveAmount;
    }

    public void setMoveAmount(int moveAmount) {
        this.moveAmount = moveAmount;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
