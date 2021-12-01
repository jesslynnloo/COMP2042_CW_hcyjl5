package test.controller;

import test.view.GameFrame;
import test.view.HomeMenuView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HomeMenuController {
    private HomeMenuView homeMenuView;

    private boolean startClicked;
    private boolean menuClicked;

    public HomeMenuController(HomeMenuView homeMenuView) {
        this.homeMenuView = homeMenuView;
    }


    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p)){
            homeMenuView.getOwner().enableGameBoard();

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
        else if(menuClicked){
            menuClicked = false;
            homeMenuView.repainting(homeMenuView.getMenuButton());
        }
    }

    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p) || homeMenuView.getMenuButton().contains(p))
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
}