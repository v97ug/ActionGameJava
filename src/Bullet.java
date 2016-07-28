//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;

public class Bullet extends MoveThing {
	double angle;
	int speed = 4;
	final Color color = new Color(255, 61, 37);

	public Bullet(double x, double y, int length, double angle) {
		super(x, y, length);
		this.angle = angle;
		super.setSpeed(this.speed);
		super.setColor(this.color);
	}

	public double getAngle() {
		return this.angle;
	}
}
