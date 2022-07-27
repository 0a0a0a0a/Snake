package game;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import stuff.*;
import mainMenu.*;
import sidePanels.*;

@SuppressWarnings("all")
public class Snake extends SidePanel {

	private final JLabel pauseLabel = new JLabel("Pause");
	
	JLayeredPane scene;
	
	boolean pause = false;
	int rate = 128;
	boolean cooldown = false;
	
	int score = 0;
	JLabel scoreLabel = new JLabel("Score: " + score);
	
	Field field;
	
	List<BPart> body = new ArrayList<BPart>();
	
	Head head;
	
	PowerUp food;
	
	void frame() {
		try {
			Thread.sleep(rate);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void Start() {
		
		scene = getLayeredPane();
		scene.setBounds(0, 0, 900, 700);
		
		setSize(Consts.getFieldDim());
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Snake Game");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				Game.g.setState(Frame.NORMAL);
				Game.g.toFront();
				Game.runningTask.dispose();
				Game.runningTask = null;
			}
		});
		
		scoreLabel.setBounds(13, 100, 200, 65);
		scoreLabel.setFont(Consts.DefaultFont(0, 30));
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setVerticalAlignment(SwingConstants.TOP);
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		pauseLabel.setBounds(13, 150, 200, 65);
		pauseLabel.setFont(Consts.DefaultFont(0, 20));
		pauseLabel.setForeground(Color.WHITE);
		pauseLabel.setVerticalAlignment(SwingConstants.TOP);
		pauseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scene.add(scoreLabel, 1, 1);
		
		if (PowerUp.trigger.isAlive()) {
			Effect.kill();
		}
		
		rate = (Consts.hasPwups) ? 128 : 85;
		
		field = new Field(Consts.fieldSide);
		scene.add(field, 0, 0);
		head = new Head(1, 1);
		scene.add(head, 2, 2);
		
		body.add(head);
		body.add(new BPart(head.x, head.y));
		
		food = new Food();
		scene.add(food, 3, 3);
		
		setFocusable(true);
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent k) {
				if ((k.getKeyCode() == KeyEvent.VK_W || k.getKeyCode() == KeyEvent.VK_UP)
						&& cooldown && !pause) {
					head.dx = 0;
					head.dy = (head.dy != 1) ? -1 : 1;
					cooldown = false;
				}
				if ((k.getKeyCode() == KeyEvent.VK_S || k.getKeyCode() == KeyEvent.VK_DOWN)
						&& cooldown && !pause) {
					head.dx = 0;
					head.dy = (head.dy != -1) ? 1 : -1;
					cooldown = false;
				}
				if ((k.getKeyCode() == KeyEvent.VK_A || k.getKeyCode() == KeyEvent.VK_LEFT)
						&& cooldown && !pause) {
					head.dx = (head.dx != 1) ? -1 : 1;
					head.dy = 0;
					cooldown = false;
				}
				if ((k.getKeyCode() == KeyEvent.VK_D || k.getKeyCode() == KeyEvent.VK_RIGHT)
						&& cooldown && !pause) {
					head.dx = (head.dx != -1) ? 1 : -1;
					head.dy = 0;
					cooldown = false;
				}
				if (k.getKeyCode() == KeyEvent.VK_Q) {
					Game.g.setState(Frame.NORMAL);
					Game.g.toFront();
					Game.runningTask.dispose();
					Game.runningTask = null;
				}
				if (k.getKeyCode() == KeyEvent.VK_R) {
					MainMenu.newSnake();
				}
				if (k.getKeyCode() == KeyEvent.VK_SPACE) {
					pause = ! pause;
					if (pause) {
						field.add(pauseLabel);
						field.repaint(0, 0, Consts.getFieldX(), Consts.getFieldY());
					}
					else {
						field.remove(pauseLabel);
						field.repaint(0, 0, Consts.getFieldX(), Consts.getFieldY());
					}
				}
			}
		});
		
		setVisible(true);
	}
	
	void Update() {
		while (true) {
			frame();
			try {
				if (! pause) {
					cooldown = true;
					head.move();
				}
			}
			catch (Die d) {
				break;
			}
			for (int i = body.size()-1; i >= 1 ; i--) {
				body.get(i).move();
			}
			food.eat();
		}
	}
	
	public void run() {
		Consts.game = this;
		if (Game.runningTask == null) {
			Game.runningTask = this;
			this.Start();
			this.Update();
		}
		else {
			Game.runningTask.dispose();
			Game.runningTask = this;
			this.Start();
			this.Update();
		}
	}
}
