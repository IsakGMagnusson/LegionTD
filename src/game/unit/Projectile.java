package game.unit;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class Projectile extends GameObject {

    private int color;
    private final int SPEED = 1;
    private Unit target;

    public Projectile(Unit target, double posX, double posY, int width, int height, int color){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.color = color;
        this.target = target;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        moveTowardsUnit(target);

        if(posX >= target.getPosX() && target.getPosX() + target.getWidth() >= posX &&
                posY >= target.getPosY() && target.getPosY() + target.getHeight() >= posY){
           setDead(true);
        }

        if(target.isDead())
            setDead(true);

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int) posX, (int) posY, width, height, color);

    }

    private void moveTowardsUnit(GameObject objMoveTo){
        if(objMoveTo.getPosX() > posX){
            posX += SPEED;
        }
        if(objMoveTo.getPosX() < posX){
            posX -= SPEED;
        }
        if(objMoveTo.getPosY() < posY){
            posY -= SPEED;
        }
        if(objMoveTo.getPosY() > posY){
            posY += SPEED;
        }
    }
}
