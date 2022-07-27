package stuff;

import java.awt.*;
import javax.swing.*;

import game.Snake;
import mainMenu.Game;

import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("all")
public final class Consts {

	public static final Font DefaultFont(int property, int size) {
		return new Font("Calibri", property, size);
	}
	
	public static Snake game = (Snake) Game.g.runningTask;
	
	public static final Color fieldBorder = new Color(0x343a40);
	public static final Color fieldInside1 = new Color(250, 250, 250);
	public static final Color fieldInside2 = new Color(240, 240, 240);
	
	private static final List<Color> BodySchemes = new ArrayList<Color>(
		Arrays.asList(
			new Color(168, 215, 121),
			new Color(200, 200, 200),
			new Color(0x005f73),
			new Color(0x457b9d),
			new Color(0x538d22),
			new Color(0xffb600),
			new Color(0xac46a1),
			new Color(0x003459),
			new Color(0xf9bec7)
			)
		);
	
	private static final List<Color> HeadSchemes = new ArrayList<Color>(
		Arrays.asList(
			new Color(71, 161, 49),
			new Color(100, 100, 100),
			new Color(0xee9b00),
			new Color(0xbf0603),
			new Color(0x245501),
			new Color(0xff6000),
			new Color(0xea698b),
			new Color(0x007ea7),
			new Color(0xff85a1)
			)
		);
	
	private static final List<Color> FoodSchemes = new ArrayList<Color>(
		Arrays.asList(
			new Color(233, 71, 22),
			new Color(50, 50, 50),
			new Color(0xae2012),
			new Color(0xf4d58d),
			new Color(0x143601),
			new Color(0xba181b),
			new Color(0x6d23b6),
			new Color(0x52b69a),
			new Color(0xcfbaf0)
			)
		);
	
	public static final Color slowDownColor = new Color(0x8ecae6);
	public static final Color speedUpColor = new Color(0xffc300);
	
	private static final Color switchFalse = new Color(0xf94144).darker().darker();
	private static final Color switchTrue = new Color(0x43aa8b).darker().darker();
	
	public static final Color getBodyColor() {
		return BodySchemes.get(scheme);
	}
	
	public static final Color getHeadColor() {
		return HeadSchemes.get(scheme);
	}
	
	public static final Color getFoodColor() {
		return FoodSchemes.get(scheme);
	}
	
	public static int scheme = 0;
	
	public static final int schemesCount = BodySchemes.size();
	
	public static int fieldSide = 22;
	public static final int square() {
		return 580/fieldSide;
	}
	
	public static boolean hasTpWalls = false;
	public static boolean hasObstacles = false;
	public static boolean hasPwups = true;
	public static boolean isTransparent = false;
	
	public static final Color getSwitchStatusBG(boolean sw) {
		return (sw) ? Consts.switchTrue : Consts.switchFalse;
	}
	
	public static final Color DefaultHover = new Color(50, 50, 50);
	
	public static final Color DefaultButtonBG = new Color(5, 5, 5);
	
	public static final Dimension getFieldDim() {
		int x = 900-(900-(20+865-(580 % fieldSide)));
		int y = 700-(700-(20+688-(580 % fieldSide)));
		return new Dimension(x, y);
	}
	
	public static final int getFieldX() {
		return 900;
	}
	public static final int getFieldY() {
		return 700;
	}
	
	public static final int inFieldX(int x) {
		return 250 + x * Consts.square();
	}
	public static final int inFieldY(int y) {
		return 50 + y * Consts.square();
	}
	
	public static final MouseAdapter setDefaultHover(JButton b) {
		return new MouseAdapter() {
			// hover interaction
			public void mouseEntered(MouseEvent e) {
		        b.setBackground(Consts.DefaultHover);
		    }
			// hover quit
		    public void mouseExited(MouseEvent e) {
		    	b.setBackground(Consts.DefaultButtonBG);
		    }
		};
	}
	
	public static final Random gRand = new Random();
}
