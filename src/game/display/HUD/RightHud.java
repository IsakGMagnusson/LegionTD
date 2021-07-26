package game.display.HUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightHud extends JPanel{
    private static String tierOnePath = "";


    Icon tierOneImage = new ImageIcon(getClass().getResource(tierOnePath+"icon.png"));

    JButton buttonT1 = new JButton(tierOneImage);
    JButton buttonT2 = new JButton("Tier 2");


    public static int buying = -1;

    public RightHud(){

        buttonT1.setBorderPainted(false);
        buttonT1.setFocusPainted(false);
        buttonT1.setContentAreaFilled(false);

        this.add(buttonT1);
        this.add(buttonT2);


        buttonT1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buying = 0;
            }
        });

        buttonT2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buying = 1;
            }
        });
    }


    public static void setTierOnePath(String tierOnePath) {
        RightHud.tierOnePath = tierOnePath;
    }
}
