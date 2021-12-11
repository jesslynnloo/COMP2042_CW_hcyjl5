/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package game.view;


import game.model.Ball;
import game.model.Wall;
import game.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This is the DebugConsole class which extends JDialog class and implements WindowListener interface.
 */
public class DebugConsole extends JDialog implements WindowListener{

    private static final String TITLE = "Debug Console";


    private JFrame owner;
    private DebugPanel debugPanel;
//    private GameBoard gameBoard;
    private GameController gameController;
    private Wall wall;


    /**
     * Class constructor.
     * Initialize the frame.
     * Initialize a DebugPanel object and add the object to the frame.
     * @param owner The JFrame object.
     * @param wall The Wall object.
     * @param gameController The GameController object.
     */
    public DebugConsole(JFrame owner, Wall wall, GameController gameController){

        this.wall = wall;
        this.owner = owner;
        this.gameController = gameController;
        initialize();

        debugPanel = new DebugPanel(wall);
        this.add(debugPanel,BorderLayout.CENTER);


        this.pack();
    }

    /**
     * Initialize the dialog window.
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * Set the location of the dialog window.
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**
     * Repaint all the components after the dialog window is closed.
     * @param windowEvent A low-level event that indicates that a window has changed its status.
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameController.repainting();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * Set the location of the dialog window.
     * Set the current value of the slider to speedX and speedY of the ball.
     * @param windowEvent A low-level even that indicates that a window has changed its status.
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = wall.getBall();
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}
