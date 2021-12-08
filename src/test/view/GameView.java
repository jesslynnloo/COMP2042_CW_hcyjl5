package test.view;

import test.controller.GameController;
import test.model.Ball;
import test.model.Brick;
import test.model.Player;
import test.model.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


/**
 * This is the GameView class which extends JComponent and implements KeyListener, MouseListener and MouseMotionListener interface.
 */
public class GameView extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0,255,0);


    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;

    private static final Color BG_COLOR = Color.WHITE;

    private Wall wall;

    private String message;

    private Font menuFont;

    private Rectangle continueButtonRect;
    private Rectangle exitButtonRect;
    private Rectangle restartButtonRect;
    private int strLen;


    private GameFrame owner;

    private DebugConsole debugConsole;

    private GameController gameController = new GameController(this);

    /**
     * Class constructor.
     * Set the strLen to 0.
     * Set showPauseMenu to false.
     * Set the menuFont.
     * Initialize the frame.
     * Initialize the message with an empty string.
     * Create and initialize a Wall object.
     * Create and initialize a DebugConsole object.
     * Initialize the first level.
     * @param owner The GameFrame object.
     */
    public GameView(GameFrame owner){
        super();
        this.owner = owner;

        strLen = 0;
        gameController.setShowPauseMenu(false);

        menuFont = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

        this.initialize();
        message = "";
        wall = new Wall(new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT),30,3,6/2,new Point(300,430));

        debugConsole = new DebugConsole(owner,wall,gameController);
        //initialize the first level
        wall.nextLevel();
    }


    /**
     * Initialize the frame.
     * Set the preferred size of the frame.
     * Requests that this frame gets the input focus.
     * Add key listener, mouse listener and mouse motion listener to the frame.
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    /**
     * Paint all the components, message, ball, player and pause menu if the showPauseMenu is true.
     * Synchronizes this toolkit's graphics state.
     * @param g The Graphics object.
     */
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        g2d.drawString(message,250,225);

        drawBall(wall.getBall(),g2d);

        for(Brick b : wall.getBricks())
            if(!b.isBroken())
                drawBrick(b,g2d);

        drawPlayer(wall.getPlayer(),g2d);

        if(gameController.isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Fill the frame with BG_COLOR.
     * @param g2d The Graphics2D object.
     */
    private void clear(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * Draw the brick.
     * @param brick The Brick object.
     * @param g2d The Graphics2D object.
     */
    private void drawBrick(Brick brick,Graphics2D g2d){
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    /**
     * Draw the ball.
     * @param ball The Ball object.
     * @param g2d The Graphics2D object.
     */
    private void drawBall(Ball ball, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw the player.
     * @param p The Player object.
     * @param g2d The Graphics2D object.
     */
    private void drawPlayer(Player p, Graphics2D g2d){
        Color tmp = g2d.getColor();

        Shape s = p.getPlayerFace();
        g2d.setColor(Player.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Player.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    /**
     * Draw the pause menu.
     * @param g2d The Graphics2D object.
     */
    private void drawMenu(Graphics2D g2d){
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    /**
     * Do blending and transparency effects.
     * Set the color to black and fill the frame with the color.
     * @param g2d The Graphics2D object.
     */
    private void obscureGameBoard(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,DEF_WIDTH,DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * Draw the pause menu.
     * @param g2d The Graphics2D object.
     */
    private void drawPauseMenu(Graphics2D g2d){
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(menuFont);
        g2d.setColor(MENU_COLOR);

        if(strLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            strLen = menuFont.getStringBounds(PAUSE,frc).getBounds().width;
        }

        int x = (this.getWidth() - strLen) / 2;
        int y = this.getHeight() / 10;

        g2d.drawString(PAUSE,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;


        if(continueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            continueButtonRect = menuFont.getStringBounds(CONTINUE,frc).getBounds();
            continueButtonRect.setLocation(x,y-continueButtonRect.height);
        }

        g2d.drawString(CONTINUE,x,y);

        y *= 2;

        if(restartButtonRect == null){
            restartButtonRect = (Rectangle) continueButtonRect.clone();
            restartButtonRect.setLocation(x,y-restartButtonRect.height);
        }

        g2d.drawString(RESTART,x,y);

        y *= 3.0/2;

        if(exitButtonRect == null){
            exitButtonRect = (Rectangle) continueButtonRect.clone();
            exitButtonRect.setLocation(x,y-exitButtonRect.height);
        }

        g2d.drawString(EXIT,x,y);



        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    /**
     * Call the checkKeyPressed method in GameController class when key is pressed.
     * @param keyEvent An event which indicates that a keystroke occurred in the component.
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        gameController.checkKeyPressed(keyEvent);
    }

    /**
     * Call the checkKeyReleased method in GameController class when key is released.
     * @param keyEvent An event which indicates that a keystroke occurred in the component.
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        gameController.checkKeyReleased(keyEvent);
    }


    /**
     * Call the checkMouseClicked method in GameController class when mouse is clicked.
     * @param mouseEvent An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        gameController.checkMouseClicked(mouseEvent);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

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
     * Call the checkMouseMoved method in GameController class when mouse is moved.
     * @param mouseEvent An event which indicates that a mouse action occurred in the component.
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        gameController.checkMouseMoved(mouseEvent);
    }

    /**
     * Get the wall.
     * @return The wall.
     */
    public Wall getWall() {
        return wall;
    }

    /**
     * Set the message.
     * @param message A String value containing value for the message.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the continueButtonRect.
     * @return A Rectangle value of continueButtonRect.
     */
    public Rectangle getContinueButtonRect() {
        return continueButtonRect;
    }

    /**
     * Get the exitButtonRect.
     * @return A Rectangle value of exitButtonRect.
     */
    public Rectangle getExitButtonRect() {
        return exitButtonRect;
    }

    /**
     * Get the restartButtonRect.
     * @return A Rectangle value of restartButtonRect.
     */
    public Rectangle getRestartButtonRect() {
        return restartButtonRect;
    }

    /**
     * Get the debugConsole.
     * @return A DebugConsole object of debugConsole.
     */
    public DebugConsole getDebugConsole() {
        return debugConsole;
    }

    /**
     * Get the owner.
     * @return A GameFrame object of owner.
     */
    public GameFrame getOwner() {
        return owner;
    }


    /**
     * Set the cursor to the hand cursor.
     */
    public void setHandCursor () {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * Set the cursor to the default cursor.
     */
    public void setDefaultCursor () {
        this.setCursor(Cursor.getDefaultCursor());
    }


}