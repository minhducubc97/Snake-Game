package pkg;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Data {
	
	public static BufferedImage sprite;
	
	public static Image imageHead;
	public static Image imageHead_GoLeft;
	public static Image imageHead_GoRight;
	public static Image imageHead_GoUp;
	public static Image imageHead_GoDown;
	
	public static Image imageBody;
	public static Image imageWorm;
	
	public static Image imageWorm2;
	public static Image imageWorm3;
	
	public static Animation HeadGoUp;
	public static Animation HeadGoDown;
	public static Animation HeadGoLeft;
	public static Animation HeadGoRight;
	
	public static Animation Worm;
	
	public static void loadImage()
	{
		try {
			// if include resource outside the jar file
			// sprite = ImageIO.read(new File ("res/sprite1.png"));
			// else to include resource inside the jar file
			sprite = ImageIO.read(FrameScreen.class.getResource("/res/sprite1.png"));
			
			imageHead = sprite.getSubimage(2, 3, 30, 30);
			imageBody = sprite.getSubimage(6, 79, 20, 20);
			imageHead_GoLeft = sprite.getSubimage(75, 3, 30, 30);
			imageHead_GoRight = sprite.getSubimage(110, 3, 30, 30);
			imageHead_GoUp = sprite.getSubimage(145, 3, 30, 30);
			imageHead_GoDown = sprite.getSubimage(39, 3, 30, 30);
			

			imageWorm = sprite.getSubimage(2, 40, 30, 30);
			imageWorm2 = sprite.getSubimage(32, 40, 30, 30);
			imageWorm3 = sprite.getSubimage(63, 40, 30, 30);
		} catch (Exception e) {}
	}
	
	public static void loadAllAnim()
	{	
		HeadGoUp = new Animation();
		HeadGoUp.addImage(imageHead);
		HeadGoUp.addImage(imageHead_GoUp);
		
		HeadGoDown = new Animation();
		HeadGoDown.addImage(imageHead);
		HeadGoDown.addImage(imageHead_GoDown);
		
		HeadGoRight = new Animation();
		HeadGoRight.addImage(imageHead);
		HeadGoRight.addImage(imageHead_GoRight);
		
		HeadGoLeft = new Animation();
		HeadGoLeft.addImage(imageHead);
		HeadGoLeft.addImage(imageHead_GoLeft);
		
		Worm = new Animation();
		Worm.addImage(imageWorm);
		Worm.addImage(imageWorm2);
		Worm.addImage(imageWorm3);
		Worm.addImage(imageWorm2);
	}
}
