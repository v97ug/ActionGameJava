//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.io.File;
import javax.imageio.ImageIO;

public class Item extends Thing {
	final Color color;

	public Item(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.blue;
		super.setColor(this.color);

		try {
			this.image = ImageIO.read(new File("img/item.png"));
		} catch (Exception var7) {
			var7.printStackTrace();
		}

	}
}
