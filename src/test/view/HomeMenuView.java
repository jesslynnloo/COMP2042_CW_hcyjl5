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
package test.view;

import test.controller.HomeMenuController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Objects;


/**
 * This is the HomeMenuView class which extends JComponent and implements MouseListener and MouseMotionListener interface.
 */
public class HomeMenuView extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";
    private static final String INFO_TEXT = "Info";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color TEXT_COLOR = Color.WHITE;//new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Rectangle menuFace;



    private Rectangle startButton;
    private Rectangle menuButton;



    private Rectangle infoButton;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private int stringHeight;
    private HomeMenuController homeMenuController = new HomeMenuController(this);

    /**
     * Class constructor.
     * Requests that this frame gets the input focus.
     * Add mouse listener and mouse motion listener to the frame.
     * Create and initialize a Rectangle object as menuFace.
     * Set the preferred size of the frame.
     * Create and initialize three Rectangle objects as the buttons.
     * Set the font for the text.
     * @param owner The GameFrame object.
     * @param area The area of the menu.
     */
    public HomeMenuView (GameFrame owner, Dimension area){
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;

        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        infoButton = new Rectangle(btnDim);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);
    }

    /**
     * Paint all the components in the home menu window.
     * @param g The Graphics object.
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     * Draw the menu.
     * @param g2d The Graphics2D object.
     */
    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawAllText(g2d);
        drawAllButton(g2d);
        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * Draw the container.
     * @param g The Graphics object.
     */
    private void drawContainer(Graphics g){
        paintComponent(g);

    }

    /**
     * Draw the background image for the high score window.
     * @param g The Graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if(image != null) {
            int x = (menuFace.width - image.getWidth(this)) / 2;
            int y = (menuFace.height - image.getHeight(this)) / 2;
            g2d.drawImage(image, x, y, this);
        }
    }

    /**
     * Draw all the text in the home menu window.
     * @param g2d The Graphics2D object.
     */
    private void drawAllText(Graphics2D g2d){
        drawText(g2d, greetingsFont, GREETINGS);
        drawText(g2d, gameTitleFont, GAME_TITLE);
        drawText(g2d, creditsFont, CREDITS);
    }

    /**
     * Draw the text at the specific position of the frame.
     * @param g2d The Graphics2D object.
     * @param font The font of the text.
     * @param text The text to be drawn.
     */
    private void drawText(Graphics2D g2d, Font font, String text){
        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D textRect = font.getStringBounds(text, frc);

        int sX;

        sX = (int)(menuFace.getWidth() - textRect.getWidth()) / 2;

        if(text.equals(GREETINGS)){
            stringHeight = (int)(menuFace.getHeight() / 4);
        }
        else{
            stringHeight += (int) textRect.getHeight() * 1.1;//add 10% of String height between the two strings
        }

        g2d.setFont(font);
        g2d.drawString(text,sX,stringHeight);

    }

    /**
     * Draw all the buttons in the home menu.
     * @param g2d The Graphics2D object.
     */
    private void drawAllButton(Graphics2D g2d){
        drawButton(g2d, startButton, START_TEXT, homeMenuController.isStartClicked());
        drawButton(g2d, menuButton, MENU_TEXT, homeMenuController.isMenuClicked());
        drawButton(g2d, infoButton, INFO_TEXT, homeMenuController.isInfoClicked());
    }

    /**
     * Draw the button at the specific position of the frame.
     * @param g2d The Graphics2D object.
     * @param button The button to be drawn.
     * @param buttonText The button text.
     * @param buttonClicked A boolean value whether the button is clicked.
     */
    private void drawButton(Graphics2D g2d, Rectangle button, String buttonText, boolean buttonClicked){
        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D txtRect;
        int x = 0, y = 0;

        if(buttonText.equals(START_TEXT)){
            x = (menuFace.width - startButton.width) / 2;
            y =(int) ((menuFace.height - startButton.height) * 0.7);
        }

        else if(buttonText.equals(INFO_TEXT)){
            x = startButton.x;
            y = startButton.y;

            y *= 1.2;
        }

        else if(buttonText.equals(MENU_TEXT)){
            x = startButton.x;
            y = startButton.y;

            y *= 1.4;
        }

        txtRect = buttonFont.getStringBounds(buttonText,frc);
        g2d.setFont(buttonFont);

        button.setLocation(x,y);

        x = (int)(button.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(button.getHeight() - txtRect.getHeight()) / 2;

        x += button.x;
        y += button.y + (startButton.height * 0.9);

        if(buttonClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(button);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(buttonText,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(button);
            g2d.drawString(buttonText,x,y);
        }
    }

    /**
     * Call the checkMouseClicked method in homeMenuController class when mouse is clicked.
     * @param mouseEvent An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        homeMenuController.checkMouseClicked(mouseEvent);
    }

    /**
     * Call the checkMousePressed method in homeMenuController class when mouse is pressed.
     * @param mouseEvent An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        homeMenuController.checkMousePressed(mouseEvent);
    }

    /**
     * Call the checkMouseReleased method in homeMenuController class when mouse is released.
     * @param mouseEvent An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        homeMenuController.checkMouseReleased(mouseEvent);
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * Call the checkMouseMoved method in homeMenuController class when mouse is moved.
     * @param mouseEvent An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        homeMenuController.checkMouseMoved(mouseEvent);
    }

    /**
     * Get the startButton.
     * @return A Rectangle object of startButton.
     */
    public Rectangle getStartButton() {
        return startButton;
    }

    /**
     * Get the menuButton.
     * @return A Rectangle object of menuButton.
     */
    public Rectangle getMenuButton() {
        return menuButton;
    }

    /**
     * Get the infoButton.
     * @return A Rectangle object of infoButton.
     */
    public Rectangle getInfoButton() {
        return infoButton;
    }

    /**
     * Get the owner.
     * @return A GameFrame object of owner.
     */
    public GameFrame getOwner() {
        return owner;
    }


    /**
     * Repaint the button.
     * @param button The button to be repainted.
     */
    public void repainting (Rectangle button) {
        repaint(button.x,button.y,button.width+1,button.height+1);
    }

    /**
     * Set the cursor to the hand cursor.
     */
    public void settingHandCursor () {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Set the cursor to the default cursor.
     */
    public void settingDefaultCursor () {
        this.setCursor(Cursor.getDefaultCursor());
    }
}
