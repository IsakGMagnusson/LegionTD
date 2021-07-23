package game.unit;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Healthbar extends GameObject {
    private Unit barOwner;
    private double hpBar;

    private int hpColor;
    private final int LOWHP_COLOR      = 0xFFFF0000;
    private final int REGULARHP_COLOR  = 0xff229013;
    private final int BACKGROUND_COLOR = 0xff000000;

    int heigth = 2;

    public Healthbar(Unit barOwner){
        this.barOwner = barOwner;
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        hpBar = barOwner.getWidth()*(barOwner.getpercentHealth() * 0.01);
        hpColor = (barOwner.getpercentHealth() <= 33) ? LOWHP_COLOR : REGULARHP_COLOR;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)Math.floor(barOwner.getPosX()), (int)Math.floor(barOwner.getPosY()-4), barOwner.getWidth(), heigth*2, BACKGROUND_COLOR);
        r.drawFillRect((int)Math.floor(barOwner.getPosX()), (int)Math.floor(barOwner.getPosY()-4), (int)hpBar, heigth*2, hpColor);
    }
}
