
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.SwingWorker;

public class Application extends JFrame {
    private SwingWorker gameLooper;
    private boolean stop;
    
    private int seconds;

    private ArrayList<Block> sceneObjects = new ArrayList<Block>();
    
    public Application() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Object init
        sceneObjects.add(new Ball(50,100,20,20));

        seconds = 0;
        stop = false;
        
        gameLooper = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                while(!stop) {
                    update();
                    repaint();
                    Thread.sleep(50); //lazy 1 FPS & 1 update per second
                }
                return null;
            }
        };
        
        gameLooper.execute();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //Prevents smearing
        for (Block obj : sceneObjects)
        {
            obj.draw(g);
            obj.logic();
        }
        getGraphics().setColor(Color.BLACK);
        g.drawString("second: " + seconds, 200, 200);
    }
    
    public void update() 
    { //DON'T update the UI in this method. Game logic ONLY!
        seconds++;
    }
}