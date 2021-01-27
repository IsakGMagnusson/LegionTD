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
    private final int REGULARHP_COLOR  = 0xff33ff33;
    private final int BACKGROUND_COLOR = 0xff000000;


    public Healthbar(Unit barOwner){
        this.barOwner = barOwner;
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        hpBar = barOwner.getWidth()*(barOwner.getpercentHealth() * 0.01);
        hpColor = (barOwner.getpercentHealth() <= 33) ? LOWHP_COLOR : REGULARHP_COLOR;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)Math.floor(barOwner.getPosX()-1), (int)Math.floor(barOwner.getPosY()-4), barOwner.getWidth(), 2, BACKGROUND_COLOR);
        r.drawFillRect((int)Math.floor(barOwner.getPosX()-1), (int)Math.floor(barOwner.getPosY()-4), (int) hpBar, 2, hpColor);
    }
}
