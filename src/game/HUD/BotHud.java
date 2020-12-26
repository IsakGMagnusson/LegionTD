package game.HUD;
import game.gameplay.GamePlay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotHud extends JPanel {

    public enum SelectedType{
        DEF,
        TOWER,
        ENEMY
    }

    private static SelectedType selectedType = SelectedType.DEF;

    private static String info = "";
    private static Boolean isSelling = false;
    private JLabel towerInfo = new JLabel(info);
    private static JButton sellTower = new JButton("sell");

    public BotHud() {
        this.add(towerInfo);
        this.add(sellTower);

        sellTower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setIsSelling(true);
            }
        });
    }

    public void update() {
        getSell();
        towerInfo.setText(info);
        display();
        setSellingOnOff(GamePlay.getState());
    }

    public static void setInfo(String info) {
        BotHud.info = info;
    }

    private void display(){
        switch (selectedType) {
            case TOWER:
                sellTower.setVisible(true);
                break;

            case ENEMY:
                sellTower.setVisible(false);
                break;

            default: sellTower.setVisible(false);
        }
    }

    public static void setIsSelling(boolean sell){
        isSelling = sell;
    }

    public static void setSellingOnOff(GamePlay.State state){
        if(state.equals(GamePlay.State.BUYTIME)) sellTower.setEnabled(true);
        else sellTower.setEnabled(false);
    }

    public static void setSelectedType(SelectedType seltype){
        selectedType = seltype;
    }

    public static boolean getSell() {
        return isSelling;
    }
}
