package game;

import java.awt.*;
import javax.swing.*;

import stuff.Consts;

import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

@SuppressWarnings("all")
public class Head extends BPart {
	
	int dx, dy;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Consts.getHeadColor());
		g.fillRect(0, 0, Consts.square(), Consts.square());
	}
	
	public Head(int x, int y) {
		super(x, y);
		this.dx = 1;
		this.dy = 0;
		this.setBounds(Consts.inFieldX(x), Consts.inFieldY(y), Consts.square(), Consts.square());
		this.setOpaque(true);
	}
	
	public void move() throws Die {
		if (x + dx == Consts.fieldSide || y + dy == Consts.fieldSide || x + dx < 0 || y + dy < 0) {
			int dx1 = dx; int dy1 = dy;
			if (Consts.hasTpWalls) {
				if (x + dx == Consts.fieldSide)
					dx = (Consts.fieldSide - 1) * -1;
				else if (x + dx < 0) {
					dx = (Consts.fieldSide - 1);
				}
				
				if (y + dy == Consts.fieldSide)
					dy = (Consts.fieldSide - 1) * -1;
				else if (y + dy < 0) {
					dy = (Consts.fieldSide - 1);
				}
				x += dx; dx = dx1;
				y += dy; dy = dy1;
			}
			else {
				throw new Die();
			}
		}
		else {
			if (! Consts.isTransparent)
				if (BPart.collides(x + dx, y + dy))
					throw new Die();
			x += dx;
			y += dy;
		}
		this.setBounds(Consts.inFieldX(x), Consts.inFieldY(y), Consts.square(), Consts.square());
	}
}
