package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Block extends Thing {
	final Color color;

	public Block(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.pink;
		super.setColor(this.color);

		try {
            InputStream resourceInputStream = getClass().getClassLoader().getResourceAsStream("img/Block.png");
            this.image = ImageIO.read(resourceInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
}
