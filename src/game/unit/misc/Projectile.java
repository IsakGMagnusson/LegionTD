package game.unit.misc;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;
import game.unit.Unit;

public class Projectile extends GameObject {

    private final double SPEED = 2.2;
    private Unit target;
    private double damage;
    Image image =  new Image("/images/bullet.png", 0.5);

    public Projectile(Unit target, double damage, double posX, double posY, int width, int height){
        this.target = target;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        moveTowardsUnit(target, SPEED);

        if(hasHitTarget(target)){
            target.setHealth(target.getHealth()-damage);
            target.setpercentHealth((target.getHealth()/target.getMaxHealth()) * 100);
            setDead(true);
        }

        if(target.isDead()){
            setDead(true);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image, (int) posX, (int) posY);
    }

    private boolean hasHitTarget(Unit target){
        if(posX >= target.getPosX() && target.getPosX() + target.getWidth() >= posX &&
                posY >= target.getPosY() && target.getPosY() + target.getHeight() >= posY){
            return true;
        }

        return false;
    }
}
