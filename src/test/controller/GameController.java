package test.controller;

import test.model.HighScore;
import test.model.Wall;
import test.view.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameController {

    private GameView view;
    private Timer gameTimer;

    private boolean showPauseMenu;
    private HighScoreController highScoreController;

    public GameController(GameView view) {
        this.view = view;
        highScoreController = new HighScoreController();
        highScoreController.readHighscoreFromFile();
        timer();
    }

    public void timer () {
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
                }
                else{
                    view.setMessage("ALL WALLS DESTROYED");
                    gameTimer.stop();
                }
            }

            repainting();
        });
    }

    public void onLostFocus(){
        gameTimer.stop();
        view.setMessage("Focus Lost");
        repainting();
    }

    public void checkKeyPressed (KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            case KeyEvent.VK_A:
                view.getWall().getPlayer().moveLeft();
                break;
            case KeyEvent.VK_D:
                view.getWall().getPlayer().movRight();
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
                view.getWall().getPlayer().stop();
        }
    }

    public void checkKeyReleased (KeyEvent keyEvent) {
        view.getWall().getPlayer().stop();
    }

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

    public boolean isShowPauseMenu() {
        return showPauseMenu;
    }

    public void setShowPauseMenu(boolean showPauseMenu) {
        this.showPauseMenu = showPauseMenu;
    }

    public void repainting() {
        view.repaint();
    }

    public void reset() {
        view.getWall().ballReset();
        view.getWall().wallReset();
        view.getWall().resetBallCount();
        highScoreController.restartScore();
    }
}
