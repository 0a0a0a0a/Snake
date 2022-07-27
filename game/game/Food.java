package game;

import java.awt.*;
import javax.swing.*;

import mainMenu.Game;
import stuff.Consts;

import java.awt.*;

@SuppressWarnings("all")
public class Food extends PowerUp {
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Consts.getFoodColor());
		g.fillRect(0, 0, Consts.square(), Consts.square());
	}
	
	public Food() {
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
			BPart.grow();
			Consts.game.score++;
			Consts.game.scoreLabel.setText("Score: " + Consts.game.score);
		}
	}
}
