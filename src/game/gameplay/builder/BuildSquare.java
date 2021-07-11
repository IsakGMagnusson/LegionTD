package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;

public class BuildSquare {
    public static final int SQUARE_WIDTH = 15*3;

    private int posX, posY;
    private boolean isOccupied;

    private int color;
    private int regColor = 0xFFeeabff;
    private int hooveredColor = 0xFFdd56ff;

    public BuildSquare(int posX, int posY){
        this.isOccupied = false;
        this.posX = posX;
        this.posY = posY;
    }

    public void render(GameContainer gc, Renderer r) {
        color = (isSquareHoovered(gc)) ? hooveredColor : regColor;
        r.drawFillRect( getPosX(),  getPosY(), SQUARE_WIDTH, SQUARE_WIDTH, color);

        r.drawRect(posX, posY ,SQUARE_WIDTH, SQUARE_WIDTH, 0xFF000000);
    }

    public boolean isSquareHoovered(GameContainer gc){
        if(gc.getInput().getMouseX() >= posX && gc.getInput().getMouseX() <= posX + SQUARE_WIDTH-1
                && gc.getInput().getMouseY() >= posY && gc.getInput().getMouseY() <= posY + SQUARE_WIDTH-1
        ) return true;
         else return false;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public boolean getIsOccupied(){
        return isOccupied;
    }

    public void setIsOccupied(boolean b){
        isOccupied = b;
    }

}
