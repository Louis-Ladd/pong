import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Point;


public class Application extends JFrame {
    public static final int SCREENHEIGHT = 600;
    public static final int SCREENWIDTH = 800;

    private Image dbImage;
    private Graphics dbg;

    private SwingWorker gameLooper;
    private boolean stop;
    
    private int seconds;

    private ArrayList<Block> sceneObjects = new ArrayList<Block>();
    
    public Application() 
    {
        setSize(SCREENWIDTH, SCREENHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Pong");


        //Object init
        sceneObjects.add(new Paddle(20, 250, 30, 100, Color.RED, false));
        sceneObjects.add(new Paddle(SCREENWIDTH-50, 250, 30, 100, Color.RED, true));
        sceneObjects.add(new Ball(50, 50, 50, 50, Color.BLUE));

        seconds = 0;
        stop = false;
        
        gameLooper = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while(!stop) {
                    update();
                    repaint();
                    Thread.sleep(17); //~60 FPS
                }
                return null;
            }
        };
        
        gameLooper.execute();
    }

    @Override
    public void paint(Graphics g) {
        //Double Buffered image
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }

    public void paintComponent(Graphics g)
    {
        for (int i = 0; i < sceneObjects.size(); i++)
        {
            Block obj = sceneObjects.get(i);
            if (obj instanceof Ball)
            {
                if(obj.getIsGone())
                {
                    sceneObjects.remove(i);

                    if (i != 0)
                        i--;

                    continue;
                }
            }
            if (obj instanceof Paddle)
            {
                obj.logic(sceneObjects, MouseInfo.getPointerInfo().getLocation());
            }
            else
            {
                obj.logic(sceneObjects);
            }
            obj.draw(g);
        }
    }
    
    public void update() 
    { //DON'T update the UI in this method. Game logic ONLY!
        seconds++;
    }
}