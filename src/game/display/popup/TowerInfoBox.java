package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.tower.Tower;

public class TowerInfoBox extends GameObject {
    private Tower tower;
    private boolean keepShowing = false;

    public TowerInfoBox(Tower tower){
        this.tower = tower;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(keepShowing){

        } else{
            setDead(true);
        }

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        int posX = GameManager.SCREEN_WIDTH/2;
        int posY = GameManager.SCREEN_HEIGHT/2;

        r.drawFillRect(posX, posY,500, 2000, 0xFFFFFFFF);
        r.drawText("Name: " + tower.getName(), posX+20, posY+20, 0xff000000);
        r.drawText("Dmg: " + tower.getDamage(), posX+20, posY+40, 0xff000000);
        r.drawText("Health: " + tower.getHealth(), posX+20, posY+60, 0xff000000);
        r.drawText("Health: " + tower.getCost(), posX+20, posY+80, 0xff000000);

    }
}
