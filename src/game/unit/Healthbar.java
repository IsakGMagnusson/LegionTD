package game.unit;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Healthbar extends GameObject {
    private Unit barOwner;

    private int hpColor;
    private final int LOWHPCOLOR = 0xFFFF0000;
    private final int REGHPCOLOR = 0xff33ff33;
    private double hpBar;

    public Healthbar(Unit barOwner){
        this.barOwner = barOwner;
        hpBar = barOwner.getWidth();
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        hp();
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)Math.floor(barOwner.getPosX()-1), (int)Math.floor(barOwner.getPosY()-4), barOwner.getWidth(), 2, 0xff000000);
        r.drawFillRect((int)Math.floor(barOwner.getPosX()-1), (int)Math.floor(barOwner.getPosY()-4), (int) hpBar, 2, hpColor);
    }

    private void hp(){
        hpBar = barOwner.getWidth()*(barOwner.getpercentHealth() * 0.01);
        if(barOwner.getpercentHealth() < 33) hpColor = LOWHPCOLOR;
        else hpColor = REGHPCOLOR;
    }
}
