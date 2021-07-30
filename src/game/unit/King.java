package game.unit;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.ImageTile;
import game.GameManager;
import game.GameObject;
import game.unit.enemy.Enemy;

public class King extends Unit {
    private int kingSize = 64;
    protected double nextAttack = 0;
    protected Healthbar healthbar = new Healthbar(this);
    protected Unit unitToAttack;

    ImageTile imageTile = new ImageTile("/images/king/king.png", kingSize, kingSize);

    public King(){
        this.posX = 250;
        this.posY = 700;
        this.width = kingSize;
        this.height = kingSize;
        this.maxHealth = 1000;
        this.health = maxHealth;
        this.percentHealth = (health/maxHealth) * 100;
        this.damage = 100;
        this.attackSpeed = 2;
        this.range = 100;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(health <= 0) setDead(true);

        nextAttack += dt;

        if(detectUnit(gm)){
            if(getDistTo(unitToAttack) <= range){
                if(attackSpeed <= nextAttack){
                    attack(gm);
                }
            }
        }

        healthbar.update(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImageTile(imageTile, (int)this.posX, (int)this.posY, 0, 0, 1, 180);
        r.drawCircle((int) ((int) posX+(range/2)), (int) ((int) posY+(range/2)), (int) range, 0xff9392ff, 1);
        healthbar.render(gc, r);
    }

    private void attack(GameManager gm){
        gm.getObjects().add(new Projectile(unitToAttack, damage, posX+(width/2), posY, 5, 5, 0xff00FFFF));
        nextAttack = 0;
    }

    private boolean detectUnit(GameManager gm){
        //if enemy detected
        for(GameObject objDetected : gm.getObjects()){
            if(getDistTo(objDetected) <= view && objDetected instanceof Enemy) {
                hasDetected = true;
                unitToAttack = (Unit) objDetected;
                return true;
            }
        }

        hasDetected = false;
        unitToAttack = null;
        return false;
    }
}
