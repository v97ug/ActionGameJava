//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.HashMap;

public class Thing {
	protected double x;
	protected double y;
	int length;
	Color color;
	Player player;
	Field field;
	Image image;
	ImageObserver imageObs;

	public Thing(double x, double y, int length) {
		this.x = x;
		this.y = y;
		this.length = length;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setL(int length) {
		this.length = length;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}

	public int getL() {
		return this.length;
	}

	public boolean contact(Thing thing) {
		if(this.length != 0 && thing.getL() != 0) {
			for(int i = (int)this.x; (double)i <= this.x + (double)this.length; ++i) {
				for(int j = (int)this.y; (double)j < this.y + (double)this.length; ++j) {
					if(thing.x <= (double)i && thing.x + (double)thing.length >= (double)i && thing.y <= (double)j && thing.y + (double)thing.length >= (double)j) {
						return true;
					}
				}
			}

			return false;
		} else {
			return false;
		}
	}

	public boolean contactField(Field field) {
		int begin_i = (int)this.y / 30;
		int end_i = (int)(this.y + (double)this.length) / 30;
		int begin_j = (int)this.x / 30;
		int end_j = (int)(this.x + (double)this.length) / 30;

		for(int i = begin_i; i <= end_i; ++i) {
			for(int j = begin_j; j <= end_j; ++j) {
				if(field.getFields().get(Integer.valueOf(i)) != null && ((HashMap)field.getFields().get(Integer.valueOf(i))).get(Integer.valueOf(j)) != null) {
					Thing thing = (Thing)((HashMap)field.getFields().get(Integer.valueOf(i))).get(Integer.valueOf(j));
					if(this.contact(thing)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect((int)this.x, (int)this.y, this.length, this.length);
	}

	public void drawScroll(Graphics g) {
		if(this.player.x < 400.0D) {
			g.setColor(this.color);
			g.fillRect((int)this.x, (int)this.y, this.length, this.length);
		} else if(this.player.x > (double)(Num.MAX_WIDTH - 400)) {
			g.setColor(this.color);
			g.fillRect((int)this.x - (Num.MAX_WIDTH - 800), (int)this.y, this.length, this.length);
		} else {
			g.setColor(this.color);
			g.fillRect((int)this.x - (int)this.player.x + 400, (int)this.y, this.length, this.length);
		}

	}

	public void imageDraw(Graphics g) {
		if(this.length != 0 && this.image != null) {
			if(this.player.x < 400.0D) {
				g.drawImage(this.image, (int)this.x, (int)this.y, this.imageObs);
			} else if(this.player.x > (double)(Num.MAX_WIDTH - 400)) {
				g.drawImage(this.image, (int)this.x - (Num.MAX_WIDTH - 800), (int)this.y, this.imageObs);
			} else {
				g.drawImage(this.image, (int)this.x - (int)this.player.x + 400, (int)this.y, this.imageObs);
			}

		}
	}
}
