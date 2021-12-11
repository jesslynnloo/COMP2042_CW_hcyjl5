package test.controller;

import test.view.HomeMenuView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Timer;

/**
 * This is the HomeMenuController class.
 */
public class HomeMenuController {
    private HomeMenuView homeMenuView;

    private boolean startClicked;
    private boolean menuClicked;
    private boolean infoClicked;

    /**
     * Class constructor.
     * @param homeMenuView The HomeMenuView object.
     */
    public HomeMenuController(HomeMenuView homeMenuView) {
        this.homeMenuView = homeMenuView;
    }


    /**
     * Do the corresponding action when the user clicks the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p)){
            homeMenuView.getOwner().enableGameBoard();
            Timer timer = new Timer();
            TimerTaskController timerTaskController = new TimerTaskController();
            TimerController timerController = new TimerController(timerTaskController, timer);

        }
        else if(homeMenuView.getInfoButton().contains(p)){
            homeMenuView.getOwner().enableInfoView();
        }
        else if(homeMenuView.getMenuButton().contains(p)){
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

    /**
     * Change the appearance of the button and repaint the buttons when the user releases the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
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

    /**
     * Change the cursor image to the hand cursor when the cursor is on the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(homeMenuView.getStartButton().contains(p) || homeMenuView.getInfoButton().contains(p) ||homeMenuView.getMenuButton().contains(p))
            homeMenuView.settingHandCursor();
        else
            homeMenuView.settingDefaultCursor();
    }







    /**
     * Get the value of startClicked.
     * @return A boolean value of startClicked.
     */
    public boolean isStartClicked() {
        return startClicked;
    }

    public void setStartClicked(boolean startClicked) {
        this.startClicked = startClicked;
    }

    /**
     * Get the value of menuClicked.
     * @return A boolean value of menuClicked.
     */
    public boolean isMenuClicked() {
        return menuClicked;
    }

    public void setMenuClicked(boolean menuClicked) {
        this.menuClicked = menuClicked;
    }

    /**
     * Get the value of infoClicked.
     * @return A boolean value of infoClicked.
     */
    public boolean isInfoClicked() {
        return infoClicked;
    }

    public void setInfoClicked(boolean infoClicked) {
        this.infoClicked = infoClicked;
    }
}