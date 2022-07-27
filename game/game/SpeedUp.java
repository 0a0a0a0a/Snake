package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import stuff.*;
import game.*;
import mainMenu.Game;
import sidePanels.*;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class SpeedUp extends PowerUp {
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Consts.speedUpColor);
		g.fillRect(0, 0, Consts.square(), Consts.square());
	}
	
	public SpeedUp() {
		super(Consts.gRand.nextInt(Consts.fieldSide), Consts.gRand.nextInt(Consts.fieldSide));
		while (BPart.collides(x, y)) {
			x = Consts.gRand.nextInt(Consts.fieldSide);
			y = Consts.gRand.nextInt(Consts.fieldSide);
		}
		setBounds(Consts.inFieldX(x), Consts.inFieldY(y), Consts.square(), Consts.square());
		setOpaque(true);
	}

	public void eat() {
		if (Consts.game.head != null && x == Consts.game.head.x && y == Consts.game.head.y) {
			Consts.game.scene.remove(this);
			Consts.game.field.nextFood();
			super.trigger = new Thread(new Effect() {
				public void run() {
					Consts.game.rate /= 2;
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						
					}
					Consts.game.rate *= 2;
				}
			});
			trigger.start();
			Consts.game.score++;
			Consts.game.scoreLabel.setText("Score: " + Consts.game.score);
		}
	}
}
