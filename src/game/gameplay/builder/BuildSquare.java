package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class BuildSquare extends GameObject {
    public static final int SQUARE_WIDTH = 64;

    private double posX, posY;
    private boolean isOccupied;

    private int regColor = 0xFFeeabff;
    private int hooveredColor = 0xFFffd6fe;
    private int occupiedColor = 0xFFe685ff;

    public BuildSquare(int posX, int posY){
        this.isOccupied = false;
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        if(isOccupied){
            r.drawFillRect((int)posX, (int)posY, SQUARE_WIDTH, SQUARE_WIDTH, occupiedColor);
        } else if(isSquareHoovered(gc)){
            r.drawFillRect((int)posX, (int)posY, SQUARE_WIDTH, SQUARE_WIDTH, hooveredColor);
        } else{
            r.drawFillRect((int)posX, (int)posY, SQUARE_WIDTH, SQUARE_WIDTH, regColor);
        }

        r.drawRect((int)posX, (int)posY, SQUARE_WIDTH, SQUARE_WIDTH, 0xFF000000);
    }

    public boolean isSquareHoovered(GameContainer gc){
        return gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + SQUARE_WIDTH - 1
                && gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + SQUARE_WIDTH - 1;
    }

    public double getPosX(){
        return posX;
    }

    public double getPosY(){
        return posY;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public boolean getIsOccupied(){
        return isOccupied;
    }

    public void setIsOccupied(boolean b){
        isOccupied = b;
    }
}
