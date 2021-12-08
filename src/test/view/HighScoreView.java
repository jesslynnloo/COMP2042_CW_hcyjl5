package test.view;

import test.controller.HighScoreController;
import test.controller.HighScoreViewController;
import test.controller.HomeMenuController;
import test.model.HighScore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * This is the HighScoreView class which extends JComponent and implements MouseListener and MouseMotionListener interface.
 */
public class HighScoreView extends JComponent implements MouseListener, MouseMotionListener {

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;



    private Rectangle restartButton;
    private Rectangle homeMenuButton;
    private Rectangle exitButton;

    private static final String RESTART_TEXT = "Restart";
    private static final String HOME_MENU_TEXT = "Home Menu";
    private static final String EXIT_TEXT = "Exit";

    private static final String SCORE_TEXT = "Your score is: ";
    private static final String HIGHSCORE_TEXT = "The highscore is: ";

    private Font buttonFont;

    private static final Color BG_COLOR = Color.WHITE.darker();
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private int stringHeight;

    Font font = new Font("TimesRoman", Font.PLAIN, 18);

    private Rectangle menuFace;



    private GameFrame owner;

    private HighScoreViewController highScoreViewController = new HighScoreViewController(this);

    /**
     * Class constructor.
     * Initialize the frame.
     * Create a new Rectangle object as the menuFace.
     * Create three new Rectangle objects as the restartButton, homeMenuButton and exitButton.
     * @param owner The GameFrame object.
     */
    public HighScoreView(GameFrame owner) {
        this.owner = owner;
        this.initialize();

        menuFace = new Rectangle(new Point(0,0),new Dimension(DEF_WIDTH, DEF_HEIGHT));
        Dimension btnDim = new Dimension(DEF_WIDTH / 3, DEF_HEIGHT / 12);
        restartButton = new Rectangle(btnDim);
        homeMenuButton = new Rectangle(btnDim);
        exitButton = new Rectangle(btnDim);


        buttonFont = new Font("Monospaced",Font.PLAIN,restartButton.height-2);

    }

    /**
     * Initialize the frame.
     * Set the preferred size of the frame.
     * Requests that this frame gets the input focus.
     * Add key listener, mouse listener and mouse motion listener to the frame.
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Paint all the components in the high score window.
     * @param g The Graphics object.
     */
    public void paint(Graphics g){
        drawHighScoreView((Graphics2D)g);
    }


    /**
     * Draw the high score view.
     * @param g2d The Graphics2D object.
     */
    private void drawHighScoreView(Graphics2D g2d) {
        drawContainer(g2d);

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
        paintComponent((Graphics2D) g);

    }

    /**
     * Draw the background image for the high score window.
     * @param g The Graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Image image = Toolkit.getDefaultToolkit().getImage("highscore_view_background.jpg");
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        if(image != null) {
            int x = (menuFace.width - image.getWidth(this)) / 2;
            int y = (menuFace.height - image.getHeight(this)) / 2;
            g2d.drawImage(image, x, y, this);
        }
    }


    /**
     * Draw all the text in the high score window.
     * @param g2d The Graphics2D object.
     */
    private void drawAllText(Graphics2D g2d){
        drawText(g2d, font, SCORE_TEXT, HighScore.getSCORE());
        drawText(g2d, font, HIGHSCORE_TEXT, HighScore.getHighScore());
    }

    /**
     * Draw the text at the specific position of the frame.
     * @param g2d The Graphics2D object.
     * @param font The font of the text.
     * @param text The text to be drawn.
     * @param displayedScore The score to be displayed.
     */
    private void drawText(Graphics2D g2d, Font font, String text, int displayedScore){
        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D textRect = font.getStringBounds(text, frc);

        int sX;


        sX = (int)(menuFace.getWidth() - textRect.getWidth()) / 2;

        if(text.equals(SCORE_TEXT)){
            stringHeight = (int)(menuFace.getHeight() / 4);
        }
        else{
            stringHeight += (int) textRect.getHeight() * 1.1;//add 10% of String height between the two strings
        }

        g2d.setFont(font);
        g2d.drawString(text + displayedScore,sX,stringHeight);

    }

    /**
     * Draw all the buttons in the high score view.
     * @param g2d The Graphics2D object.
     */
    private void drawAllButton(Graphics2D g2d){
        drawButton(g2d, restartButton, RESTART_TEXT, highScoreViewController.isRestartClicked());
        drawButton(g2d, homeMenuButton, HOME_MENU_TEXT, highScoreViewController.isHomeMenuClicked());
        drawButton(g2d, exitButton, EXIT_TEXT, highScoreViewController.isExitClicked());
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

        if(buttonText.equals(RESTART_TEXT)){
            x = (menuFace.width - restartButton.width) / 2;
            y =(int) ((menuFace.height - restartButton.height) * 0.5);
        }

        else if(buttonText.equals(HOME_MENU_TEXT)){
            x = restartButton.x;
            y = restartButton.y;

            y *= 1.2;
        }

        else if(buttonText.equals(EXIT_TEXT)) {
            x = restartButton.x;
            y = restartButton.y;

            y *= 1.4;
        }

        txtRect = buttonFont.getStringBounds(buttonText,frc);
        g2d.setFont(buttonFont);

        button.setLocation(x,y);

        x = (int)(button.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(button.getHeight() - txtRect.getHeight()) / 2;

        x += button.x;
        y += button.y + (restartButton.height * 0.9);

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
     * Repaint the button.
     * @param button The button to be repainted.
     */
    public void repainting (Rectangle button) {
        repaint(button.x,button.y,button.width+1,button.height+1);
    }

    /**
     * Call the checkMouseClicked method in HighScoreViewController class when mouse is clicked.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        highScoreViewController.checkMouseClicked(e);
    }

    /**
     * Call the checkMousePressed method in HighScoreViewController class when mouse is pressed.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        highScoreViewController.checkMousePressed(e);
    }

    /**
     * Call the checkMouseReleased method in HighScoreViewController class when mouse is released.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        highScoreViewController.checkMouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Call the checkMouseMoved method in HighScoreViewController class when mouse is moved.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        highScoreViewController.checkMouseMoved(e);
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

    /**
     * Get the restartButton.
     * @return A Rectangle object of restartButton.
     */
    public Rectangle getRestartButton() {
        return restartButton;
    }

    /**
     * Get the homeMenuButton.
     * @return A Rectangle object of homeMenuButton.
     */
    public Rectangle getHomeMenuButton() {
        return homeMenuButton;
    }

    /**
     * Get the exitButton.
     * @return A Rectangle object of exitButton.
     */
    public Rectangle getExitButton() {
        return exitButton;
    }

    /**
     * Get the owner.
     * @return A GameFrame object of owner.
     */
    public GameFrame getOwner() {
        return owner;
    }
}
