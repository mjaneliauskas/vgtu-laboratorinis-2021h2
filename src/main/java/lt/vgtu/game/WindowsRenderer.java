package lt.vgtu.game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.List;

import javax.imageio.ImageIO;

public class WindowsRenderer {

	private List<Rocket> rockets;
	private Map map;
	
	private BufferedImage flameImg;
	private BufferedImage rocketImg;
	private BufferedImage asteroidImg;
	
	public WindowsRenderer(Map map, List<Rocket> rockets) throws Exception {
		this.map = map;
		this.rockets = rockets;
		
		rocketImg = ImageIO.read(new FileInputStream("starship.png"));
		flameImg = ImageIO.read(new FileInputStream("rocket-flame.png"));
		asteroidImg = ImageIO.read(new FileInputStream("asteroid.png"));
	}

	public void paint(Graphics g) {
		for (int y=0; y<map.getHeight(); y++) {
			for (int x=0; x<map.getWidth(); x++) {
				int element = map.getElement(x, y);
				
				if (element == 1)
					drawAsteroid(g, x, y);
				else
				if (isRocketTail(x, y))
					drawRocketTail(g, x, y);
				else
				if (isRocketHead(x, y))
					drawRocketHead(g, x, y);
				else
					drawSpace(g, x, y);
			}
		}	
	}

	private void drawSpace(Graphics g, int x, int y) {
	}

	private void drawRocketHead(Graphics g, int x, int y) {
		g.drawImage(rocketImg, 50+x*20, 50+y*20, 20, 20, null);
	}

	private void drawRocketTail(Graphics g, int x, int y) {
		g.drawImage(this.flameImg, 50+x*20, 50+y*20, 20, 20, null);
	}

	private void drawAsteroid(Graphics g, int x, int y) {
		g.drawImage(this.asteroidImg, 50+x*20, 50+y*20, 20, 20, null);
	}
	
	private boolean isRocketHead(int x, int y) {
		for (Rocket rocket: rockets)
			if (rocket.x+1 ==x && rocket.y == y)
				return true;
		
		return false;
	}

	private boolean isRocketTail(int x, int y) {
		for (Rocket rocket: rockets)
			if (rocket.x ==x && rocket.y == y)
				return true;
		
		return false;
	}
}