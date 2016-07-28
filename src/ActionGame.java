//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

//import ActionGame.MyDrawPanel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ActionGame {
	JFrame jframe;
	MyDrawPanel drawPanel;
	int scene = 0;
	int select = 0;
	final int TITLE = 0;
	final int PLAY = 1;
	private static final int GAMEOVER = 2;
	private static final int CLEAR = 3;
	boolean once;
	Graphics g;
	Player player = new Player(10.0D, 100.0D, 25);
	Enemy boss;
	Image skyImage;

	public ActionGame() {
	}

	// TODO implement
	class MyDrawPanel extends JPanel {
		MoveNeedle moveNeedle1;

		MyDrawPanel(ActionGame paramActionGame) {}

		public void game()
		{
			try {
				skyImage = ImageIO.read(new File("img/sky.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Image img = createImage(800, 600);
			g = img.getGraphics();
			Graphics wg = getGraphics();
			while (true) {
				if (scene == 0) {
					title();
				}
				if (scene == 1) {
					play2();
				}
				if (scene == 2) {
					gameover();
				}
				if (scene == 3) {
					gameclear();
				}
				wg.drawImage(img, 0, 0, null);
				try {
					Thread.sleep(10L);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void title()
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 800, 600);

			g.setColor(Color.lightGray);
//			g.setFont(new Font("������ ������������", 1, 12)); // TODO
			if (select == 0)
			{
				g.drawString("���", 280, 425);
				g.drawString("START", 325, 425);
				g.drawString("EXIT", 325, 500);
			}
			if (select == 1)
			{
				g.drawString("START", 325, 425);
				g.drawString("���", 280, 500);
				g.drawString("EXIT", 325, 500);
			}
			if (once)
			{
				if (!Key.enter) {
					once = false;
				}
			}
			else
			{
				if (Key.up) {
					select = 0;
				}
				if (Key.down) {
					select = 1;
				}
				if ((select == 0) &&
						(Key.enter))
				{
					scene = 1;
					once = true;
					initStage();
//					makeStage1();
					makeBossStage1();
				}
				if ((select == 1) &&
						(Key.enter)) {
					System.exit(0);
				}
			}
		}

		private void gameover()
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.lightGray);
			g.setFont(new Font("������ ������������", 1, 12));

			g.drawString("GAMEOVER", 325, 425);
			if (once)
			{
				if (!Key.enter) {
					once = false;
				}
			}
			else if (Key.enter)
			{
				scene = 0;
				once = true;
			}
		}

		private void gameclear()
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.lightGray);
			g.setFont(new Font("������ ������������", 1, 12));

			g.drawString("GAMECLAER", 325, 425);
			if (once)
			{
				if (!Key.enter) {
					once = false;
				}
			}
			else if (Key.enter)
			{
				scene = 0;
				once = true;
			}
		}

		ArrayList<MoveNeedle> someNeedles = new ArrayList();
		Goal goal = new Goal(0.0D, 0.0D, 30);
		PointGet pointGet = new PointGet();
		Field field1 = new Field();
		Field field2 = new Field();

		public void play()
		{
			g.setColor(Color.white);

			g.drawImage(skyImage, 0, 0, this);

			this.field1.setPlayer(player);
			this.field1.draw(g);

			this.field1.drawItemsLayer(g);
			this.field1.drawNeedlesLayer(g);
			if ((player.getX() + 10.0D > this.moveNeedle1.getX()) && (!this.moveNeedle1.getAppear())) {
				this.moveNeedle1.changeAppear();
			}
			if (this.moveNeedle1.getAppear())
			{
				this.moveNeedle1.setPlayer(player);
				this.moveNeedle1.gravity();

				this.moveNeedle1.imageDraw(g);
			}
			if ((player.getX() > ((MoveNeedle)this.someNeedles.get(0)).getX()) && (!((MoveNeedle)this.someNeedles.get(0)).getAppear())) {
				for (MoveNeedle aNeedle : this.someNeedles) {
					aNeedle.changeAppear();
				}
			}
			if (((MoveNeedle)this.someNeedles.get(0)).getAppear()) {
				for (MoveNeedle aNeedle : this.someNeedles)
				{
					aNeedle.setPlayer(player);
					aNeedle.gravity();

					aNeedle.imageDraw(g);
				}
			}
			ArrayList<Item> items = this.field1.getItemsLayer();
			for (int i = 0; i < items.size();)
			{
				Item aItem = (Item)items.get(i);
				if (player.contact(aItem))
				{
					items.remove(aItem);
					this.pointGet.addCoin(1);
				}
				else
				{
					i++;
				}
			}
			player.setField(this.field1);
			player.playerMoving();
			player.draw(g);

			this.goal.setPlayer(player);
			this.goal.drawScroll(g);

			g.setColor(Color.blue);
			g.drawString(Integer.toString(this.pointGet.getCoin()), 0, 30);
			if (player.contact(this.moveNeedle1)) {
				scene = 2;
			}
			for (MoveNeedle aNeedle : this.someNeedles) {
				if (player.contact(aNeedle)) {
					scene = 2;
				}
			}
			if (player.toDie()) {
				scene = 2;
			}
			if (player.contact(this.goal)) {
				scene = 3;
			}
		}

		public void play2()
		{
			g.setColor(Color.white);
			g.fillRect(0, 0, 800, 600);

			this.field2.setPlayer(player);
			this.field2.draw(g);

			player.setField(this.field2);
			player.playerMoving();
			player.swordMove();
			if ((player.sword.contact(boss)) && (player.sword.isAppear())) {
				boss.damage(1);
			}
			player.draw(g);
			player.swordDraw(g);

			g.setColor(Color.blue);
			g.drawString(Integer.toString(this.pointGet.getCoin()), 0, 30);

			boss.setPlayer(player);
			boss.drawScroll(g);

			boss.fireRadiation();
			boss.fallNeedle();

			this.field2.setFireLayer(boss.getFires());
			this.field2.setMoveNeedlesLayer(boss.getNeedles());

			this.field2.drawFireLayer(g);
			this.field2.drawMoveNeedlesLayer(g);
			if ((player.toDie()) || (player.contact(boss))) {
				scene = 2;
			}
		}

		public void makeStage1()
		{
			try
			{
				File file2 = new File("stage/stage1_150x20.pgm");
				BufferedReader br = new BufferedReader(new FileReader(file2));

				br.readLine();
				br.readLine();

				String[] strWidthHeight = br.readLine().split(" ");
				int WIDTH_FIELD = Integer.parseInt(strWidthHeight[0]);
				int HEIGHT_FIELD = Integer.parseInt(strWidthHeight[1]);

				Num.calMaxWidth(WIDTH_FIELD);
				Num.calMaxHeight(HEIGHT_FIELD);

				br.readLine();
				for (int i = 0; i < HEIGHT_FIELD; i++) {
					for (int j = 0; j < WIDTH_FIELD; j++)
					{
						String str = br.readLine();
						switch (str)
						{
							case "0":
								Block block = new Block(j * 30, i * 30, 30);
								this.field1.addField(i, j, block);
								break;
							case "18":
								Item item = new Item(j * 30, i * 30, 18);
								this.field1.addItem(item);
								break;
							case "54":
								Needle needle = new Needle(j * 30, i * 30, 30);
								this.field1.addNeedle(needle);
								break;
							case "182":
								makeThing(this.goal, i, j, 30);
						}
					}
				}
				br.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			this.moveNeedle1 = new MoveNeedle(3030.0D, -10.0D, 20);
			this.someNeedles.clear();
			for (int i = 48; i <= 51; i++) {
				this.someNeedles.add(new MoveNeedle(i * 30, -20.0D, 20));
			}
		}

		public void makeBossStage1()
		{
			Num.MAX_WIDTH = 800;
			Num.MAX_HEIGHT = 600;
			for (int i = 17; i < 20; i++) {
				for (int j = 0; j < 27; j++)
				{
					Block block = new Block(j * 30, i * 30, 30);
					this.field2.addField(i, j, block);
				}
			}
			boss = new Enemy(100.0D, 100.0D, 40, 20);
			makeThing(boss, 10, 14, 100);

			makeThing(player, 10, 0, player.getL());
		}

		public void makeThing(Thing thing, int i, int j, int len)
		{
			thing.setX(j * 30);
			thing.setY(i * 30);
			thing.setL(len);
		}

		public void initStage()
		{
			player.setX(100.0D);
			player.setY(100.0D);
			this.pointGet.initCoin();
		}
	}

	public static void main(String[] args) {
		(new ActionGame()).buildGUI();
	}

	public void buildGUI() {
		this.jframe = new JFrame("Action Game");
		this.jframe.setDefaultCloseOperation(3);
		this.drawPanel = new MyDrawPanel(this);
		this.jframe.getContentPane().add("Center", this.drawPanel);
		this.jframe.setSize(800, 600);
		this.jframe.setVisible(true);
		this.jframe.addKeyListener(new Key());
		this.drawPanel.game();
	}
}
