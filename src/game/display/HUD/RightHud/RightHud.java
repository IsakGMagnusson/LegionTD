package game.display.HUD.RightHud;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.unit.tower.TowerFactory;

public class RightHud  {
    private TowerFactory towerFactory = new TowerFactory();

    private int color = 0xFFfda3ff;
    private int rightHUDwidth = 150;
    private int posX, posY, width, height;
    private int xMargin = 75;
    private int yMargin = 75;

    private UnitDisplay[] unitDisplays;

    public static int buying = -1;

    public RightHud(int[] towerIDs){
        this.posX = GameManager.SCREEN_WIDTH-rightHUDwidth;
        this.posY = 12; //tophud.height
        this.width = rightHUDwidth;
        this.height = GameManager.SCREEN_HEIGHT;

        UnitDisplay tier1Display = new UnitDisplay(posX, posY, towerFactory.getTier1(towerIDs[0], 0, 0, null));
        UnitDisplay tier2Display = new UnitDisplay(posX+xMargin, posY, towerFactory.getTier2(towerIDs[1], 0, 0, null));
        UnitDisplay tier3Display = new UnitDisplay(posX, posY+yMargin, towerFactory.getTier3(towerIDs[2], 0, 0, null));
        UnitDisplay tier34isplay = new UnitDisplay(posX+xMargin, posY+yMargin, towerFactory.getTier4(towerIDs[3], 0, 0, null));

        unitDisplays = new UnitDisplay[]{tier1Display, tier2Display, tier3Display, tier34isplay};
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

        //this is rendered here and not in UnitDisplay to avoid new buttons cover brieftowerinfo
        for(UnitDisplay unitDisplay : unitDisplays){
            if(unitDisplay.getBuyButton().isHoovered(gc)){
                unitDisplay.getBriefTowerInfoBox().render(gc, r);
            }
        }

    }

}
