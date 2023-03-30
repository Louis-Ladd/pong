// Louis Harshman - Pong
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Application extends JFrame {
    public static final int SCREENHEIGHT = 600;
    public static final int SCREENWIDTH = 1200;

    private Image dbImage;
    private Graphics dbg;

    private SwingWorker gameLooper;
    private boolean stop;
    
    private int seconds;

    // Terrible static modifier usage 
    // how unfortunate ( ͡° ͜ʖ ͡°)
    public static int leftScore = 0;
    public static int rightScore = 0;

    private ArrayList<Block> sceneObjects = new ArrayList<Block>();
    
    public static boolean[] keys = {false, false, false, false};

    public boolean debugMode;

    public Application(boolean dbm) 
    {
        debugMode = dbm;

        addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keys[0] = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keys[1] = true;
                    break;
                case KeyEvent.VK_W:
                    keys[2] = true;
                    break;
                case KeyEvent.VK_S:
                    keys[3] = true;
                    break;
            }
          }
            public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    keys[0] = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keys[1] = false;
                    break;
                case KeyEvent.VK_W:
                    keys[2] = false;
                    break;
                case KeyEvent.VK_S:
                    keys[3] = false;
                    break;
            }
          }
        });

        setSize(SCREENWIDTH, SCREENHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Pong");


        // Object init
        sceneObjects.add(new Paddle(20, 250, 30, 100, Color.RED, false, 0));
        sceneObjects.add(new Paddle(SCREENWIDTH-50, 250, 30, 100, Color.RED, false, 1)); // Change false to true to play against bot.

        sceneObjects.add(new Ball(300, 200, 50, 50, Color.BLUE));

        seconds = 0;
        stop = false;
        
        gameLooper = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while(!stop) {
                    update();
                    repaint();
                    Thread.sleep(17); // ~60 FPS
                }
                return null;
            }
        };
        
        gameLooper.execute();
    }

    @Override
    public void paint(Graphics g) {
        // Double Buffered image to get around flickering
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();

        paintComponent(dbg);
        
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g)
    {
        g.setFont(new Font("SansSerif", Font.BOLD, 100)); 

        g.drawString("" + leftScore, (int)(SCREENWIDTH*0.25)-50, SCREENHEIGHT/2 +50);
        g.drawString("" + rightScore, (int)(SCREENWIDTH*0.75)-50, SCREENHEIGHT/2 +50);

        for (int i = 0; i < sceneObjects.size(); i++)
        {
            Block obj = sceneObjects.get(i);
            if (obj instanceof Ball)
            {
                if(obj.getIsGone())
                {
                    sceneObjects.remove(i);

                    if (i != 0) // Account for index shift
                        i--;

                    continue;
                }
            }

            obj.logic(sceneObjects);

            obj.draw(g);

            if(debugMode)
            {
                g.setFont(new Font("Comic Sans MS", Font.BOLD, 25)); 
                g.drawString(sceneObjects.get(i).getClass().getSimpleName(), sceneObjects.get(i).getX(), sceneObjects.get(i).getY()-10);
                g.drawString("Objects: " + sceneObjects.size(),20,50);
            }
        }
    }
    
    public void update() 
    {
        seconds++;
    }
}