package game.view;

import game.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/**
 * This is the GameFrame class which extends JFrame and implements WindowFocusListener interface.
 */
public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameView view;
    private GameController gameController;
    private HomeMenuView homeMenuView;
    private HighScoreView highScoreView;
    private InfoView infoView;

    private boolean gaming;

    /**
     * Class constructor.
     * Constructs a new frame that is initially invisible.
     * Set gaming to false.
     * Set the layout of the frame.
     * Initialize GameView object, GameController object, HomeMenuView object, HomeMenuController object,
     * HighScoreView object, HighScoreViewController object, InfoView object and InfoController object.
     * Add the homeMenuView to the frame.
     * Disables decorations for this frame.
     */
    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        view = new GameView(this);
        gameController = new GameController(view);

        homeMenuView = new HomeMenuView(this, new Dimension(450, 300));

        highScoreView = new HighScoreView(this);

        infoView = new InfoView(this);

        this.add(homeMenuView,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    /**
     * Initialize the frame.
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * Releases all the native screen resources used by this Window.
     * Remove the homeMenuView.
     * Add the game view to the frame.
     * Enables decorations for this frame.
     * Initialize the frame.
     * Add window focus listener to this frame.
     */
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuView);
        this.add(view,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    /**
     * Releases all the native screen resources used by this window.
     * Remove the highScoreView.
     * Add the game view to the frame.
     * Enables decorations for this frame.
     * Initialize the frame.
     * Add window focus listener to this frame.
     * Reset the ball, the wall, the ball count, the score and the player face.
     */
    public void enableGameBoardFromHighscore() {
        this.dispose();
        this.remove(highScoreView);
        this.add(view,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

        gameController.reset();
    }

    /**
     * Releases all the native screen resources used by this window.
     * Remove the highScoreView.
     * Add the homeMenuView to the frame.
     * Disables decorations for this frame.
     * Initialize the frame.
     * Add window focus listener to this frame.
     * Reset the ball, the wall, the ball count, the score and the player face.
     */
    public void enableHomeMenu() {
        this.dispose();
        this.remove(highScoreView);
        this.add(homeMenuView,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);

        gameController.reset();
    }

    /**
     * Releases all the native screen resources used by this window.
     * Remove the infoView.
     * Add the homeMenuView to the frame.
     * Disables decorations for this frame.
     * Initialize the frame.
     * Add window focus listener to this frame.
     */
    public void enableHomeMenuFromInfo() {
        this.dispose();
        this.remove(infoView);
        this.add(homeMenuView,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Releases all the native screen resources used by this window.
     * Remove the game view.
     * Add the highScoreView to the frame.
     * Disables decorations for this frame.
     * Initialize the frame.
     * Add window focus listener to this frame.
     */
    public void enableHighScoreView() {
        this.dispose();
        this.remove(view);
        this.add(highScoreView,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Releases all the native screen resources used by this window.
     * Remove the homeMenuView.
     * Add the infoView to the frame.
     * Disables decorations for this frame.
     * Initialize the frame.
     * Add window focus listener to this frame.
     */
    public void enableInfoView() {
        this.dispose();
        this.remove(homeMenuView);
        this.add(infoView,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    /**
     * Locate the frame at the middle of the screen.
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /**
     * Set gaming to true when the window gains focus.
     * @param windowEvent A low-level event that indicates that a window has changed its status.
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * If the value of gaming is true, call the onLostFocus() method in GameController class.
     * Else, do nothing.
     * @param windowEvent A low-level event that indicates that a window has changed its status.
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameController.onLostFocus();

    }
}