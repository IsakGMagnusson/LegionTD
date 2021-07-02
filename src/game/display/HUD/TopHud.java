package game.display.HUD;


import javax.swing.*;

public class TopHud extends JPanel {
    private static String info;
    JLabel timeLabel = new JLabel(info);

    public TopHud() {
        this.add(timeLabel);
    }

    public void update() {
        timeLabel.setText(info);
    }

    public static void setInfo(String info) {
        TopHud.info = info;
    }


}
