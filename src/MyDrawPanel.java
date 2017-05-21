
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Thing.MoveNeedle;
import Thing.Player;
import Thing.Enemy;
import Thing.Key;
import Thing.Goal;
import Thing.Thing;
import Thing.Field;
import Thing.Item;
import Thing.Num;
import Thing.Block;
import Thing.Needle;

/**
 * Created by takeyuki on 17/05/21.
 */
public class MyDrawPanel extends JPanel {
    private MoveNeedle moveNeedle1;
    private int scene = 0;
    private int select = 0;
    final int TITLE = 0;
    final int PLAY = 1;
    private static final int GAMEOVER = 2;
    private static final int CLEAR = 3;
    private boolean once;
    private Graphics g;
    private Player player = new Player(10.0D, 100.0D, 25);
    private Enemy boss;
    private Image skyImage;

    public void game() {
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
                play();
//                play2();
            }
            if (scene == 2) {
                gameover();
            }
            if (scene == 3) {
                gameclear();
            }
            wg.drawImage(img, 0, 0, null);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void title() {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);

        g.setColor(Color.lightGray);
        g.setFont(new Font("Arial", Font.BOLD, 12));

        g.drawString("→", 280, 425 + 75 * select);
        g.drawString("START", 325, 425);
        g.drawString("EXIT", 325, 500);

