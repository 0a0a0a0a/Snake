package mainMenu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import stuff.*;
import game.*;
import sidePanels.*;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class MainMenu extends JPanel {
	
	private static Thread game = new Thread(new Snake());
	
	private static final JLabel title = new JLabel("Snake");
	
	private static final JButton play = new JButton("Play");
	
	private static final JButton settings = new JButton("Settings");
	
	private static final JButton quit = new JButton("Quit");
	
	public static void refresh() {
		Game.g.menu.removeAll();
		Game.g.menu.repaint(0, 0, 1360, 768);
		Game.g.menu.add(title);
		Game.g.menu.add(play);
		Game.g.menu.add(settings);
		Game.g.menu.add(quit);
	}
	
	public static void newSnake() {
		game = new Thread(new Snake());
		game.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Consts.getBodyColor());
		g.fillRect(45, 170, 410, 30);
		g.fillRect(45, 0, 30, 170);
		g.setColor(Consts.getBodyColor().darker().darker().darker());
		g.fillRect(455, 170, 450, 30);
		g.setColor(Consts.getBodyColor());
		g.fillRect(905, 170, 300, 30);
		g.fillRect(1175, 170, 30, 450);
		g.fillRect(755, 620, 450, 30);
		g.fillRect(725, 390, 30, 200);
		g.fillRect(295, 390, 430, 30);
		g.fillRect(295, 390, 30, 130);
		g.setColor(Consts.getHeadColor());
		g.fillRect(295, 520, 30, 30);
		g.setColor(Consts.getFoodColor());
		g.fillRect(295, 590, 30, 30);
	}
	
	public MainMenu() {
		setBounds(0, 0, 1360, 768);
		setBackground(Color.BLACK);
		setLayout(null);
		title.setBounds(0, 100, 1360, 500);
		title.setFont(Consts.DefaultFont(0, 160));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setVerticalAlignment(JLabel.TOP);
		title.setForeground(Color.BLACK);
		play.setBounds(480, 350, 400, 100);
		play.setVerticalAlignment(SwingConstants.CENTER);
		play.setBackground(Consts.DefaultButtonBG);
		play.setForeground(Color.WHITE);
		play.setFocusPainted(false);
		play.setFont(new Font("Calibri", 0, 40));
		play.addMouseListener(Consts.setDefaultHover(play));
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (! (Game.runningTask instanceof Settings)) {
					Game.g.setState(Frame.ICONIFIED);
					newSnake();
				}
			}
		});
		settings.setBounds(480, 470, 400, 100);
		settings.setVerticalAlignment(SwingConstants.CENTER);
		settings.setBackground(Consts.DefaultButtonBG);
		settings.setForeground(Color.WHITE);
		settings.setFocusPainted(false);
		settings.setFont(new Font("Calibri", 0, 40));
		settings.addMouseListener(Consts.setDefaultHover(settings));
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.g.blur();
				Game.g.setFocusableWindowState(false);
				new Thread(new Settings()).start();
			}
		});
		quit.setBounds(480, 590, 400, 100);
		quit.setVerticalAlignment(SwingConstants.CENTER);
		quit.setBackground(Consts.DefaultButtonBG);
		quit.setForeground(Color.WHITE);
		quit.setFocusPainted(false);
		quit.setFont(new Font("Calibri", 0, 40));
		quit.addMouseListener(Consts.setDefaultHover(quit));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Game.runningTask != null) {
					Game.runningTask.dispose();
				}
				Game.g.dispose();
			}
		});
		setOpaque(true);
		add(title);
		add(play);
		add(settings);
		add(quit);
	}
	
	public void fadeIn(int sleepTime) {
		for (int i = 0; i < 255; i+=2) {
			title.setForeground(new Color(i, i, i));
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fadeOut(int sleepTime) {
		for (int i = 255; i >= 0; i-=2) {
			title.setForeground(new Color(i, i, i));
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
