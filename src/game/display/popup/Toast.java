package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Toast extends GameObject {

    int boxHeight = 20*4;
    int uptime = 2*60;
    String text;
    boolean isGood;
    int goodColor = 0xff19ff26;
    int badColor  = 0xffff160b;
    int color;

    public Toast(String text, boolean isGood){
        this.text = text;
        this.isGood = isGood;
        color = isGood ? goodColor : badColor;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        uptime -= dt;
        if (uptime <= 0) setDead(true);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        int posX = GameManager.SCREEN_WIDTH/2-(text.length()*5*5)/2;
        int posY = GameManager.SCREEN_HEIGHT-boxHeight-5;

        //magic number (*5) is text scale in drawText in renderer
        r.drawFillRect(posX, posY,text.length()*5*5, boxHeight, color);
        r.drawText(text, posX+(text.length())/2, posY+20, 0xffffffff);
    }
}