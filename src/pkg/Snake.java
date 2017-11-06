package pkg;

import java.awt.Color;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake {
	
	int length = 1;
	int[] x;
	int[] y;
	
	public static final int GO_UP = 1;
	public static final int GO_DOWN = -1;
	public static final int GO_LEFT = 2;
	public static final int GO_RIGHT = -2;
	
	int vector = this.GO_DOWN;
	
	long t1 = 0;
	long t2 = 0;
	
	int speed = 200;
	
	boolean updateAfterChangeVt = true;
	
	int flag = 0;
	
	public Snake()
	{
		x = new int[100];
		y = new int[100];
		
		x[0] = 5;
		y[0] = 4;
		
//		x[1] = 5;
//		y[1] = 3;
//		
//		x[2] = 5;
//		y[2] = 2;
	}
	
	public void resetGame()
	{
		x = new int[100];
		y = new int[100];
		
		x[0] = 5;
		y[0] = 4;
		
//		x[1] = 5;
//		y[1] = 3;
//		
//		x[2] = 5;
//		y[2] = 2;
		
		length = 1;
		vector = this.GO_DOWN;
		GameScreen.Score = 0;
		GameScreen.CurrentLevel = 1;
		speed = 200;
	}
	
	public void setVector(int v)
	{
		if (vector != -v && updateAfterChangeVt) // this allows the change only after the snake head has changed direction
		{
			vector = v;
			updateAfterChangeVt = false;
		}
	}
	
	public boolean location_in_snake(int x1, int y1)
	{
		for (int i = 0; i < length; i++)
		{
			if (x[i] == x1 && y[i] == y1)
			{
				return true;
			}
		}
		return false;
	}
	
	public Point location()
	{
		Random r = new Random();
		int x;
		int y;
		do {
			x = r.nextInt(19);
			y = r.nextInt(19);
		} while (location_in_snake(x,y));
		return new Point(x,y);
	}
	
	public void update()
	{
		if (flag == 5)
		{
			GameScreen.CurrentLevel++;
			flag = 0;
		}
		
		for (int i = 1; i<length; i++)
		{
			if (x[0]==x[i] && y[0]==y[i])
			{
				String name = JOptionPane.showInputDialog("Please enter your name: ");
				FrameScreen.users.add(new User(name, String.valueOf(GameScreen.Score)));
				
				GameScreen.isPlaying = false;
				GameScreen.isGameOver = true;
			}
		}
		
		if (System.currentTimeMillis() - t2 > speed)
		{
			
			Data.HeadGoUp.update();
			Data.HeadGoDown.update();
			Data.HeadGoRight.update();
			Data.HeadGoLeft.update();
			
			updateAfterChangeVt = true;
			
			t2 = System.currentTimeMillis();
		}
		
		if (System.currentTimeMillis() - t1 > speed)
		{
			
			if (GameScreen.bg[x[0]][y[0]] == 10)
			{
				length++;
				flag++;
				speed = speed - 5;
				GameScreen.Score += 100;
				GameScreen.bg[x[0]][y[0]] = 0; // destroy that food
				GameScreen.bg[location().x][location().y] = 10; // create new food
			}
			for (int i = length - 1; i > 0; i--)
			{
				x[i] = x[i-1];
				y[i] = y[i-1];
			}
			if (vector == this.GO_UP) y[0]--;
			else if (vector == this.GO_DOWN) y[0]++;
			else if (vector == this.GO_LEFT) x[0]--;
			else if (vector == this.GO_RIGHT) x[0]++;
			
			if (x[0] < 0) x[0] = 19;
			if (x[0] > 19) x[0] = 0;
			if (y[0] < 0) y[0] = 19;
			if (y[0] > 19) y[0] = 0;
			t1 = System.currentTimeMillis();
		}
	}
	 
	public void drawSnake(Graphics g)
	{
		Graphics2D g2ds = (Graphics2D) g;
		g2ds.setColor(Color.red);
		for (int i = 1; i < length; i++)
		{
			g2ds.drawImage(Data.imageBody, x[i]*20+GameScreen.padding, y[i]*20+GameScreen.padding, null);
		}
		if (vector == Snake.GO_UP)
		{
			g2ds.drawImage(Data.HeadGoUp.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null); // the head of the snake
		}
		else if (vector == Snake.GO_DOWN)
		{
			g2ds.drawImage(Data.HeadGoDown.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
		}
		else if (vector == Snake.GO_LEFT)
		{
			g2ds.drawImage(Data.HeadGoLeft.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
		}
		else if (vector == Snake.GO_RIGHT)
		{
			g2ds.drawImage(Data.HeadGoRight.getCurrentImage(), x[0]*20-6+GameScreen.padding, y[0]*20-6+GameScreen.padding, null);
		}
	}
	
}
