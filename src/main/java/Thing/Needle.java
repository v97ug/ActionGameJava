package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Needle extends Thing {
	final Color color;

	public Needle(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.red;
		super.setColor(this.color);

		try {
            InputStream resourceInputStream = getClass().getClassLoader().getResourceAsStream("img/needle.png");
            this.image = ImageIO.read(resourceInputStream);
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}
}
