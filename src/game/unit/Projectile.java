package game.unit;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Projectile extends GameObject {

    private int color;
    private final double SPEED = 0.9;
    private Unit target;
    private double damage;

    public Projectile(Unit target, double damage, double posX, double posY, int width, int height, int color){
        this.target = target;
        this.damage = damage;
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        moveTowardsUnit(target, SPEED);

        if(hasHitTarget(target)){
            target.setHealth(target.getHealth()-damage);
            target.setpercentHealth((target.getHealth()/target.getMaxHealth()) * 100);
            setDead(true);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int) posX, (int) posY, width, height, color);
    }

    private boolean hasHitTarget(Unit target){
        if(posX >= target.getPosX() && target.getPosX() + target.getWidth() >= posX &&
                posY >= target.getPosY() && target.getPosY() + target.getHeight() >= posY){
            return true;
        }
        return false;
    }
}
