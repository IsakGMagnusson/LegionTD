package engine;

import game.HUD.BotHud;
import game.HUD.RightHud;
import game.HUD.TopHud;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Window {

    private JFrame frame;
    private BufferedImage image;
    private Canvas canvas;
    private BufferStrategy bs;
    private Graphics g;

    private RightHud rightHud = new RightHud();
    private TopHud topHud = new TopHud();
    private BotHud botHud = new BotHud();


    public Window(GameContainer gc){
        image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension s = new Dimension((int)(gc.getWidth() * gc.getScale()), (int)(gc.getHeight() * gc.getScale()));
        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);

        frame = new JFrame(gc.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);


        rightHud.setLayout(new BoxLayout(rightHud, BoxLayout.Y_AXIS));
        frame.add(rightHud, BorderLayout.EAST);

        topHud.setLayout(new BoxLayout(topHud, BoxLayout.X_AXIS));
        frame.add(topHud, BorderLayout.NORTH);

        botHud.setLayout(new BoxLayout(botHud, BoxLayout.X_AXIS));
        frame.add(botHud, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
        g = bs.getDrawGraphics();

    }

    public void update(){
        topHud.update();
        botHud.update();

        g.drawImage(image,0,0,canvas.getWidth(),canvas.getHeight(), null);
        bs.show();

    }

    public Canvas getCanvas() {
        return canvas;
    }

    public BufferedImage getImage() {
        return image;
    }

    public JFrame getFrame() {
        return frame;
    }
}


