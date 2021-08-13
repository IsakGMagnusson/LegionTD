package game.display.HUD.RightHud;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.display.Buttons.BuyButton;
import game.display.HUD.RightHud.InfoBox.BriefTowerInfoBox;
import game.display.HUD.RightHud.InfoBox.DetailedTowerInfoBox;
import game.unit.tower.Tower;

public class UnitDisplay {

    private BuyButton buyButton;
    private BriefTowerInfoBox briefTowerInfoBox;
    private DetailedTowerInfoBox detailedTowerInfoBox;

    private double buyButtonSize = 1;
    private double posX, posY;
    private Tower tower;


    public UnitDisplay(double posX, double posY, Tower tower){
        this.posX = posX;
        this.posY = posY;
        this.tower = tower;

        buyButton = new BuyButton(posX+5, posY+10, new Image(tower.getPath()+"icon.png", buyButtonSize), tower);
        this.briefTowerInfoBox = new BriefTowerInfoBox(posX, posY, tower);
        detailedTowerInfoBox = new DetailedTowerInfoBox(tower);
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        buyButton.update(gc, gm, dt);
    }

    public void render(GameContainer gc, Renderer r) {
        buyButton.render(gc, r);

        if(buyButton.isHoovered(gc) && gc.getInput().isButton(3)){
            detailedTowerInfoBox.render(gc, r);
        }
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

    public BuyButton getBuyButton() {
        return buyButton;
    }

    public BriefTowerInfoBox getBriefTowerInfoBox() {
        return briefTowerInfoBox;
    }
}
