package test.controller;

import test.view.HighScoreView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HighScoreViewController {
    private HighScoreView highScoreView;



    private boolean restartClicked;
    private boolean homeMenuClicked;
    private boolean exitClicked;

    public HighScoreViewController(HighScoreView highScoreView) {
        this.highScoreView = highScoreView;
    }

    public boolean isRestartClicked() {
        return restartClicked;
    }

    public void setRestartClicked(boolean restartClicked) {
        this.restartClicked = restartClicked;
    }

    public boolean isHomeMenuClicked() {
        return homeMenuClicked;
    }

    public void setHomeMenuClicked(boolean homeMenuClicked) {
        this.homeMenuClicked = homeMenuClicked;
    }

    public boolean isExitClicked() {
        return exitClicked;
    }

    public void setExitClicked(boolean exitClicked) {
        this.exitClicked = exitClicked;
    }

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

    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(highScoreView.getRestartButton().contains(p) || highScoreView.getHomeMenuButton().contains(p) || highScoreView.getExitButton().contains(p))
            highScoreView.settingHandCursor();
        else
            highScoreView.settingDefaultCursor();
    }


}
