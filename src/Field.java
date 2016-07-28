//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Field {
	HashMap<Integer, HashMap<Integer, Thing>> fields = new HashMap();
	ArrayList<Bullet> fireLayer = new ArrayList();
	ArrayList<Needle> needlesLayer = new ArrayList();
	ArrayList<MoveNeedle> moveNeedlesLayer = new ArrayList();
	ArrayList<Item> itemsLayer = new ArrayList();
	Player player;

	public Field() {
	}

	public void addField(int i, int j, Thing thing) {
		if(!this.fields.containsKey(Integer.valueOf(i))) {
			this.fields.put(Integer.valueOf(i), new HashMap());
		}

		((HashMap)this.fields.get(Integer.valueOf(i))).put(Integer.valueOf(j), thing);
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public HashMap<Integer, HashMap<Integer, Thing>> getFields() {
		return this.fields;
	}

	public ArrayList<Bullet> getFireLayer() {
		return this.fireLayer;
	}

	public ArrayList<MoveNeedle> getMoveNeedlesLayer() {
		return this.moveNeedlesLayer;
	}

	public ArrayList<Item> getItemsLayer() {
		return this.itemsLayer;
	}

	public ArrayList<Needle> getNeedlesLayer() {
		return this.needlesLayer;
	}

	public void setFireLayer(ArrayList<Bullet> bullets) {
		this.fireLayer = bullets;
	}

	public void setMoveNeedlesLayer(ArrayList<MoveNeedle> needles) {
		this.moveNeedlesLayer = needles;
	}

	public void addItem(Item item) {
		this.itemsLayer.add(item);
	}

	public void addNeedle(Needle needle) {
		this.needlesLayer.add(needle);
	}

	public void draw(Graphics g) {
		Iterator i$ = this.fields.keySet().iterator();

		while(i$.hasNext()) {
			int i = ((Integer)i$.next()).intValue();
			Iterator i$1 = ((HashMap)this.fields.get(Integer.valueOf(i))).keySet().iterator();

			while(i$1.hasNext()) {
				int j = ((Integer)i$1.next()).intValue();
				this.imageDraw(g, (Thing)((HashMap)this.fields.get(Integer.valueOf(i))).get(Integer.valueOf(j)));
			}
		}

	}

	public void imageDraw(Graphics g, Thing thing) {
		if(thing.image == null) {
			if(this.player.x < 400.0D) {
				g.setColor(thing.color);
				g.fillRect((int)thing.x, (int)thing.y, thing.length, thing.length);
			} else if(this.player.x > (double)(Num.MAX_WIDTH - 400)) {
				g.setColor(thing.color);
				g.fillRect((int)thing.x - (Num.MAX_WIDTH - 800), (int)thing.y, thing.length, thing.length);
			} else {
				g.setColor(thing.color);
				g.fillRect((int)thing.x - (int)this.player.x + 400, (int)thing.y, thing.length, thing.length);
			}
		} else if(this.player.x < 400.0D) {
			g.drawImage(thing.image, (int)thing.x, (int)thing.y, thing.imageObs);
		} else if(this.player.x > (double)(Num.MAX_WIDTH - 400)) {
			g.drawImage(thing.image, (int)thing.x - (Num.MAX_WIDTH - 800), (int)thing.y, thing.imageObs);
		} else {
			g.drawImage(thing.image, (int)thing.x - (int)this.player.x + 400, (int)thing.y, thing.imageObs);
		}

	}

	public void drawFireLayer(Graphics g) {
		Iterator i$ = this.fireLayer.iterator();

		while(i$.hasNext()) {
			Bullet aFire = (Bullet)i$.next();
			this.imageDraw(g, aFire);
		}

	}

	public void drawMoveNeedlesLayer(Graphics g) {
		Iterator i$ = this.moveNeedlesLayer.iterator();

		while(i$.hasNext()) {
			MoveNeedle aNeedle = (MoveNeedle)i$.next();
			this.imageDraw(g, aNeedle);
		}

	}

	public void drawItemsLayer(Graphics g) {
		Iterator i$ = this.itemsLayer.iterator();

		while(i$.hasNext()) {
			Item aItem = (Item)i$.next();
			this.imageDraw(g, aItem);
		}

	}

	public void drawNeedlesLayer(Graphics g) {
		Iterator i$ = this.needlesLayer.iterator();

		while(i$.hasNext()) {
			Needle aNeedle = (Needle)i$.next();
			this.imageDraw(g, aNeedle);
		}

	}
}
