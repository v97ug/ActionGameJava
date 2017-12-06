package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Thing.MoveThing;

import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class MoveNeedle extends MoveThing {
	Color color;
	final int speed;
	boolean appear;

	public MoveNeedle(double x, double y, int length) {
		super(x, y, length);
		color = Color.red;
		speed = 3;
		appear = false;
		super.setSpeed(3);
		super.setColor(this.color);

		try {
			image = ImageIO.read(new File("img/fallNeedle.png"));
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}

	public void changeAppear() {
		appear = !appear;
	}

	public boolean getAppear() {
		return appear;
	}
}
