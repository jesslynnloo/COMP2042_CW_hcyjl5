package test.controller;

import test.view.InfoView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class InfoController {
    private InfoView infoView;



    private boolean backClicked;

    public InfoController(InfoView infoView) {
        this.infoView = infoView;
    }

    public void checkMouseClicked (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(infoView.getBackButton().contains(p)){
            infoView.getOwner().enableHomeMenuFromInfo();
        }

    }

    public void checkMousePressed (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(infoView.getBackButton().contains(p)){
            backClicked = true;
            infoView.repainting(infoView.getBackButton());
        }
    }

    public void checkMouseReleased (MouseEvent mouseEvent) {
        if(backClicked){
            backClicked = false;
            infoView.repainting(infoView.getBackButton());
        }
    }

    public void checkMouseMoved (MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(infoView.getBackButton().contains(p))
            infoView.settingHandCursor();
        else
            infoView.settingDefaultCursor();
    }

    public boolean isBackClicked() {
        return backClicked;
    }
}
