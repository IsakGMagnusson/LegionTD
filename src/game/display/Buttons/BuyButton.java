package game.display.Buttons;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.display.HUD.RightHud.RightHud;
import game.display.popup.Toast;
import game.display.popup.TowerInfoBox;
import game.unit.tower.Tower;

public class BuyButton extends Button {
    private Tower tower;
    private TowerInfoBox towerInfoBox;

    public BuyButton(double posX, double posY, int width, int height, Image image, Tower tower) {
        super(posX, posY, width, height, image);
        this.tower = tower;

        this.towerInfoBox = new TowerInfoBox(posX-200, posY, tower);
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

        if(isHoovered(gc)){
            towerInfoBox.render(gc, r);
        }
    }


}
