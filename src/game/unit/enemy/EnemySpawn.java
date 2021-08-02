package game.unit.enemy;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;

public class EnemySpawn extends GameObject {

    private double posX = 100+(64*4)/2;
    double posY  = 60-(64*4)/2;
    Image image =  new Image("/images/enemyspawn.png", 1);

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {

    }

    public void render(GameContainer gc, Renderer r) {
        r.drawImage(image, (int)posX, (int)posY);
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public void setPosX(double posX) {
        this.posX = posX;
    }

    @Override
    public void setPosY(double posY) {
        this.posY = posY;
    }
}
