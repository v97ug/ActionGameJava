package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Thing.Thing;

import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class Needle extends Thing {
	final Color color;

	public Needle(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.red;
		super.setColor(this.color);

		try {
			this.image = ImageIO.read(new File("img/needle.png"));
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}
}
