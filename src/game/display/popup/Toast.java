package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Toast extends GameObject {

    int uptime = 2*60;
    String text;
    boolean isGood;
    int goodColor = 0xff19ff26;
    int badColor  = 0xffff160b;
    int color;
    int posX = 200;
    int posY = (1080/4)-25;

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
        r.drawFillRect(posX, posY,text.length()*5, 20, color);
        r.drawText(text, posX+5, posY+7, 0xffffffff);
    }
}