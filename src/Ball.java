// Louis Harshman - Pong
import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block
{
	private boolean isGone;

	public Ball(int x, int y, int w, int h, Color c)
	{
		super(x,y,w,h,c);
		isGone = false;
		xVel = 5;
		yVel = randomInt();
	}

	@Override
	public void invertXVel()
	{
		xVel = -xVel;
	}
	@Override
	public void invertYVel()
	{
		yVel = -yVel;
	}

	@Override
	public boolean getIsGone()
	{
		return isGone;
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
		for (Block block : sceneObjects)
		{
			if (this.equals(block))
			{continue;}

			if (isOverlapping(block))
			{
				if (x - block.getX() < 0)
				{
					xVel = -(int)(xVel+2);
					if (block instanceof Ball)
						block.invertXVel();
					x -= Math.abs(xVel);
				}
				if (x - block.getX() > 0)
				{
					xVel = -(int)(xVel+2);
					if (block instanceof Ball)
						block.invertXVel();
					x += Math.abs(xVel);
				}
				if (y - block.getY() > 0)
				{
					yVel = (yVel + (int)(block.getYVel()/2));
					if (block instanceof Ball)
						block.invertYVel();
				}
				if (y - block.getY() < 0)
				{
					yVel = (yVel + (int)(block.getYVel()/2));
					if (block instanceof Ball)
						block.invertYVel();
				}
			}
		}

		// top of screen
		if (y + yVel <= 30)
		{
			yVel = -yVel-1;
		}
		// bottom of screen
		if (y + yVel > Application.SCREENHEIGHT-height)
		{
			yVel = -yVel-1;
		}
		// left of screen
		if (x + xVel <= 0)
		{
			isGone = true;
			sceneObjects.add(new Ball((int)(Application.SCREENWIDTH/2), (int)(Application.SCREENHEIGHT/2), 50, 50, Color.BLUE));
			xVel = -xVel;
			Application.rightScore ++;
		}
		// right of screen
		if (x + xVel > Application.SCREENWIDTH-width)
		{
			isGone = true;
			sceneObjects.add(new Ball((int)(Application.SCREENWIDTH/2), (int)(Application.SCREENHEIGHT/2), 50, 50, Color.BLUE));
			xVel = -xVel;
			Application.leftScore ++;
		}

		if(xVel > width)
		{
			xVel --;
		}
		if(yVel > height)
		{
			yVel--;
		}

		xVel = (int)clamp(xVel, -20,20);
		yVel = (int)clamp(yVel, -20,20);

		x += xVel;
		y += yVel;
	}

	private int randomInt()
	{
		return new Random().nextInt(-5,5);
	}
}