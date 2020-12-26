package game.HUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotHud extends JPanel {
    private static String info = "";
    private static Boolean isSelling = false;
    JLabel towerInfo = new JLabel(info);
    JButton sellTower = new JButton("sell");

    public BotHud() {
        this.add(towerInfo);
        this.add(sellTower);

        sellTower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSell(true);
            }
        });
    }

    public void update() {
        getSell();
        towerInfo.setText(info);
    }

    public static void setInfo(String info) {
        BotHud.info = info;
    }

    public static void setSell(Boolean sell) {
        BotHud.isSelling = sell;
    }

    public static boolean getSell() {
        return isSelling;
    }



}
