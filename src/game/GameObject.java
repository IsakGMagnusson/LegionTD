package game;

import engine.GameContainer;
import engine.Renderer;

public abstract class GameObject {

    protected double posX, posY;
    protected int width, height;
    protected boolean dead = false;
    protected int rotation = 0;

    public abstract void update(GameContainer gc, GameManager gm, float dt);
    public abstract void render(GameContainer gc, Renderer r);

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isHoovered(GameContainer gc){
        if(gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + width
                && gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + height
        ){
            return true;
        } else{
            return false;
        }
    }

    protected void moveTowardsUnit(GameObject objMoveTo, double speed){
        double distance =  Math.sqrt((objMoveTo.getPosX() - posX)*(objMoveTo.getPosX() - posX) + (objMoveTo.getPosY() - posY)*(objMoveTo.getPosY() - posY)); //calculates the distance between the two points
        double speedX = (objMoveTo.getPosX() - posX) /distance;
        double speedY = (objMoveTo.getPosY() - posY) /distance;

        posX += speed*speedX;
        posY += speed*speedY;
    }
}
