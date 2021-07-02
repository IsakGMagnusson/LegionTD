package game.display.HUD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RightHud extends JPanel{

    JButton buttonT1 = new JButton("Tier 1");
    JButton buttonT2 = new JButton("Tier 2");

    public static int buying = -1;

    public RightHud(){
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
}
