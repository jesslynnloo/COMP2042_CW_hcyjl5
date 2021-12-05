package test.view;

import test.controller.InfoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class InfoView extends JComponent implements MouseListener, MouseMotionListener {


    private GameFrame owner;

    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;


    private Rectangle backButton;

    private Rectangle menuFace;

    private static final String INFO_TEXT = "Info Screen";
    private static final String BACK_TEXT = "Back";

    private static final Color BG_COLOR = Color.WHITE.darker();
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private Font buttonFont;
    private Font titleFont;

    private InfoController infoController = new InfoController(this);

    int stringHeight;

    public InfoView(GameFrame owner) {
        this.owner = owner;
        this.initialize();


        menuFace = new Rectangle(new Point(0,0),new Dimension(DEF_WIDTH,DEF_HEIGHT));

        Dimension btnDim = new Dimension(DEF_WIDTH / 5, DEF_HEIGHT / 15);
        backButton = new Rectangle(btnDim);

        titleFont = new Font("Noto Mono",Font.PLAIN,25);
        buttonFont = new Font("Monospaced",Font.PLAIN,backButton.height-2);
    }

    private void initialize(){
        this.setPreferredSize(new Dimension(DEF_WIDTH,DEF_HEIGHT));
        this.setLayout(new BorderLayout());
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }

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

    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        g2d.draw(menuFace);

        g2d.setColor(prev);
    }

    private void drawAllText(Graphics2D g2d){
        drawText(g2d, titleFont, INFO_TEXT);

    }

    private void drawText(Graphics2D g2d, Font font, String text){
        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();
        Rectangle2D textRect = font.getStringBounds(text, frc);

        int sX;

        sX = (int)(menuFace.getWidth() - textRect.getWidth()) / 2;

        if(text.equals(INFO_TEXT)){
            stringHeight = (int)(menuFace.getHeight() / 12);
        }
        else{
            stringHeight += (int) textRect.getHeight() * 1.1;//add 10% of String height between the two strings
        }

        g2d.setFont(font);
        g2d.drawString(text,sX,stringHeight);

    }

    private void drawAllButton(Graphics2D g2d){
        drawButton(g2d, backButton, BACK_TEXT, infoController.isBackClicked());
    }

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

    @Override
    public void mouseClicked(MouseEvent e) {
        infoController.checkMouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        infoController.checkMousePressed(e);
    }

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

    @Override
    public void mouseMoved(MouseEvent e) {
        infoController.checkMouseMoved(e);
    }

    public void repainting (Rectangle button) {
        repaint(button.x,button.y,button.width+1,button.height+1);
    }

    public void settingHandCursor () {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void settingDefaultCursor () {
        this.setCursor(Cursor.getDefaultCursor());
    }

    public Rectangle getBackButton() {
        return backButton;
    }

    public GameFrame getOwner() {
        return owner;
    }

}
