package test.view;

import test.controller.GameController;
import test.controller.HighScoreViewController;
import test.controller.HomeMenuController;
import test.controller.InfoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameView view;
    private GameController gameController;
    private HomeMenuController homeMenuController;
    private HomeMenuView homeMenuView;
    private HighScoreView highScoreView;
    private HighScoreViewController highScoreViewController;
    private InfoView infoView;
    private InfoController infoController;

    private boolean gaming;

    public GameFrame(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        view = new GameView(this);
        gameController = new GameController(view);

        homeMenuView = new HomeMenuView(this, new Dimension(450, 300));
        homeMenuController = new HomeMenuController(homeMenuView);

        highScoreView = new HighScoreView(this);
        highScoreViewController = new HighScoreViewController(highScoreView);

        infoView = new InfoView(this);
        infoController = new InfoController(infoView);

        this.add(homeMenuView,BorderLayout.CENTER);

        this.setUndecorated(true);


    }

    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
        this.setResizable(false);
    }

    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenuView);
        this.add(view,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);

    }

    public void enableGameBoardFromHighscore() {
        this.dispose();
        this.remove(highScoreView);
        this.add(view,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);

        gameController.reset();
    }

    public void enableHomeMenu() {
        this.dispose();
        this.remove(highScoreView);
        this.add(homeMenuView,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);

        gameController.reset();
    }

    public void enableHomeMenuFromInfo() {
        this.dispose();
        this.remove(infoView);
        this.add(homeMenuView,BorderLayout.CENTER);
        this.setUndecorated(true);
        initialize();
        this.addWindowFocusListener(this);
    }

    public void enableHighScoreView() {
        this.dispose();
        this.remove(view);
        this.add(highScoreView,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }

    public void enableInfoView() {
        this.dispose();
        this.remove(homeMenuView);
        this.add(infoView,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
        this.addWindowFocusListener(this);
    }

    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


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

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameController.onLostFocus();

    }
}