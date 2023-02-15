
import java.awt.Graphics;
public class Block
{
	protected int x,y,width,height;

	public Block(int x, int y, int w, int h)
	{
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}

	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}

	public void draw(Graphics g)
	{
		g.fillRect(x,y,width,height);
	}

	public void logic()
	{
	}


	public String toString()
	{
		return "block at x: " + x + " y: " + y;
	}
}