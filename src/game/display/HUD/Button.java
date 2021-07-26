package game.display.HUD;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;

public class Button extends GameObject {
    private Image image;
    private boolean isPressed;
    private int borderColor = 0xff000000;
    private int hooveredColor = 0xffc9cd00;
    private int regularColor = 0xff000000;



    public Button(double posX, double posY, int width, int height, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        borderColor = isHoovered(gc) ? hooveredColor : regularColor;

        if(isHoovered(gc)){
           leftClick(gc);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX-3, (int)posY-3, width+5, height+5, borderColor);
        r.drawImage(image, (int)posX, (int)posY);
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    private void leftClick(GameContainer gc){
        if(gc.getInput().isButtonDown(1)){
            isPressed = true;
        } else{
            isPressed = false;
        }
    }
}