        if (once) {
            if (!Key.enter) {
                once = false;
            }
        } else {
            if (Key.up) {
                select = 0;
            }
            if (Key.down) {
                select = 1;
            }
            if ((select == 0) && (Key.enter)) {
                scene = 1;
                once = true;
                initStage();
                makeStage1();
//                makeBossStage1();
            }
            if ((select == 1) && (Key.enter)) {
                System.exit(0);
            }
        }
    }

    private void gameover() {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.lightGray);

        g.drawString("GAMEOVER", 325, 425);
        if (once) {
            if (!Key.enter) {
                once = false;
            }
        } else if (Key.enter) {
            scene = 0;
            once = true;
        }
    }

    private void gameclear() {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.lightGray);

        g.drawString("GAMECLAER", 325, 425);
        if (once) {
            if (!Key.enter) {
                once = false;
            }
        } else if (Key.enter) {
            scene = 0;
            once = true;
        }
    }

    ArrayList<MoveNeedle> someNeedles = new ArrayList();
    private Goal goal = new Goal(0.0D, 0.0D, 30);
    private PointGet pointGet = new PointGet();
    private Field field1 = new Field();
    private Field field2 = new Field();

    public void play() {
        g.setColor(Color.white);

        g.drawImage(skyImage, 0, 0, this);

        field1.setPlayer(player);
        field1.draw(g);

        field1.drawItemsLayer(g);
        field1.drawNeedlesLayer(g);
        if ((player.getX() + 10.0D > moveNeedle1.getX()) && (!moveNeedle1.getAppear())) {
            moveNeedle1.changeAppear();
        }
        if (moveNeedle1.getAppear()) {
            moveNeedle1.setPlayer(player);
            moveNeedle1.gravity();

            moveNeedle1.imageDraw(g);
        }
        if ((player.getX() > ((MoveNeedle) someNeedles.get(0)).getX()) && (!((MoveNeedle) someNeedles.get(0)).getAppear())) {
            for (MoveNeedle aNeedle : someNeedles) {
                aNeedle.changeAppear();
            }
        }
        if (((MoveNeedle) someNeedles.get(0)).getAppear()) {
            for (MoveNeedle aNeedle : someNeedles) {
                aNeedle.setPlayer(player);
                aNeedle.gravity();

                aNeedle.imageDraw(g);
            }
        }
        ArrayList<Item> items = field1.getItemsLayer();
        for (int i = 0; i < items.size(); ) {
            Item aItem = (Item) items.get(i);
            if (player.contact(aItem)) {
                items.remove(aItem);
                pointGet.addCoin(1);
            } else {
                i++;
            }
        }
        player.setField(field1);
        player.playerMoving();
        player.draw(g);

        goal.setPlayer(player);
        goal.drawScroll(g);

        g.setColor(Color.blue);
        g.drawString(Integer.toString(pointGet.getCoin()), 0, 30);
        if (player.contact(moveNeedle1)) {
            scene = 2;
        }
        for (MoveNeedle aNeedle : someNeedles) {
            if (player.contact(aNeedle)) {
                scene = 2;
            }
        }
        if (player.toDie()) {
            scene = 2;
        }
        if (player.contact(goal)) {
            scene = 3;
        }
    }

    public void play2() {
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 600);

        this.field2.setPlayer(player);
        this.field2.draw(g);

        player.setField(field2);
        player.playerMoving();
        player.swordMove();
        if ((player.sword.contact(boss)) && (player.sword.isAppear())) {
            boss.damage(1);
        }
        player.draw(g);
        player.swordDraw(g);

        g.setColor(Color.blue);
        g.drawString(Integer.toString(pointGet.getCoin()), 0, 30);

        boss.setPlayer(player);
        boss.drawScroll(g);

        boss.fireRadiation();
        boss.fallNeedle();

        field2.setFireLayer(boss.getFires());
        field2.setMoveNeedlesLayer(boss.getNeedles());

        field2.drawFireLayer(g);
        field2.drawMoveNeedlesLayer(g);
        if ((player.toDie()) || (player.contact(boss))) {
            scene = 2;
        }
    }

    public void makeStage1() {
        try {
            File file2 = new File("stage/stage1_150x20.pgm");
            BufferedReader br = new BufferedReader(new FileReader(file2));

            br.readLine();
            br.readLine();

            String[] strWidthHeight = br.readLine().split(" ");
            int WIDTH_FIELD = Integer.parseInt(strWidthHeight[0]);
            int HEIGHT_FIELD = Integer.parseInt(strWidthHeight[1]);

            Num.setMaxWidth(WIDTH_FIELD);
            Num.setMaxHeight(HEIGHT_FIELD);

            br.readLine();
            for (int i = 0; i < HEIGHT_FIELD; i++) {
                for (int j = 0; j < WIDTH_FIELD; j++) {
                    String str = br.readLine();
                    switch (str) {
                        case "0":
                            Block block = new Block(j * 30, i * 30, 30);
                            field1.addField(i, j, block);
                            break;
                        case "18":
                            Item item = new Item(j * 30, i * 30, 18);
                            field1.addItem(item);
                            break;
                        case "54":
                            Needle needle = new Needle(j * 30, i * 30, 30);
                            field1.addNeedle(needle);
                            break;
                        case "182":
                            makeThing(goal, i, j, 30);
                            break;
                    }
                }
            }
            br.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        moveNeedle1 = new MoveNeedle(3030, -10, 20);
        someNeedles.clear();
        for (int i = 48; i <= 51; i++) {
            someNeedles.add(new MoveNeedle(i * 30, -20, 20));
        }
    }

    public void makeBossStage1() {
        Num.MAX_WIDTH = 800;
        Num.MAX_HEIGHT = 600;
        for (int i = 17; i < 20; i++) {
            for (int j = 0; j < 27; j++) {
                Block block = new Block(j * 30, i * 30, 30);
                this.field2.addField(i, j, block);
            }
        }
        boss = new Enemy(100.0D, 100.0D, 40, 20);
        makeThing(boss, 10, 14, 100);

        makeThing(player, 10, 0, player.getL());
    }

    public void makeThing(Thing thing, int i, int j, int len) {
        thing.setX(j * 30);
        thing.setY(i * 30);
        thing.setL(len);
    }

    public void initStage() {
        player.setX(100.0D);
        player.setY(100.0D);
        this.pointGet.initCoin();
    }
}
