package game.display.HUD.RightHud;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;
import game.display.Button;
import game.display.popup.TowerInfoBox;
import game.unit.tower.Tower;

public class TowerBuyBox extends GameObject {

    private Tower tower;
    private Button buyButton;
    private TowerInfoBox towerInfoBox;


    public TowerBuyBox(double posX, double posY, Tower tower){
        this.posX = posX;
        this.posY = posY;
        this.tower = tower;

       buyButton = new Button(posX+30, posY, 32, 32, new Image(tower.getPath()+"icon.png"));
       towerInfoBox = new TowerInfoBox(posX, posY+buyButton.getHeight()+5, tower);

       height = (int) (buyButton.getPosY()+buyButton.getHeight() + towerInfoBox.getHeight() + towerInfoBox.getPosY());
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        buyButton.update(gc, gm, dt);
        towerInfoBox.update(gc, gm, dt);

        height = (int) (buyButton.getPosY()+buyButton.getHeight() + towerInfoBox.getHeight() + towerInfoBox.getPosY());

        buyButton.setPosY(posY);
        towerInfoBox.setPosY(posY+buyButton.getHeight()+5);

        if(buyButton.getIsPressed()){
            RightHud.buying = tower.getTier();
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        buyButton.render(gc, r);
        towerInfoBox.render(gc, r);
    }
}
