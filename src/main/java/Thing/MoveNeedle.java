package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.io.InputStream;
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
            InputStream resourceInputStream = getClass().getClassLoader().getResourceAsStream("img/fallNeedle.png");
            image = ImageIO.read(resourceInputStream);
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
