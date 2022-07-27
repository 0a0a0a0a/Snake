package game;

import java.awt.*;
import javax.swing.*;

import mainMenu.Game;
import mainMenu.MainMenu;
import stuff.Consts;

import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("all")
public class Field extends JPanel {

	int side;
	
	protected final JButton restart = new JButton("Restart (R)");
	private final JButton quit = new JButton("Exit (Q)");
	
	private final JLabel title = new JLabel("Snake");
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1920, 1080);
		if (Consts.hasTpWalls) {
			g.setColor(Consts.fieldBorder.brighter().brighter().brighter());
		}
		else {
			g.setColor(Consts.fieldBorder);
		}
		g.fillRect(225, 25, 630-(580 % Consts.fieldSide), 630-(580 % Consts.fieldSide));
		g.setColor(Consts.fieldInside1);
		g.fillRect(250, 50, 580-(580 % Consts.fieldSide), 580-(580 % Consts.fieldSide));
		for (int i = 0; i < Consts.fieldSide; i++) {
			for (int e = 0; e < Consts.fieldSide; e++) {
				if (i % 2 == e % 2) {
					g.setColor(Consts.fieldInside2);
					g.fillRect(Consts.inFieldX(i), Consts.inFieldY(e), Consts.square(), Consts.square());
				}
			}
		}
	}
	
	void Start() {
		setLayout(null);
		title.setBounds(13, 25, 200, 65);
		title.setFont(Consts.DefaultFont(0, 60));
		title.setForeground(Color.WHITE);
		title.setVerticalAlignment(SwingConstants.TOP);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		quit.setBounds(13, Consts.getFieldY()-108-10, 200, 65);
		quit.setBackground(Consts.DefaultButtonBG);
		quit.setForeground(Color.WHITE);
		quit.setFont(Consts.DefaultFont(0, 25));
		quit.setFocusPainted(false);
		quit.addMouseListener(Consts.setDefaultHover(quit));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.g.setState(Frame.NORMAL);
				Game.g.toFront();
				Game.runningTask.dispose();
				Game.runningTask = null;
			}
		});
		restart.setBounds(13, Consts.getFieldY()-108-90, 200, 65);
		restart.setBackground(Consts.DefaultButtonBG);
		restart.setForeground(Color.WHITE);
		restart.setFont(Consts.DefaultFont(0, 25));
		restart.setFocusPainted(false);
		restart.addMouseListener(Consts.setDefaultHover(restart));
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.newSnake();
			}
		});
		add(restart);
		add(quit);
		add(title);
	}
	
	public Field(int side) {
		Consts.game = (Snake) Game.runningTask;
		setOpaque(true);
		this.side = side;
		this.setBounds(0, 0, Consts.getFieldX(), Consts.getFieldY());
		this.Start();
	}
	
	public void nextFood() {
		Consts.game.scene.remove(Consts.game.food);
		Consts.game.food = PowerUp.rngSpawn();
		Consts.game.scene.add(Consts.game.food, 3, 3);
	}
}
