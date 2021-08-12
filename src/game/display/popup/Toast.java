package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Toast extends GameObject {

    private int boxHeight = 20*4;
    private int uptime = 2*60;
    private String text;
    private int goodColor = 0xff5cff5d;
    private int badColor  = 0xffff273b;
    private int color;

    public Toast(String text, boolean isGood){
        this.text = text;
        color = isGood ? goodColor : badColor;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        uptime -= dt;
        if (uptime <= 0) setDead(true);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        posX = GameManager.SCREEN_WIDTH/2-(text.length()*5*5)/2;
        posY = GameManager.SCREEN_HEIGHT-boxHeight-35;

        //magic number (*5) is text scale in drawText in renderer
        r.drawFillRect((int)posX, (int)posY-50,text.length()*5*5, boxHeight, color);
        r.drawText(text, (int)posX+(text.length())/2, (int)posY+20-50, 0xff000000, 5);
    }
}