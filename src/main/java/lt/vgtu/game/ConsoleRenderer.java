package lt.vgtu.game;

import java.util.List;

public class ConsoleRenderer {

	private List<Rocket> rockets;
	private Map map;

	public ConsoleRenderer(Map map, List<Rocket> rockets) {
		this.map = map;
		this.rockets = rockets;
	}

	public void render() {
		for (int y=0; y<map.getHeight(); y++) {
			for (int x=0; x<map.getWidth(); x++) {
				int element = map.getElement(x, y);
				if (element == 1)
					drawAsteroid();
				else
				if (isRocketTail(x, y))
					drawRocketTail();
				else
				if (isRocketHead(x, y))
					drawRocketHead();
				else
					drawSpace();
			}
			System.out.println();
		}
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

	private static void drawSpace() {
		System.out.print(" ");
	}

	private static void drawRocketHead() {
		System.out.print(">");
	}

	private static void drawRocketTail() {
		System.out.print("<");
	}

	private static void drawAsteroid() {
		System.out.print("*");
	}
}
