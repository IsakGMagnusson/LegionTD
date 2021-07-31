package game.unit;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.enemy.Enemy;

public class Ability {
    private Unit abilityOwner;
    private int radius = 150;
    private double posX, posY;
    private double cooldown = 1;
    private double timer;

    public Ability(Unit unit){
        this.abilityOwner = unit;
        this.posX = unit.getPosX();
        this.posY = unit.getPosY();
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        posX = abilityOwner.getPosX();
        posY = abilityOwner.getPosY();

        timer += dt;

        if(timer >= cooldown){
            for(GameObject enemy : gm.getObjects()){
                if(enemy instanceof Enemy){
                    if(((Enemy) enemy).getDistTo(abilityOwner) <= radius){
                        ((Enemy) enemy).setHealth(((Enemy) enemy).getHealth()-(0.05*((Enemy) enemy).getMaxHealth()));
                    }
                }
            }
            timer = 0;
        }

    }

    public void render(GameContainer gc, Renderer r) {
        r.drawCircle((int)posX+abilityOwner.getWidth()/2, (int)posY+abilityOwner.getHeight()/2, radius, 0xffbbbbbb, 2);
    }
}
