package game.unit.ability;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.unit.Unit;
import game.unit.enemy.Enemy;

public class DeathAndDecay extends Ability {
    public DeathAndDecay(Unit unit) {
        super(unit);
        this.name = "Death and Decay";
        this.castRange = 150;
        this.cooldown = 0.5;
        this.damage = 0.01;
        this.description = "Deals " + damage*100 + "% of enemy maxhealth every " + cooldown + " seconds in a " + castRange + " radius.";
        this.icon = new Image("/images/deathanddecay.png", 1);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        posX = abilityOwner.getPosX();
        posY = abilityOwner.getPosY();

        timer += dt;

        if(timer >= cooldown){
            for(Enemy enemy : gm.getEnemies()){
                if((enemy.getDistTo(abilityOwner) <= castRange)){
                    enemy.setHealth(enemy.getHealth()-(damage*(enemy.getMaxHealth())));
                }
            }
            setTimer(0);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawCircle((int)posX+abilityOwner.getWidth()/2, (int)posY+abilityOwner.getHeight()/2, castRange, 0xff0da500, 4);
    }
}
