//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import Thing.Key;

import javax.swing.*;

public class ActionGame {
    public static void main(String[] args) {
        JFrame jframe = new JFrame("Action Game");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawPanel drawPanel = new MyDrawPanel();
        jframe.getContentPane().add("Center", drawPanel);
        jframe.setSize(800, 600) ;
        jframe.setVisible(true);
        jframe.addKeyListener(new Key());
        drawPanel.game();
    }
}
