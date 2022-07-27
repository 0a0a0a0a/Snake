package mainMenu;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;

import game.Snake;
import stuff.Consts;
import sidePanels.*;

public class Game extends JFrame {

	public static final Game g = new Game();
	
	public static SidePanel runningTask;
	
	public MainMenu menu;
	
	public void blur() {
		this.getGlassPane().setVisible(true);
	}
	public void deBlur() {
		this.getGlassPane().setVisible(false);
	}
	
	void Start() {
		setSize(1360, 768);
		setLocationRelativeTo(null);
		setLayout(null);
		
		setGlassPane(new JComponent() {
			protected void paintComponent(Graphics g) {
				g.setColor(new Color(0, 0, 0, 200));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		});
		
		menu = new MainMenu();
		add(menu);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (runningTask != null) {
					runningTask.dispose();
				}
				dispose();
			}
		});

		setTitle("Snake");
		setResizable(false);
		setVisible(true);
	}
	
	void Update() {
		menu.fadeIn(5);
	}
	
	public static void main(String[] args) {
		g.Start();
		g.Update();
	}
}
