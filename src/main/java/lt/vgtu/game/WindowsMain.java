package lt.vgtu.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class WindowsMain  extends JFrame implements KeyListener{
	private static final long serialVersionUID = 1L;
	
	private List<Rocket> rockets = new ArrayList<>();
	private Map map = new Map();
	private WindowsRenderer renderer = new WindowsRenderer(map, rockets);
	private GameRules gameRules = new GameRules(map, rockets);
	
	public WindowsMain() throws Exception {
		super.setPreferredSize(new Dimension(1200, 600));
		super.pack();
		super.setVisible(true);
		
		rockets.add(new Rocket(3, 10, "wsad"));
		rockets.add(new Rocket(5, 12, "ikjl"));
		
		super.addKeyListener(this);
		
		super.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		renderer.paint(g);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int userCommand = e.getKeyChar();
		gameRules.processUserCommand(userCommand);
		
		for (Rocket rocket: rockets)
			rocket.processUserCommand(userCommand);
		
		if (gameRules.isGameOver())
			System.exit(1);
		
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			
			@Override
			public void run() {
				try {
					new WindowsMain();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
