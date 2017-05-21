package Thing;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Thing.MoveThing;

import java.awt.Color;
import java.util.ArrayList;

public class Enemy extends MoveThing {
	int HP;
	final Color color = new Color(5263871);
	boolean isFire;
	boolean isNeedle;
	int fireFrame;
	ArrayList<Bullet> fires = new ArrayList();
	boolean onceFireAttack = false;
	boolean onceNeedleAttack = false;
	ArrayList<MoveNeedle> needles = new ArrayList();

	public Enemy(double x, double y, int length, int HP) {
		super(x, y, length);
		this.HP = HP;
		super.setColor(this.color);
		this.isFire = false;
		this.isNeedle = false;
		this.onceFireAttack = false;
		this.onceNeedleAttack = false;
		this.fireFrame = 0;
	}

	public int getHP() {
		return this.HP;
	}

	public void damage(int damage) {
		this.HP -= damage;
	}

	public ArrayList<Bullet> getFires() {
		return this.fires;
	}

	public ArrayList<MoveNeedle> getNeedles() {
		return this.needles;
	}

	public void fireMove_delete() {
		int i = 0;

		while(true) {
			while(i < this.fires.size()) {
				Bullet aFire = (Bullet)this.fires.get(i);
				aFire.move(Math.cos(aFire.getAngle()), Math.sin(aFire.getAngle()));
				if(aFire.getX() - (double)aFire.getL() > 0.0D && aFire.getX() < (double) Num.MAX_WIDTH && aFire.getY() < (double) Num.MAX_HEIGHT && aFire.getY() - (double)aFire.getL() >= 0.0D) {
					++i;
				} else {
					this.fires.remove(aFire);
				}
			}

			return;
		}
	}

	public void addFire() {
		++this.fireFrame;

		for(int i = 120; i < 150; i += 3) {
			Bullet bullet = new Bullet(this.x, this.y, 20, Math.toRadians((double)i));
			bullet.setSpeed(6);
			this.fires.add(bullet);
		}

	}

	public void fireRadiation() {
		if(!this.onceFireAttack && this.HP % 10 == 0) {
			this.isFire = true;
			this.onceFireAttack = true;
		}

		if(this.HP % 10 != 0) {
			this.onceFireAttack = false;
		}

		if(this.isFire) {
			this.addFire();
		}

		if(this.fireFrame > 150) {
			this.fireFrame = 0;
			this.isFire = false;
		}

		this.fireMove_delete();
	}

	public void fallNeedle() {
		if(!this.onceNeedleAttack && this.HP % 10 == 0) {
			this.isNeedle = true;
			this.onceNeedleAttack = true;
		}

		if(this.HP % 10 != 0) {
			this.onceNeedleAttack = false;
		}

		if(this.isNeedle) {
			for(int i = 0; i < 20; ++i) {
				MoveNeedle aNeedle = new MoveNeedle((double)(i * 60), 0.0D, 20);
				aNeedle.setSpeed(2);
				this.needles.add(aNeedle);
			}

			this.isNeedle = false;
		}

		this.needlesMove_delete();
	}

	private void needlesMove_delete() {
		int i = 0;

		while(i < this.needles.size()) {
			MoveNeedle aNeedle = (MoveNeedle)this.needles.get(i);
			aNeedle.gravity();
			if(aNeedle.getY() >= (double) Num.MAX_HEIGHT) {
				this.needles.remove(aNeedle);
			} else {
				++i;
			}
		}

	}
}
