package game.display.HUD.RightHud;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.display.Buttons.BuyButton;
import game.unit.tower.Tower;

public class UnitDisplay {

    private BuyButton buyButton;
    private double buyButtonSize = 1.2;
    private double posX, posY;
    private Tower tower;
    String ability;

    public UnitDisplay(double posX, double posY, Tower tower){
        this.posX = posX;
        this.posY = posY;
        this.tower = tower;

        if(tower.getUnitAbilities() != null) ability = tower.getUnitAbilities()[0].getName();
        else ability = "";

        buyButton = new BuyButton(posX+30, posY+15, new Image(tower.getPath()+"icon.png", buyButtonSize), tower);
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        buyButton.update(gc, gm, dt);
    }

    public void render(GameContainer gc, Renderer r) {
        buyButton.render(gc, r);
        r.drawText(ability, (int)posX, (int)posY+80, 0xFF000000,2);
    }


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

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }
}
