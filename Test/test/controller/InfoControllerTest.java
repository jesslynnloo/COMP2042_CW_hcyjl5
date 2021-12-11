package test.controller;

import game.controller.InfoController;
import org.junit.jupiter.api.Test;
import game.view.GameFrame;
import game.view.InfoView;

import static org.junit.jupiter.api.Assertions.*;

class InfoControllerTest {
    GameFrame gameFrame = new GameFrame();
    InfoView infoView = new InfoView(gameFrame);
    InfoController infoController = new InfoController(infoView);

    @Test
    void isBackClicked() {
        infoController.setBackClicked(true);
        assertTrue(infoController.isBackClicked());
    }

    @Test
    void setBackClicked() {
        infoController.setBackClicked(true);
        assertTrue(infoController.isBackClicked());
    }
}