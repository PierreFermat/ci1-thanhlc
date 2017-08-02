package touhou;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/**
 * Created by huynq on 7/29/17.
 */
public class GameWindow extends Frame {

    private long lastTimeUpdate;
    private long currentTime;
    private Graphics2D windowGraphics;
    private Graphics2D backBufferGraphics;
    private BufferedImage backBufferImage;
    private BufferedImage background;
    private BufferedImage player_Straight;
    private BufferedImage player_Right;
    private BufferedImage player_Left;

    private int playX = 178;
    private int playY = 600;
    private int dx ;
    private int dy ;
    private int i;


    private int backgroundy = -2400;


    public GameWindow() {
        background = tklibs.SpriteUtils.loadImage("assets/images/background/0.png");
        player_Straight = tklibs.SpriteUtils.loadImage("assets/images/players/straight/0.png");
        player_Left = tklibs.SpriteUtils.loadImage("assets/images/players/left/0.png");
        player_Right = tklibs.SpriteUtils.loadImage("assets/images/players/right/0.png");
        setupGameLoop();
        setupWindow();
    }

    private void setupGameLoop() {
        lastTimeUpdate = -1;
    }

    private void setupWindow() {
        this.setSize(1024, 768);
        this.setTitle("Touhou-Remade by Thanh Le Cong");
        this.backBufferImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.backBufferGraphics = (Graphics2D) this.backBufferImage.getGraphics();

        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

        });
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    dx = 4;
                    i = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    dx = -4;
                    i = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    dy = -4;
                    i =3;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    dy = 4;
                    i=3;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT){
                    dx = 0;

                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT){
                    dx = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    dy = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    dy = 0;
                }

            }
        });
    }




    public void loop() {
        while (true) {
            if (lastTimeUpdate == -1) {
                lastTimeUpdate = System.currentTimeMillis();

            }
            currentTime = System.currentTimeMillis();
            if (currentTime - lastTimeUpdate > 17) {
                run();
                render();
                lastTimeUpdate = currentTime;
            }
        }
    }

    private void run() {
        playX += dx;
        playY += dy;
        if (playX < 0){
            playX = 0;
        }
        if (playY < 0){
            playY = 0;
        }
        if ( playY > 740){
            playY = 740;
        }
        if (playX > 365) {
            playX = 365;
        }

    }

    @Override
    public void paint(Graphics g) {
        backBufferGraphics.setColor(Color.BLACK);
        backBufferGraphics.fillRect(0,0,1024,768);
        backBufferGraphics.drawImage(background,0,backgroundy,null);
        backgroundy += 1;
        if (backgroundy > 0) {
            backgroundy = 0;
        }
        if (i==1){
            backBufferGraphics.drawImage(player_Right, playX,playY ,null);
        }
        if (i==2){
            backBufferGraphics.drawImage(player_Left, playX,playY ,null);
        }
        if (i==3){
            backBufferGraphics.drawImage(player_Straight , playX,playY ,null);
        }

        g.drawImage(backBufferImage, 0, 0, null);
    }

    private void render() {
        repaint();
    }
}
