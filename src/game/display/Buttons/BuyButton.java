package game.display.Buttons;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.display.HUD.RightHud.RightHud;
import game.unit.tower.Tower;

public class BuyButton extends Button {
    private Tower tower;

    public BuyButton(double posX, double posY, Image image, Tower tower) {
        super(posX, posY, image);
        this.tower = tower;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        super.update(gc,gm,dt);
        if(getIsPressed(gc)){
            RightHud.buying = tower.getTier();
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        super.render(gc, r);
    }

}
