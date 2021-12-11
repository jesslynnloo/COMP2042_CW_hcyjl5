package game.controller;

import game.view.HighScoreView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This is the HighScoreViewController class.
 */
public class HighScoreViewController {
    private HighScoreView highScoreView;



    private boolean restartClicked;
    private boolean homeMenuClicked;
    private boolean exitClicked;

    /**
     * Class constructor.
     * @param highScoreView The HighScoreView object.
     */
    public HighScoreViewController(HighScoreView highScoreView) {
        this.highScoreView = highScoreView;
    }







    /**
     * Get the value of restartClicked.
     * @return A boolean value of restartClicked.
     */
    public boolean isRestartClicked() {
        return restartClicked;
    }

    public void setRestartClicked(boolean restartClicked) {
        this.restartClicked = restartClicked;
    }


    /**
     * Get the value of homeMenuClicked.
     * @return A boolean value of homeMenuClicked.
     */
    public boolean isHomeMenuClicked() {
        return homeMenuClicked;
    }

    public void setHomeMenuClicked(boolean homeMenuClicked) {
        this.homeMenuClicked = homeMenuClicked;
    }

    /**
     * Get the value of exitClicked.
     * @return A boolean value of exitClicked.
     */
    public boolean isExitClicked() {
        return exitClicked;
    }

    public void setExitClicked(boolean exitClicked) {
        this.exitClicked = exitClicked;
    }

    /**
     * Do the corresponding action when the user clicks the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(highScoreView.getRestartButton().contains(p)){
            highScoreView.getOwner().enableGameBoardFromHighscore();

        }
        else if(highScoreView.getHomeMenuButton().contains(p)){
            highScoreView.getOwner().enableHomeMenu();
        }

        else if(highScoreView.getExitButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    /**
     * Change the appearance of the button and repaint the buttons when the user presses the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMousePressed (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(highScoreView.getRestartButton().contains(p)){
            restartClicked = true;
            highScoreView.repainting(highScoreView.getRestartButton());

        }
        else if(highScoreView.getHomeMenuButton().contains(p)){
            homeMenuClicked = true;
            highScoreView.repainting(highScoreView.getHomeMenuButton());
        }

        else if(highScoreView.getExitButton().contains(p)){
            exitClicked = true;
            highScoreView.repainting(highScoreView.getExitButton());
        }
    }

    /**
     * Change the appearance of the button and repaint the buttons when the user releases the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseReleased (MouseEvent mouseEvent) {
        if(restartClicked){
            restartClicked = false;
            highScoreView.repainting(highScoreView.getRestartButton());
        }
        else if(homeMenuClicked){
            homeMenuClicked = false;
            highScoreView.repainting(highScoreView.getHomeMenuButton());
        }
        else if(exitClicked){
            exitClicked = false;
            highScoreView.repainting(highScoreView.getExitButton());
        }
    }

    /**
     * Change the cursor image to the hand cursor when the cursor is on the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(highScoreView.getRestartButton().contains(p) || highScoreView.getHomeMenuButton().contains(p) || highScoreView.getExitButton().contains(p))
            highScoreView.settingHandCursor();
        else
            highScoreView.settingDefaultCursor();
    }


}
