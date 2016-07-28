//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;

public class Sword extends MoveThing {
	boolean appear = false;
	final Color blue;

	public Sword(double x, double y, int length) {
		super(x, y, length);
		this.blue = Color.blue;
		this.setColor(this.blue);
	}

	public boolean isAppear() {
		return this.appear;
	}

	public void setAppear(boolean appear) {
		this.appear = appear;
	}

	public void appearOrNotSword() {
		this.appear = Key.attack;
	}

	public void draw(Graphics g) {
	}
}
