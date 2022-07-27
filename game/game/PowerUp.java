package game;

import java.awt.*;
import javax.swing.*;

import stuff.Consts;

import java.awt.event.*;

@SuppressWarnings("all")
public abstract class PowerUp extends JPanel {

	int x, y;
	
	public static Thread trigger = new Thread();
	public abstract void eat();
	
	public PowerUp(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static PowerUp rngSpawn() {
		if (Consts.hasPwups) {
			switch (Consts.gRand.nextInt(10)) {
				case 1: {
					return new SpeedUp();
				}
				case 8: {
					return new SlowDown();
				}
			}
		}
		return new Food();
	}
}
