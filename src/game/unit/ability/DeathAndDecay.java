package game.unit.ability;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.Unit;
import game.unit.enemy.Enemy;

public class DeathAndDecay extends Ability {
    private int castRange = 150;
    private int cooldown = 1;

    public DeathAndDecay(Unit unit) {
        super(unit);
        this.name = "Death and Decay";
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        posX = abilityOwner.getPosX();
        posY = abilityOwner.getPosY();

        timer += dt;

        if(timer >= cooldown){
            for(Enemy enemy : gm.getEnemies()){
                if((enemy.getDistTo(abilityOwner) <= castRange)){
                    enemy.setHealth(enemy.getHealth()-(0.05*(enemy.getMaxHealth())));
                }
            }
            setTimer(0);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawCircle((int)posX+abilityOwner.getWidth()/2, (int)posY+abilityOwner.getHeight()/2, castRange, 0xffbbbbbb, 2);
    }
}
