package game.display.HUD.RightHud;


import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.tower.Tower;

public class RightHud extends GameObject {

    private int color = 0xFFfd94ff;

    private static Tower tierOne;
    private static Tower tierTwo;

    private TowerBuyBox tierOneBox;
    private TowerBuyBox tierTwoBox;


    public static int buying = -1;

    public RightHud(double posX, double posY, int width, int height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        this.tierOneBox = new TowerBuyBox(posX+5, posY+5, tierOne);
        this.tierTwoBox = new TowerBuyBox(posX+5, posY+tierOneBox.getHeight(), tierTwo);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        tierOneBox.update(gc, gm, dt);
        tierTwoBox.update(gc, gm, dt);

        tierTwoBox.setPosY(posY+tierOneBox.getHeight());
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, color);
        tierOneBox.render(gc, r);
        tierTwoBox.render(gc, r);
    }


    public static void setTierOneInfo(Tower tierOne) {
        RightHud.tierOne = tierOne;
    }
    public static void setTierTwoInfo(Tower tierTwo) {
        RightHud.tierTwo = tierTwo;
    }
}
