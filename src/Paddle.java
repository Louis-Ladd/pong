// Louis Harshman - Pong
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Paddle extends Block
{
	private boolean isBot;
	private int id;

	public Paddle(int x, int y, int w, int h, Color c, boolean isBot, int id)
	{
		super(x,y,w,h,c);
		this.isBot = isBot;
		this.id = id;
	}

	@Override
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	@Override
	public void logic(ArrayList<Block> sceneObjects)
	{
		y = (int)clamp(y, 0, Application.SCREENHEIGHT - height);
		if (isBot)
		{
			botLogic(sceneObjects);
		}

		else 
		{
			playerLogic(sceneObjects);
		}

	}

	public void playerLogic(ArrayList<Block> sceneObjects)
	{
		boolean up = Application.keys[0];
		boolean down = Application.keys[0];

		switch (id)
		{
			case 0:
				up = Application.keys[2];
				down = Application.keys[3];
				break;
			case 1:
				up = Application.keys[0];
				down = Application.keys[1];
				break;
		}

		if (up)
		{
			yVel -= 2;
		}
		if (down)
		{
			yVel += 2;
		}

		yVel = (int)clamp(yVel, -16, 16);
		y += yVel;

		if (!(up || down) && yVel != 0)
			yVel += yVel > 0 ? -1 : 1;
	}

	public void botLogic(ArrayList<Block> sceneObjects)
	{
		y = (int)clamp(y, 0, Application.SCREENHEIGHT-height);
		for (Block blk : sceneObjects)
		{
			if (!(blk instanceof Ball))
			{continue;}

			if (Math.abs(x + (int)(width/2) - blk.getX()) > 500)
			{
				if (yVel != 0)
				{
					yVel += yVel < 0 ? 1 : -1;
				}

				continue;
			}
			if (y+(int)(height/2)+yVel - blk.getY() < 20)
			{
				yVel ++;
			}
			else if (y+(int)(height/2)+yVel - blk.getY() > 20)
			{
				yVel --;
			}
			
		}

		y += clamp(yVel,-8,8);
	}
}