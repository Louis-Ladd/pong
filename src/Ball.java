import java.util.Random;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Ball extends Block
{
	private int xVel, yVel;
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
					xVel = -(int)(xVel);
					if (block instanceof Ball)
						block.invertXVel();
				}
				if (x - block.getX() > 0)
				{
					xVel = -(int)(xVel);
					if (block instanceof Ball)
						block.invertXVel();
				}
				if (y - block.getY() > 0)
				{
					yVel = -(yVel + randomInt());
					if (block instanceof Ball)
						block.invertYVel();
				}
				if (y - block.getY() < 0)
				{
					yVel = -(yVel + randomInt());
					if (block instanceof Ball)
						block.invertYVel();
				}
			}
		}

		//top
		if (y + yVel <= 30)
		{
			yVel = -yVel + randomInt();
		}
		//bottom
		if (y + yVel > Application.SCREENHEIGHT-height)
		{
			yVel = -yVel + randomInt();
		}
		//left
		if (x + xVel <= 0)
		{
			//isGone = true;
			//sceneObjects.add(new Ball((int)(Application.SCREENWIDTH/2), (int)(Application.SCREENHEIGHT/2), 50, 50, Color.BLUE));
			xVel = -xVel + randomInt();
		}
		//right
		if (x + xVel > Application.SCREENWIDTH-width)
		{
			//isGone = true;
			//sceneObjects.add(new Ball((int)(Application.SCREENWIDTH/2), (int)(Application.SCREENHEIGHT/2), 50, 50, Color.BLUE));
			xVel = -xVel + randomInt();
		}

		if(xVel > width)
		{
			xVel --;
		}
		if(yVel > height)
		{
			yVel--;
		}

		xVel = (int)clamp(xVel, -10,10);
		yVel = (int)clamp(yVel, -10, 10);

		x += xVel;
		y += yVel;
	}

	private int randomInt()
	{
		return new Random().nextInt(-5,5);
	}
}