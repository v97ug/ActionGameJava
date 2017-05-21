package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Key implements KeyListener {
	public static boolean up = false;
	public static boolean down = false;
	public static boolean right = false;
	public static boolean left = false;
	public static boolean enter = false;
	static boolean attack = false;
	static boolean jump = false;

	public Key() {
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 38 || e.getKeyCode() == 87) {
			up = true;
		}

		if(e.getKeyCode() == 40 || e.getKeyCode() == 83) {
			down = true;
		}

		if(e.getKeyCode() == 37 || e.getKeyCode() == 65) {
			left = true;
		}

		if(e.getKeyCode() == 39 || e.getKeyCode() == 68) {
			right = true;
		}

		if(e.getKeyCode() == 10 || e.getKeyCode() == 90 || e.getKeyCode() == 32) {
			enter = true;
		}

		if(e.getKeyCode() == 90 || e.getKeyCode() == 32) {
			jump = true;
		}

		if(e.getKeyCode() == 88) {
			attack = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38 || e.getKeyCode() == 87) {
			up = false;
		}

		if(e.getKeyCode() == 40 || e.getKeyCode() == 83) {
			down = false;
		}

		if(e.getKeyCode() == 37 || e.getKeyCode() == 65) {
			left = false;
		}

		if(e.getKeyCode() == 39 || e.getKeyCode() == 68) {
			right = false;
		}

		if(e.getKeyCode() == 10 || e.getKeyCode() == 90 || e.getKeyCode() == 32) {
			enter = false;
		}

		if(e.getKeyCode() == 90 || e.getKeyCode() == 32) {
			jump = false;
		}

		if(e.getKeyCode() == 88) {
			attack = false;
		}

	}

	public void keyTyped(KeyEvent e) {
	}
}
