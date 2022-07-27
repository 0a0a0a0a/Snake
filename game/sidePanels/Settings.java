package sidePanels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import mainMenu.*;
import stuff.Consts;

@SuppressWarnings("all")
public class Settings extends SidePanel {
	
	private final JLabel title = new JLabel("Settings:");
	
	private final JButton changeColor = new JButton("Change Color");
	
	private final JLabel changeFieldSize = new JLabel("Change Field");
	
	private final JButton sideInc = new JButton(">");
	private final JButton sideReset = new JButton(String.valueOf("reset"));
	private final JButton sideDec = new JButton("<");
	
	private final JButton mods = new JButton("Select mods");
	
	private final JButton setTpWalls = new JButton("Set TP Walls");
//	private final JButton setObstacles = new JButton("Set Obstacles");
	private final JButton usePowerUps = new JButton("Use PowerUps");
	private final JButton setTransparent = new JButton("Set Transparency");
	
	private final JPanel ui = new JPanel() {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 700, 700);
			g.setColor(Consts.getFoodColor());
			g.fillRect(450, 100, 25, 25);
			g.setColor(Consts.getHeadColor());
			g.fillRect(500, 100, 25, 25);
			g.setColor(Consts.getBodyColor());
			g.fillRect(525, 100, 75, 25);
			g.fillRect(575, 100, 25, 75);
			g.fillRect(575, 150, 150, 25);
			g.setColor(Color.WHITE);
			g.drawLine(0, 212, 700, 212);
			g.drawLine(25, 0, 25, 600);
			g.setFont(Consts.DefaultFont(2, 20));
			g.setColor(Consts.getFoodColor().darker());
			g.drawString("(food)", 385, 118);
			g.setColor(Consts.getHeadColor().darker());
			g.drawString("(head)", 483, 92);
			g.setColor(Consts.getBodyColor().darker());
			g.drawString("(body)", 614, 140);
			g.setColor(Consts.getBodyColor());
			g.drawString("Current Scheme: " + (Consts.scheme+1), 380, 204);
			g.setColor(Color.WHITE);
			g.drawString("Current Field Size: " + Consts.fieldSide + "x" + Consts.fieldSide, 380, 233);
		}
	};
	
	public void Start() {
		setSize(700, 400);
		setLocationRelativeTo(null);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Game.g.setFocusableWindowState(true);
				Game.runningTask = null;
				Game.g.deBlur();
				dispose();
			}
		});
		setTitle("Snake Settings");
		setResizable(false);
		setVisible(true);
		title.setBounds(50, 25, 400, 145);
		title.setForeground(Color.WHITE);
		title.setFont(Consts.DefaultFont(0, 45));
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setVerticalAlignment(SwingConstants.TOP);
		changeFieldSize.setBounds(50, 223, 400, 100);
		changeFieldSize.setForeground(Color.WHITE);
		changeFieldSize.setFont(Consts.DefaultFont(0, 35));
		changeFieldSize.setHorizontalAlignment(SwingConstants.LEFT);
		changeFieldSize.setVerticalAlignment(SwingConstants.TOP);
		ui.setLayout(null);
		ui.setBounds(0, 0, 700, 700);
		ui.setBackground(Color.BLACK);
		ui.setOpaque(true);
		ui.add(title);
		ui.add(changeFieldSize);
		changeColor.setActionCommand("changeColor");
		changeColor.setBounds(50, 100, 300, 100);
		changeColor.setBackground(Color.BLACK);
		changeColor.setBackground(new Color(5, 5, 5));
		changeColor.setFocusPainted(false);
		changeColor.setFont(Consts.DefaultFont(0, 30));
		changeColor.setForeground(Color.WHITE);
		changeColor.addMouseListener(Consts.setDefaultHover(changeColor));
		changeColor.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Consts.scheme += (Consts.scheme <= Consts.schemesCount) ? 1 : 0;
					if (Consts.scheme == Consts.schemesCount) {
						Consts.scheme = 0;
					}
				}
				else if (e.getButton() == MouseEvent.BUTTON3) {
					Consts.scheme -= (Consts.scheme > 0) ? 1 : ((Consts.schemesCount * -1) + 1);
					if (Consts.scheme == Consts.schemesCount) {
						Consts.scheme = 0;
					}
				}
				ui.repaint(0, 0, 700, 700);
				MainMenu.refresh();
			}
		});
		sideInc.setBounds(235, 270, 75, 75);
		sideInc.setBackground(Consts.DefaultButtonBG);
		sideInc.setFocusPainted(false);
		sideInc.setFont(Consts.DefaultFont(0, 25));
		sideInc.setForeground(Color.WHITE);
		sideInc.addMouseListener(Consts.setDefaultHover(sideInc));
		sideInc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.fieldSide += (Consts.fieldSide < 35) ? 1 : 0;
				ui.repaint(0, 0, 700, 700);
			}
		});
		sideDec.setBounds(50, 270, 75, 75);
		sideDec.setBackground(Consts.DefaultButtonBG);
		sideDec.setFocusPainted(false);
		sideDec.setFont(Consts.DefaultFont(0, 25));
		sideDec.setForeground(Color.WHITE);
		sideDec.addMouseListener(Consts.setDefaultHover(sideDec));
		sideDec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.fieldSide -= (Consts.fieldSide > 5) ? 1 : 0;
				ui.repaint(0, 0, 700, 700);
			}
		});
		sideReset.setBounds(130, 270, 100, 75);
		sideReset.setBackground(Consts.DefaultButtonBG);
		sideReset.setFocusPainted(false);
		sideReset.setFont(Consts.DefaultFont(0, 25));
		sideReset.setForeground(Color.WHITE);
		sideReset.addMouseListener(Consts.setDefaultHover(sideReset));
		sideReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.fieldSide = 22;
				ui.repaint(0, 0, 700, 700);
			}
		});
		mods.setBounds(380, 270, 260, 75);
		mods.setBackground(Consts.DefaultButtonBG);
		mods.setForeground(Color.WHITE);
		mods.setFont(Consts.DefaultFont(0, 30));
		mods.setFocusPainted(false);
		mods.addMouseListener(Consts.setDefaultHover(mods));
		mods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (getHeight() == 400) {
					setSize(700, 600);
					setLocationRelativeTo(null);
					ui.repaint(0, 0, 700, 700);
					ui.add(setTpWalls);
//					ui.add(setObstacles);
					ui.add(usePowerUps);
					ui.add(setTransparent);
				}
				else {
					setSize(700, 400);
					setLocationRelativeTo(null);
					ui.repaint(0, 0, 700, 700);
					ui.remove(setTpWalls);
//					ui.remove(setObstacles);
					ui.remove(usePowerUps);
					ui.remove(setTransparent);
				}
			}
		});
		setTpWalls.setBounds(50, 365, 260, 60);
		setTpWalls.setBackground(Consts.getSwitchStatusBG(Consts.hasTpWalls));
		setTpWalls.setForeground(Color.WHITE);
		setTpWalls.setFocusPainted(false);
		setTpWalls.setFont(Consts.DefaultFont(0, 25));
		setTpWalls.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setTpWalls.setBackground(Consts.getSwitchStatusBG(Consts.hasTpWalls)
						.darker().darker().darker());
			}
			public void mouseExited(MouseEvent e) {
				setTpWalls.setBackground(Consts.getSwitchStatusBG(Consts.hasTpWalls));
			}
		});
		setTpWalls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.hasTpWalls = ! Consts.hasTpWalls;
				setTpWalls.setBackground(Consts.getSwitchStatusBG(Consts.hasTpWalls)
						.darker().darker().darker());
			}
		});
