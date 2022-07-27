package game;

import java.awt.*;
import javax.swing.*;

import mainMenu.*;
import stuff.Consts;

import java.awt.event.*;

@SuppressWarnings("all")
public class BPart extends JPanel {
	
	int x;
	int y;
	
	public void paintComponent(Graphics g) {
			g.setColor(Consts.getBodyColor());
			g.fillRect(0, 0, Consts.square(), Consts.square());
	}
	
	public BPart(int x, int y) {
		Consts.game = (Snake) Game.g.runningTask;
		this.x = x;
		this.y = y;
		this.setBounds(Consts.inFieldX(x), Consts.inFieldY(y), Consts.square(), Consts.square());
		this.setOpaque(true);
	}
	
	public static boolean collides(int x, int y) {
		for (BPart b : Consts.game.body) {
			if (b.x == x && b.y == y) {
				return true;
			}
		}
		return false;
	}
	
	public static void grow() {
		BPart n;
		if (Consts.game != null) {
			if (!(Consts.game.body.size() == 2)) {
				n = new BPart(Consts.game.body.get(Consts.game.body.size()-1).x - (Consts.game.body.get(Consts.game.body.size()-2).x - Consts.game.body.get(Consts.game.body.size()-1).x), 
						Consts.game.body.get(Consts.game.body.size()-1).y - (Consts.game.body.get(Consts.game.body.size()-2).y - Consts.game.body.get(Consts.game.body.size()-1).y));
			}
			else {
				n = new BPart(Consts.game.body.get(Consts.game.body.size()-1).x - Consts.game.head.dx, Consts.game.body.get(Consts.game.body.size()-1).y - Consts.game.head.dy);
			}
			Consts.game.body.add(n);
			Consts.game.scene.add(n, 1, 1);
		}
	}
	
	public void move() {
		if (Consts.game.body.indexOf(this) > 0) {
			this.x = Consts.game.body.get(Consts.game.body.indexOf(this)-1).x;
			this.y = Consts.game.body.get(Consts.game.body.indexOf(this)-1).y;
		}
		this.setBounds(Consts.inFieldX(x), Consts.inFieldY(y), Consts.square(), Consts.square());
		setOpaque(true);
	}
}
