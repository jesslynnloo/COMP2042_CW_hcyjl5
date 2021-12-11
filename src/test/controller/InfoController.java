package test.controller;

import test.view.InfoView;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This is the InfoController class.
 */
public class InfoController {
    private InfoView infoView;



    private boolean backClicked;

    /**
     * Class constructor.
     * @param infoView The InfoView object.
     */
    public InfoController(InfoView infoView) {
        this.infoView = infoView;
    }

    /**
     * Go back to the home menu from the info page when user clicks the button.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(infoView.getBackButton().contains(p)){
            infoView.getOwner().enableHomeMenuFromInfo();
        }

    }

    /**
     * Change the appearance of the button and repaint the button when the user presses the button.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMousePressed (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(infoView.getBackButton().contains(p)){
            backClicked = true;
            infoView.repainting(infoView.getBackButton());
        }
    }

    /**
     * Change the appearance of the button and repaint the button when the user releases the button.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseReleased (MouseEvent mouseEvent) {
        if(backClicked){
            backClicked = false;
            infoView.repainting(infoView.getBackButton());
        }
    }

    /**
     * Change the cursor image to the hand cursor when the cursor is on the buttons.
     * @param mouseEvent Event which indicates that a mouse action occurred.
     */
    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(infoView.getBackButton().contains(p))
            infoView.settingHandCursor();
        else
            infoView.settingDefaultCursor();
    }



    /**
     * Get the value of backClicked.
     * @return A boolean value of backClicked.
     */
    public boolean isBackClicked() {
        return backClicked;
    }

    public void setBackClicked(boolean backClicked) {
        this.backClicked = backClicked;
    }
}
