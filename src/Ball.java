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
		yVel = 4;
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
		for (Block blk : sceneObjects)
		{
			if (this.equals(blk))
			{continue;}

			if (isOverlapping(blk))
			{
				if (x - blk.getX() < 0)
				{
					x -= 10; 
					xVel = -xVel;
				}
				if (x - blk.getX() > 0)
				{
					x += 10;
					xVel = -xVel;
				}
				if (y - blk.getY() > 0)
				{
					yVel = -yVel;
				}
				if (y - blk.getY() < 0)
				{
					yVel = -yVel;
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
			isGone = true;
			xVel = -xVel + randomInt();
		}
		//right
		if (x + xVel > Application.SCREENWIDTH-width)
		{
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

		x += xVel;
		y += yVel;
	}

	private int randomInt()
	{
		return new Random().nextInt(-2,2);
	}
}