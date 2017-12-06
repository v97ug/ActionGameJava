package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Thing.Thing;

import java.awt.Color;

public class Goal extends Thing {
	final Color color;

	public Goal(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.green;
		super.setColor(this.color);
	}
}
