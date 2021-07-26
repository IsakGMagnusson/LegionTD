package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.tower.Tower;

public class TowerInfoBox extends GameObject {
    private Tower tower;
    private boolean keepShowing = false;
    private int scale = 3;
    private int yMargin = 20;

    public TowerInfoBox(double posX, double posY, int width, int height,  Tower tower){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
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
        r.drawText("Name: " + tower.getName(), (int)posX, (int)posY+yMargin, 0xff000000, scale);
        r.drawText("Dmg: " + tower.getDamage(), (int)posX, (int)posY+yMargin*2, 0xff000000, scale);
        r.drawText("Health: " + tower.getHealth(), (int)posX,(int) posY+yMargin*3, 0xff000000, scale);
        r.drawText("Cost: " + tower.getCost(), (int)posX, (int)posY+yMargin*4, 0xff000000, scale);
    }
}
