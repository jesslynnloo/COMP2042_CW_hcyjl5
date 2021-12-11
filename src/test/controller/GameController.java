package test.controller;

import test.model.HighScore;
import test.model.SpecialBrick;
import test.model.Wall;
import test.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This is the GameController class.
 */
public class GameController {

    private GameView view;
    private Timer gameTimer;

    private boolean showPauseMenu;
    private HighScoreController highScoreController;

    /**
     * Class constructor
     * @param view The game view.
     */
    public GameController(GameView view) {
        this.view = view;
        highScoreController = new HighScoreController();
        highScoreController.readHighscoreFromFile();
        timer();
    }

    /**
     * Implement a game timer.
     */
    private void timer () {
        gameTimer = new Timer(10, e ->{
            view.getWall().move();
            view.getWall().findImpacts();
            view.setMessage(String.format("Bricks: %d Balls %d",view.getWall().getBrickCount(),view.getWall().getBallCount()));
            if(view.getWall().isBallLost()){
                if(view.getWall().ballEnd()){
                    view.getWall().wallReset();
                    view.setMessage("Game over");
                    highScoreController.updateHighScore();
                    System.out.println(HighScore.getHighScore());
                    highScoreController.writeScoreToFile();
                    view.getOwner().enableHighScoreView();


                }
                view.getWall().ballReset();
                gameTimer.stop();
            }
            else if(view.getWall().isDone()){
                if(view.getWall().hasLevel()){
                    view.setMessage("Go to Next Level");
                    gameTimer.stop();
                    view.getWall().ballReset();
                    view.getWall().wallReset();
                    view.getWall().nextLevel();
                    Wall.getPlayer().resetPlayerFace();
                    SpecialBrick.setExtendedWidth(0);
                }
                else{
                    view.setMessage("ALL WALLS DESTROYED");
                    gameTimer.stop();
                }
            }

            repainting();
        });

    }


    /**
     * Called when the window lost focus.
     */
    public void onLostFocus(){
        gameTimer.stop();
        view.setMessage("Focus Lost");
        repainting();
    }

    /**
     * Do the corresponding action when the user presses certain keys.
     * @param keyEvent Event which indicates that a keystroke occurred.
     */
    public void checkKeyPressed (KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                Wall.getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                Wall.getPlayer().moveRight();
                break;
            case KeyEvent.VK_ESCAPE:
                showPauseMenu = !showPauseMenu;
                repainting();
                gameTimer.stop();
                break;
            case KeyEvent.VK_SPACE:
                if(!showPauseMenu)
                    if(gameTimer.isRunning())
                        gameTimer.stop();
                    else
                        gameTimer.start();
                break;
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    view.getDebugConsole().setVisible(true);
            default:
                Wall.getPlayer().stop();
        }
    }

    /**
     * Stop the movement of the player when the user releases certain keys.
     * @param keyEvent Event which indicates that a keystroke occurred.
     */
    public void checkKeyReleased (KeyEvent keyEvent) {
        Wall.getPlayer().stop();
    }

    /**
     * Do the corresponding action when the user clicks the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(!showPauseMenu)
            return;
        if(view.getContinueButtonRect().contains(p)){
            showPauseMenu = false;
            repainting();
        }
        else if(view.getRestartButtonRect().contains(p)){
            view.setMessage("Restarting Game...");
            reset();
            showPauseMenu = false;
            repainting();
        }
        else if(view.getExitButtonRect().contains(p)){
            System.exit(0);
        }
    }

    /**
     * Change the cursor image to the hand cursor when the cursor is on the buttons.
     * @param mouseEvent Event that indicates that a mouse action occurred.
     */
    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(view.getExitButtonRect() != null && showPauseMenu) {
            if (view.getExitButtonRect().contains(p) || view.getContinueButtonRect().contains(p) || view.getRestartButtonRect().contains(p))
                view.setHandCursor();
            else
                view.setDefaultCursor();
        }
        else{
            view.setDefaultCursor();
        }
    }

    /**
     * Get the value of showPauseMenu.
     * @return A boolean value of showPauseMenu.
     */
    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    /**
     * Set the value of showPauseMenu.
     * @param showPauseMenu A boolean value containing the value for showPauseMenu.
     */
    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }

    /**
     * Call the repaint() method in the GameView class.
     */
    public void repainting() {
        view.repaint();
    }

    /**
     * Reset the ball, the wall, the ball count, the score and the player face.
     */
    public void reset() {
        view.getWall().ballReset();
        view.getWall().wallReset();
        view.getWall().resetBallCount();
        highScoreController.restartScore();
        Wall.getPlayer().resetPlayerFace();
    }

    public GameView getView() {
        return view;
    }

}
