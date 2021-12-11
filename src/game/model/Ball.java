package game.model;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * This is the Ball class.
 */
abstract public class Ball {

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * Class constructor.
     * Make the ball face.
     * @param center The center point of the ball.
     * @param radiusA The width of the ball.
     * @param radiusB The height of the ball.
     * @param inner The inner color of the ball.
     * @param border The border color of the ball.
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * An abstract method that does not have any implementation.
     * @param center The center point of the ball.
     * @param radiusA The width of the ball.
     * @param radiusB The height of the ball.
     * @return The ball shape.
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * Move the ball.
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * Set the value of speedX and speedY.
     * @param x An int value containing the value for speedX.
     * @param y An int value containing the value for speedY.
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * Set the value of speedX.
     * @param s An int value containing the value for speedX.
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * Set the value of speedY.
     * @param s An int value containing the value for speedY.
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * Reverse the speedX to make the ball to travel in the opposite direction.
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * Reverse the speedY to make the ball to travel in the opposite direction.
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * Get the border color of the ball.
     * @return A Color value of the border color of the ball.
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * Get the inner color of the ball.
     * @return A Color value of the inner color of the ball.
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * Get the center position of the ball.
     * @return A Point2D value of the center of the ball.
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * Get the ball face of the ball.
     * @return A Shape value of the ballFace of the ball.
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * Move the ball to a specific position.
     * @param p The point which the ball should move to.
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * Set the points for up, down, left and right of the ball.
     * @param width The width of the ball.
     * @param height The height of the ball.
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * Get speedX of the ball.
     * @return An int value of speedX of the ball.
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * Get speedY of the ball.
     * @return An int value of speedY of the ball.
     */
    public int getSpeedY(){
        return speedY;
    }

    /**
     * Get the up point of the ball.
     * @return A Point2D value of up point of the ball.
     */
    public Point2D getUp() {
        return up;
    }

    /**
     * Get the down point of the ball.
     * @return A Point2D value of down point of the ball.
     */
    public Point2D getDown() {
        return down;
    }

    /**
     * Get the left point of the ball.
     * @return A Point2D value of left point of the ball.
     */
    public Point2D getLeft() {
        return left;
    }

    /**
     * Get the right point of the ball.
     * @return A Point2D value of right point of the ball.
     */
    public Point2D getRight() {
        return right;
    }
}
