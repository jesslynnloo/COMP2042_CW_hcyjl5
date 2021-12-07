package test.controller;

import test.view.GameFrame;
import test.view.HomeMenuView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Timer;

public class HomeMenuController {
    private HomeMenuView homeMenuView;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean infoClicked;
    private Timer timer;
    private TimerController timerController;
    private TimerTaskController timerTaskController;

    public HomeMenuController(HomeMenuView homeMenuView) {
        this.homeMenuView = homeMenuView;
    }


    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p)){
            homeMenuView.getOwner().enableGameBoard();
            timer = new Timer();
            timerTaskController = new TimerTaskController();
            timerController = new TimerController(timerTaskController,timer);

        }
        else if(homeMenuView.getInfoButton().contains(p)){
            homeMenuView.getOwner().enableInfoView();
        }
        else if(homeMenuView.getMenuButton().contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }

    }

    public void checkMousePressed (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p)){
            startClicked = true;
            homeMenuView.repainting(homeMenuView.getStartButton());

        }

        else if(homeMenuView.getInfoButton().contains(p)){
            infoClicked = true;
            homeMenuView.repainting(homeMenuView.getInfoButton());
        }

        else if(homeMenuView.getMenuButton().contains(p)){
            menuClicked = true;
            homeMenuView.repainting(homeMenuView.getMenuButton());
        }
    }

    public void checkMouseReleased (MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            homeMenuView.repainting(homeMenuView.getStartButton());
        }

        else if(infoClicked) {
            infoClicked = false;
            homeMenuView.repainting(homeMenuView.getInfoButton());
        }

        else if(menuClicked){
            menuClicked = false;
            homeMenuView.repainting(homeMenuView.getMenuButton());
        }
    }

    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p) || homeMenuView.getInfoButton().contains(p) ||homeMenuView.getMenuButton().contains(p))
            homeMenuView.settingHandCursor();
        else
            homeMenuView.settingDefaultCursor();
    }

    public boolean isStartClicked() {
        return startClicked;
    }

    public boolean isMenuClicked() {
        return menuClicked;
    }

    public boolean isInfoClicked() {
        return infoClicked;
    }
}