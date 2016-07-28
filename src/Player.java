//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.util.Iterator;

public class Player extends MoveThing {
	final int speed = 1;
	final Color color;
	boolean stand;
	int jumpTimes;
	int jumpHours;
	final int jumpLimit;
	Sword sword;

	public Player(double x, double y, int length) {
		super(x, y, length);
		this.color = Color.green;
		this.stand = false;
		this.jumpTimes = 0;
		this.jumpHours = 0;
		this.jumpLimit = 40;
		super.setColor(this.color);
		super.setSpeed(1);
		this.sword = new Sword(x, y, length);
	}

	public void playerMoving() {
		if(Key.right && !this.nextBlock(2.0D, 0.0D)) {
			this.move(2.0D, 0.0D);
		}

		if(Key.left && !this.nextBlock(-2.0D, 0.0D)) {
			this.move(-2.0D, 0.0D);
		}

		if(this.jumpTimes == 0 && this.stand && Key.jump) {
			++this.jumpHours;
			++this.jumpTimes;
			this.stand = false;
		}

		if(this.jumpHours > 0) {
			if(!this.nextBlock(0.0D, -3.0D)) {
				this.move(0.0D, -3.0D);
			}

			++this.jumpHours;
		} else if(!this.nextBlock(0.0D, 3.0D)) {
			this.gravity();
			this.stand = false;
		} else {
			this.stand = true;
			this.jumpTimes = 0;
		}

		if(this.jumpHours > 40) {
			this.jumpHours = 0;
		}

		if(this.x <= 0.0D) {
			this.x = 0.0D;
		}

		if(this.x >= (double)(Num.MAX_WIDTH - this.length)) {
			this.x = (double)(Num.MAX_WIDTH - this.length);
		}

	}

	public boolean nextBlock(double addX, double addY) {
		Player clone = new Player(this.x, this.y, this.length);
		double var10001 = clone.x;
		clone.getClass();
		clone.x = var10001 + addX * 1.0D;
		var10001 = clone.y;
		clone.getClass();
		clone.y = var10001 + addY * 1.0D;
		return clone.contactField(super.field);
	}

	public void swordMove() {
		this.sword.setX(this.x + (double)this.length);
		this.sword.setY(this.y);
	}

	public void swordDraw(Graphics g) {
		this.sword.appearOrNotSword();
		if(this.sword.isAppear()) {
			if(this.sword.getX() < (double)(400 + this.length)) {
				g.setColor(this.sword.color);
				g.fillRect((int)this.sword.getX(), (int)this.sword.getY(), this.length, this.length);
			} else if(this.sword.getX() > (double)(Num.MAX_WIDTH - 400 + this.length)) {
				g.setColor(this.sword.color);
				g.fillRect((int)this.sword.getX() - (Num.MAX_WIDTH - 800), (int)this.sword.getY(), this.length, this.length);
			} else {
				g.setColor(this.sword.color);
				g.fillRect(400 + this.length, (int)this.sword.getY(), this.length, this.length);
			}
		}

	}

	public boolean toDie() {
		if(this.y >= (double)(Num.MAX_HEIGHT - this.length)) {
			return true;
		} else {
			Iterator i$ = this.field.getFireLayer().iterator();

			Bullet aNeedle;
			do {
				if(!i$.hasNext()) {
					i$ = this.field.getMoveNeedlesLayer().iterator();

					MoveNeedle aNeedle1;
					do {
						if(!i$.hasNext()) {
							i$ = this.field.getNeedlesLayer().iterator();

							Needle aNeedle2;
							do {
								if(!i$.hasNext()) {
									return false;
								}

								aNeedle2 = (Needle)i$.next();
							} while(!this.contact(aNeedle2));

							return true;
						}

						aNeedle1 = (MoveNeedle)i$.next();
					} while(!this.contact(aNeedle1));

					return true;
				}

				aNeedle = (Bullet)i$.next();
			} while(!this.contact(aNeedle));

			return true;
		}
	}

	public void draw(Graphics g) {
		if(super.x < 400.0D) {
			g.setColor(this.color);
			g.fillRect((int)super.x, (int)super.y, this.length, this.length);
		} else if(super.x > (double)(Num.MAX_WIDTH - 400)) {
			g.setColor(this.color);
			g.fillRect((int)super.x - (Num.MAX_WIDTH - 800), (int)super.y, this.length, this.length);
		} else {
			g.setColor(this.color);
			g.fillRect(400, (int)super.y, this.length, this.length);
		}

	}
}
