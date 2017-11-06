package pkg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.swing.JFrame;

import com.sun.istack.internal.logging.Logger;

public class FrameScreen extends JFrame{
	
	GameScreen gameScreen;
	
	public static ArrayList<User> users;
	
	public FrameScreen()
	{
		users = new ArrayList<User>();
		ReadData();
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UpdateData(); // when close the window => update data
			}
		});
		
		gameScreen = new GameScreen();
		add(gameScreen);
		
		KeyListener listener = new KeyListener() 
				{
			@Override
			public void keyTyped(KeyEvent e)
			{
			}
			@Override
			public void keyReleased (KeyEvent e)
			{
			}
			
			@Override
			public void keyPressed (KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_SPACE)
				{
					gameScreen.isPlaying = !gameScreen.isPlaying;
					if (gameScreen.isGameOver) 
					{
						gameScreen.isGameOver = !gameScreen.isGameOver;
						gameScreen.snake.resetGame();
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP)
				{
					gameScreen.snake.setVector(Snake.GO_UP);
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)
				{
					gameScreen.snake.setVector(Snake.GO_DOWN);
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_LEFT)
				{
					gameScreen.snake.setVector(Snake.GO_LEFT);
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					gameScreen.snake.setVector(Snake.GO_RIGHT);
				}
			}
				};
		addKeyListener(listener);
		setFocusable(true);
	}
	
	public static void main (String [] args)
	{
		FrameScreen f = new FrameScreen();
		f.setSize(750,500);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void UpdateData()
	{
		BufferedWriter bw = null;
		try {
		FileWriter fw = new FileWriter ("data/data.txt");
		bw = new BufferedWriter(fw);
		
		for (User u: users)
		{
			bw.write(u.getName() + " " + u.getScore());
			bw.newLine(); // next line
		}
		
		} catch (IOException ex) {}
		finally {
			try {
				bw.close();
			} catch (IOException e) {}
		}
	}
	
	public static void ReadData()
	{
		try {
			FileReader fr = new FileReader("data/data.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String line = null; // read each line
			while ((line = br.readLine()) != null) // if the line is not null
			{
				String[] str = line.split(" ");
				users.add(new User(str[0], str[1]));
			}
			
			br.close();
		} catch (IOException ex) {}
	}
}
