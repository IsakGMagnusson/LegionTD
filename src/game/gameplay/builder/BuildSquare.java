package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;

public class BuildSquare extends GameObject {
    public static final int SQUARE_WIDTH = 64;

    private double posX, posY;
    private boolean isOccupied;

    private int color;
    private int regColor = 0xFFeeabff;
    private int hooveredColor = 0xFFdd56ff;

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
        color = (isSquareHoovered(gc)) ? hooveredColor : regColor;

        r.drawFillRect((int)posX, (int)posY, SQUARE_WIDTH, SQUARE_WIDTH, color);
        r.drawRect((int)posX, (int)posY, SQUARE_WIDTH, SQUARE_WIDTH, 0xFF000000);
    }

    public boolean isSquareHoovered(GameContainer gc){
        if(gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + SQUARE_WIDTH-1
                && gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + SQUARE_WIDTH-1
        ) return true;
         else return false;
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
