package game.HUD;
import game.gameplay.GamePlay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotHud extends JPanel {

    private static String info = "";
    private static Boolean isSelling = false;
    private JLabel towerInfo = new JLabel(info);
    private static JButton sellTower = new JButton("sell");

    public enum SelectedObj{
        NULL,
        TOWER,
        ENEMY
    }
    public static SelectedObj selectedObj = SelectedObj.NULL;


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
        setSellingOnOff(GamePlay.getState());
        selectHUD();
    }

    public static void setInfo(String info) {
        BotHud.info = info;
    }

    public static void setIsSelling(boolean sell){
        isSelling = sell;
    }

    public static void setSellingOnOff(GamePlay.State state){
        if(state.equals(GamePlay.State.BUYTIME)) sellTower.setEnabled(true);
        else sellTower.setEnabled(false);
    }

    public static boolean getSell() {
        return isSelling;
    }

    private void selectHUD(){
        switch (selectedObj) {
            case ENEMY:
                enemyHUD();
                break;

            case TOWER:
                towerHUD();
                break;

            default:
                nullHUD();
        }
    }

    //TODO: make different HUDS instead of one HUD hiding/showing components
    private void towerHUD(){
        towerInfo.setVisible(true);
        sellTower.setVisible(true);
    }

    private void enemyHUD(){
        sellTower.setVisible(false);
        towerInfo.setVisible(true);
    }

    private void nullHUD(){
        sellTower.setVisible(false);
        towerInfo.setVisible(false);
    }

}
