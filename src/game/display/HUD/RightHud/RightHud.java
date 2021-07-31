package game.display.HUD.RightHud;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;
import game.display.Buttons.BuyButton;
import game.unit.tower.Tower;

public class RightHud extends GameObject {

    private int color = 0xFFfd94ff;
    private int rightHUDwidth = 200;


    private static Tower tierOne;
    private static Tower tierTwo;
    private static Tower tierThree;

    private BuyButton tier1BuyButton;
    private BuyButton tier2BuyButton;
    private BuyButton tier3BuyButton;

    private double buyButtonSize = 1.5;

    public static int buying = -1;


    public RightHud(){
        this.posX = GameManager.SCREEN_WIDTH-rightHUDwidth;
        this.posY = 0;
        this.width = rightHUDwidth;
        this.height = GameManager.SCREEN_HEIGHT;

        tier1BuyButton = new BuyButton(posX+30, posY+15, new Image(tierOne.getPath()+"icon.png", buyButtonSize), tierOne);
        tier2BuyButton = new BuyButton(posX+30, posY+tier1BuyButton.getPosY()+(tier1BuyButton.getHeight()*tierTwo.getTier()),
                 new Image(tierTwo.getPath()+"icon.png", buyButtonSize), tierTwo);
        tier3BuyButton = new BuyButton(posX+30, posY+tier2BuyButton.getPosY()+(tier1BuyButton.getHeight()*tierTwo.getTier()),
                new Image(tierThree.getPath()+"icon.png", buyButtonSize), tierThree);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        tier1BuyButton.update(gc, gm, dt);
        tier2BuyButton.update(gc, gm, dt);
        tier3BuyButton.update(gc, gm, dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, color);
        tier1BuyButton.render(gc, r);
        tier2BuyButton.render(gc, r);
        tier3BuyButton.render(gc, r);
    }


    public static void setTierOneInfo(Tower tierOne) {
        RightHud.tierOne = tierOne;
    }
    public static void setTierTwoInfo(Tower tierTwo) {
        RightHud.tierTwo = tierTwo;
    }
    public static void setTierThreeInfo(Tower tierThree) {
        RightHud.tierThree = tierThree;
    }

}
