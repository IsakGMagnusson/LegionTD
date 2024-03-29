package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class GoldPop extends GameObject {

    private int uptime = 1*60;
    private int textFloat = 0;
    private int gold;
    private int goldColor = 0xFFada900;

    public GoldPop(int posX, int posY, int gold){
        this.posX = posX;
        this.posY = posY;
        this.gold = gold;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        uptime -= dt;
        if (uptime % 8 == 0) textFloat++;
        if (uptime <= 0) setDead(true);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawText("+" + gold, (int)posX, (int)posY-textFloat, goldColor, 5);
    }

}
