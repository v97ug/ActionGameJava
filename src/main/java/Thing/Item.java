package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Item extends Thing {
	final Color color;

	public Item(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.blue;
		super.setColor(this.color);

		try {
            InputStream resourceInputStream = getClass().getClassLoader().getResourceAsStream("img/item.png");
			this.image = ImageIO.read(resourceInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
