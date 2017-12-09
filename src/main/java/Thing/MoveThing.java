package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Thing.Thing;

public class MoveThing extends Thing {
	private int speed;

	public MoveThing(double x, double y, int length) {
		super(x, y, length);
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void gravity() {
		this.move(0.0D, 3.0D);
	}

	public void move(double addX, double addY) {
		super.x += addX * (double)this.speed;
		super.y += addY * (double)this.speed;
	}
}
