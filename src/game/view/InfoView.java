package game.view;

import game.controller.InfoController;

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
 * This is the InfoView class which extends JComponent and implements MouseListener and MouseMotionListener interface.
 */
public class InfoView extends JComponent implements MouseListener, MouseMotionListener {


    private GameFrame owner;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;


    private Rectangle backButton;

    private Rectangle menuFace;

    private static final String INFO_TEXT = "Info Screen";
    private static final String BACK_TEXT = "Back";

    private static final String info1 = "1. Player's goal is to destroy a wall with a small ball.";
    private static final String info2 = "2. SPACE start/pause the game.";
    private static final String info3 = "3. A move left the player.";
    private static final String info4 = "4. D move right the player.";
    private static final String info5 = "5. ESC enter/exit pause menu.";
    private static final String info6 = "6. ALT+SHITF+F1 open console.";
    private static final String info7 = "7. The game automatically pause if the frame loses focus.";

    private static final Color BG_COLOR = Color.WHITE.darker();
    private static final Color TEXT_COLOR = new Color(101, 67, 33);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Font buttonFont;
    private Font titleFont;
    private Font infoFont;

    private InfoController infoController = new InfoController(this);

    int stringHeight;

    /**
     * Class constructor.
     * Initialize the frame.
     * Create and initialize a rectangle object as the menuFace.
     * Create and initialize a rectangle object as the button.
     * Set the font of the text.
     * @param owner The GameFrame object.
     */
    public InfoView(GameFrame owner) {
        this.owner = owner;
        this.initialize();


        menuFace = new Rectangle(new Point(0,0),new Dimension(DEF_WIDTH,DEF_HEIGHT));

        Dimension btnDim = new Dimension(DEF_WIDTH / 5, DEF_HEIGHT / 15);
        backButton = new Rectangle(btnDim);

        titleFont = new Font("Noto Mono",Font.PLAIN,25);
        infoFont = new Font("Merlin", Font.PLAIN, 20);
        buttonFont = new Font("Monospaced",Font.PLAIN,backButton.height-2);
    }

    /**
     * Initialize the frame.
     * Set the preferred size of the frame.
     * Requests that this frame gets the input focus.
     * Add mouse listener and mouse motion listener to the frame.
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
     * Paint all the components in the info window.
     * @param g The Graphics object.
     */
    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

    /**
     * Draw the info menu.
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
     * Draw the background image for the info window.
     * @param g The Graphics object.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Image image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResource("/resources/info_background.jpg")));
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
     * Draw all the text in the info window.
     * @param g2d The Graphics2D object.
     */
    private void drawAllText(Graphics2D g2d){
        drawText(g2d, titleFont, INFO_TEXT);
        drawText(g2d, infoFont, info1);
        drawText(g2d, infoFont, info2);
        drawText(g2d, infoFont, info3);
        drawText(g2d, infoFont, info4);
        drawText(g2d, infoFont, info5);
        drawText(g2d, infoFont, info6);
        drawText(g2d, infoFont, info7);


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



        if(text.equals(INFO_TEXT)){
            sX = (int)(menuFace.getWidth() - textRect.getWidth()) / 2;
            stringHeight = (int)(menuFace.getHeight() / 12);
        }
        else{
            sX = (int)(menuFace.getWidth()) / 10;
            stringHeight += (int) textRect.getHeight() * 2;//add 10% of String height between the two strings
        }

        g2d.setFont(font);
        g2d.drawString(text,sX,stringHeight);

    }

    /**
     * Draw all the buttons in the info window.
     * @param g2d The Graphics2D object.
     */
    private void drawAllButton(Graphics2D g2d){
        drawButton(g2d, backButton, BACK_TEXT, infoController.isBackClicked());
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
        int x,y;

        x = (menuFace.width - backButton.width) / 35;
        y =(int) ((menuFace.height - backButton.height) * 0.98);

        txtRect = buttonFont.getStringBounds(buttonText,frc);
        g2d.setFont(buttonFont);

        button.setLocation(x,y);

        x = (int)(button.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(button.getHeight() - txtRect.getHeight()) / 2;

        x += button.x;
        y += button.y + (backButton.height * 0.9);

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
     * Call the checkMouseClicked method in InfoController class when mouse is clicked.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        infoController.checkMouseClicked(e);
    }

    /**
     * Call the checkMousePressed method in InfoController class when mouse is pressed.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        infoController.checkMousePressed(e);
    }

    /**
     * Call the checkMouseReleased method in InfoController class when mouse is released.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        infoController.checkMouseReleased(e);
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
     * Call the checkMouseMoved method in InfoController class when mouse is moved.
     * @param e An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        infoController.checkMouseMoved(e);
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

    /**
     * Get the backButton.
     * @return A Rectangle object of backButton.
     */
    public Rectangle getBackButton() {
        return backButton;
    }

    /**
     * Get the owner.
     * @return A GameFrame object of owner.
     */
    public GameFrame getOwner() {
        return owner;
    }

}
