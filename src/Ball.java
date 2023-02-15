
import java.awt.Graphics;
public class Ball extends Block
{
	private int xVel, yVel;

	public Ball(int x, int y, int w, int h)
	{
		super(x,y,w,h);
		xVel = 40;
		yVel = -20;
	}

	@Override
	public void draw(Graphics g)
	{
		g.fillOval(x, y, width, height);
	}

	@Override
	public void logic()
	{
		//top
		if (y+yVel < 0+height*2)
		{
			yVel = -yVel;
		}
		//bottom
		if (y+yVel > 600-height*2)
		{
			yVel = -yVel;
		}
		//left
		if (x+xVel < 0-width)
		{
			xVel = -xVel;
		}
		if (x+xVel > 800-width)
		{
			xVel = -xVel;
		}

		x += xVel;
		y += yVel;
	}
}