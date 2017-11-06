package pkg;
 
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.AttributedCharacterIterator;
import java.awt.Color;

public class GameScreen extends JPanel implements Runnable {
	
	static int [][] bg = new int [20][20];
	
	static int padding = 10;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	
	static boolean isPlaying = false;
	static boolean enableTextStartGame = true;
	
	Snake snake;
	
	static boolean isGameOver = false;
	
	static int CurrentLevel = 1;
	
	static int Score = 0;
	
	public GameScreen()
	{
		snake = new Snake();
		Data.loadImage();
		Data.loadAllAnim();
		
		Thread thread = new Thread(this);
		thread.start();
		bg[10][10] = 10;
	}
	
	private void veKhung (Graphics2D g)
	{
		g.setColor(Color.ORANGE);
		g.drawRect(0, 0, WIDTH+padding*2, HEIGHT+padding*2);
		g.drawRect(1, 1, WIDTH+padding*2-2, HEIGHT+padding*2-2);
		g.drawRect(2, 2, WIDTH+padding*2-4, HEIGHT+padding*2-4);
		
		g.drawRect(0, 0, WIDTH+padding*2+300, HEIGHT+padding*2);
		g.drawRect(1, 1, WIDTH+padding*2-2+300, HEIGHT+padding*2-2);
		g.drawRect(2, 2, WIDTH+padding*2-4+300, HEIGHT+padding*2-4);
	}
	
	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		paintBg(g2d);
		snake.drawSnake(g2d);
		veKhung(g2d);
		
		if (!isPlaying)
		{
			if (enableTextStartGame)
			{
				g.setColor(Color.WHITE);
				g.setFont(g.getFont().deriveFont(18.0f));
				g.drawString("PRESS SPACE TO PLAY GAME", 78, 200);
			}
		}
		
		if (isGameOver)
		{
			g.setColor(Color.RED);
			g.setFont(g.getFont().deriveFont(28.0f));
			g.drawString("GAME OVER", 125, 350);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(28.0f));
		g.drawString("LEVEL: " + CurrentLevel, 450, 50);
		
		g.setColor(Color.WHITE);
		g.setFont(g.getFont().deriveFont(20.0f));
		g.drawString("Score: " + Score, 450, 90);
		
		for (int i = 0; i < FrameScreen.users.size(); i++)
		{
			g.drawString(FrameScreen.users.get(i).toString(), 450, i*30+150);
		}
	}
	
	public void paintBg(Graphics g)
	{
		Graphics2D g2dbg = (Graphics2D) g;
		g2dbg.setColor(Color.BLACK);
		g2dbg.fillRect(0, 0, WIDTH+padding*2+300, HEIGHT+padding*2);
		for (int i = 0; i < 20; i++)
		{
			for (int j = 0; j < 20; j++)
			{
				//g2dbg.fillRect(20*i+1, 20*j+1, 18, 18);
				if (bg[i][j] == 10)
				{
					g2dbg.drawImage(Data.Worm.getCurrentImage(), i*20-7+padding, j*20-7+padding, null);
				}
			}
		}
	}
	
	public void run()
	{
		long t = 0;
		long t2 = 0;
		while (true)
		{
			if (System.currentTimeMillis() - t2 > 500)
			{
				enableTextStartGame = !enableTextStartGame;
				t2 = System.currentTimeMillis();
			}
			if (isPlaying)
			{
				if (System.currentTimeMillis() - t > 200)
				{
					Data.Worm.update();
					t = System.currentTimeMillis();
				}
				snake.update();
			}
			
			repaint();
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
		}
	}
}