//		setObstacles.setBounds(380, 365, 260, 60);
//		setObstacles.setBackground(Consts.getSwitchStatusBG(Consts.hasObstacles));
//		setObstacles.setForeground(Color.WHITE);
//		setObstacles.setFocusPainted(false);
//		setObstacles.setFont(Consts.DefaultFont(0, 25));
//		setObstacles.addMouseListener(new MouseAdapter() {
//			public void mouseEntered(MouseEvent e) {
//				setObstacles.setBackground(Consts.getSwitchStatusBG(Consts.hasObstacles)
//						.darker().darker().darker());
//			}
//			public void mouseExited(MouseEvent e) {
//				setObstacles.setBackground(Consts.getSwitchStatusBG(Consts.hasObstacles));
//			}
//		});
//		setObstacles.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				Consts.hasObstacles = ! Consts.hasObstacles;
//				setObstacles.setBackground(Consts.getSwitchStatusBG(Consts.hasObstacles)
//						.darker().darker().darker());
//			}
//		});
		usePowerUps.setBounds(210, 465, 260, 60);
		usePowerUps.setBackground(Consts.getSwitchStatusBG(Consts.hasPwups));
		usePowerUps.setForeground(Color.WHITE);
		usePowerUps.setFocusPainted(false);
		usePowerUps.setFont(Consts.DefaultFont(0, 25));
		usePowerUps.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				usePowerUps.setBackground(Consts.getSwitchStatusBG(Consts.hasPwups)
						.darker().darker().darker());
			}
			public void mouseExited(MouseEvent e) {
				usePowerUps.setBackground(Consts.getSwitchStatusBG(Consts.hasPwups));
			}
		});
		usePowerUps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.hasPwups = ! Consts.hasPwups;
				usePowerUps.setBackground(Consts.getSwitchStatusBG(Consts.hasPwups)
						.darker().darker().darker());
			}
		});
		setTransparent.setBounds(380, 365, 260, 60);
		setTransparent.setBackground(Consts.getSwitchStatusBG(Consts.isTransparent));
		setTransparent.setForeground(Color.WHITE);
		setTransparent.setFocusPainted(false);
		setTransparent.setFont(Consts.DefaultFont(0, 25));
		setTransparent.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				setTransparent.setBackground(Consts.getSwitchStatusBG(Consts.isTransparent)
						.darker().darker().darker());
			}
			public void mouseExited(MouseEvent e) {
				setTransparent.setBackground(Consts.getSwitchStatusBG(Consts.isTransparent));
			}
		});
		setTransparent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.isTransparent = ! Consts.isTransparent;
				setTransparent.setBackground(Consts.getSwitchStatusBG(Consts.isTransparent)
						.darker().darker().darker());
			}
		});
		ui.add(sideInc);
		ui.add(sideReset);
		ui.add(sideDec);
		ui.add(changeColor);
		ui.add(changeFieldSize);
		ui.add(mods);
		add(ui);
	}

	public void run() {
		if (Game.runningTask == null) {
			Game.runningTask = this;
			this.Start();
		}
	}
}
