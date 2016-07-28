//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class MoveNeedle extends MoveThing {
	Color color;
	final int speed;
	boolean appear;

	public MoveNeedle(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.red;
		this.speed = 3;
		this.appear = false;
		super.setSpeed(3);
		super.setColor(this.color);

		try {
			this.image = ImageIO.read(new File("img/fallNeedle.png"));
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}

	public void changeAppear() {
		this.appear = !this.appear;
	}

	public boolean getAppear() {
		return this.appear;
	}
}
