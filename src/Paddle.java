import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Paddle extends Block
{
	private int yVel;
	private boolean isBot;

	public Paddle(int x, int y, int w, int h, Color c, boolean isB)
	{
		super(x,y,w,h,c);
		this.isBot = isB;
	}
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	public void logic(ArrayList<Block> sceneObjects, Point p)
	{
		if (isBot)
		{
			for (Block blk : sceneObjects)
			{
				if (blk instanceof Ball)
				{
					y = blk.getY()-height/2;
				}
			}
			return;
		}
		y = (int)(p.getY() - height);
	}

	/*
	public void logic(ArrayList<Block> sceneObjects)
	{

		for (Block obj : sceneObjects)
		{
			if (obj instanceof Ball && Math.abs(x + width - obj.getX()) < 150)
			{
				y = obj.getY();
			}
		}
	}*/
}