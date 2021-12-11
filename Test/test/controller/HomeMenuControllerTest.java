package test.controller;

import org.junit.jupiter.api.Test;
import test.view.GameFrame;
import test.view.HomeMenuView;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class HomeMenuControllerTest {
    GameFrame gameFrame = new GameFrame();
    HomeMenuView homeMenuView = new HomeMenuView(gameFrame, new Dimension(450, 300));
    HomeMenuController homeMenuController = new HomeMenuController(homeMenuView);

    @Test
    void isStartClicked() {
        homeMenuController.setStartClicked(true);
        assertTrue(homeMenuController.isStartClicked());
    }

    @Test
    void setStartClicked() {
        homeMenuController.setStartClicked(true);
        assertTrue(homeMenuController.isStartClicked());
    }

    @Test
    void isMenuClicked() {
        homeMenuController.setMenuClicked(true);
        assertTrue(homeMenuController.isMenuClicked());
    }

    @Test
    void setMenuClicked() {
        homeMenuController.setMenuClicked(true);
        assertTrue(homeMenuController.isMenuClicked());
    }

    @Test
    void isInfoClicked() {
        homeMenuController.setInfoClicked(true);
        assertTrue(homeMenuController.isInfoClicked());
    }

    @Test
    void setInfoClicked() {
        homeMenuController.setInfoClicked(true);
        assertTrue(homeMenuController.isInfoClicked());
    }
}