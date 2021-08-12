package game.display.Buttons;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;

public class Button extends GameObject {
    private Image image;
    private boolean isActive = true;

    private int borderColor = 0xff000000;
    private int hooveredColor = 0xff5cff35;
    private int regularColor = 0xff000000;

    public Button(double posX, double posY, Image image){
        this.posX = posX;
        this.posY = posY;
        this.width = image.getW();
        this.height = image.getH();
        this.image = image;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        borderColor = isHoovered(gc) ? hooveredColor : regularColor;

        if (leftClick(gc) && getIsActive()) {

        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX-3, (int)posY-3, width+5, height+5, borderColor);
        r.drawImage(image, (int)posX, (int)posY);
    }

    public boolean getIsPressed(GameContainer gc) {
        return isHoovered(gc) && leftClick(gc);
    }

    private boolean leftClick(GameContainer gc){
        return gc.getInput().isButtonDown(1);
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
