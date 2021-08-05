package game.display.HUD.RightHud;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.unit.tower.Tower;

public class RightHud  {

    private int color = 0xFFfda3ff;
    private int rightHUDwidth = 150;

    private int posX, posY, width, height;
    private int xMargin = 75;
    private int yMargin = 75;

    private static Tower tierOne;
    private static Tower tierTwo;
    private static Tower tierThree;

    private UnitDisplay[] unitDisplays;

    public static int buying = -1;


    public RightHud(){
        this.posX = GameManager.SCREEN_WIDTH-rightHUDwidth;
        this.posY = 12; //tophud.height
        this.width = rightHUDwidth;
        this.height = GameManager.SCREEN_HEIGHT;

        UnitDisplay tier1Display = new UnitDisplay(posX, posY, tierOne);
        UnitDisplay tier2Display = new UnitDisplay(posX+xMargin, posY, tierTwo);
        UnitDisplay tier3Display = new UnitDisplay(posX, posY+yMargin, tierThree);
        unitDisplays = new UnitDisplay[]{tier1Display, tier2Display, tier3Display};
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        for(UnitDisplay unitDisplay : unitDisplays){
            unitDisplay.update(gc, gm, dt);
        }
    }

    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect(posX, posY, width, height, color);

        for(UnitDisplay unitDisplay : unitDisplays){
            unitDisplay.render(gc, r);
        }
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
