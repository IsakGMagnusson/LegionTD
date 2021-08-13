package game.display.HUD.RightHud.InfoBox;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.display.LetterSizes;
import game.unit.tower.Tower;

public class BriefTowerInfoBox extends GameObject {
    private Tower tower;
    private int scale = 2;
    private int yMargin = 20;
    private int textColor = 0xff000000;

    public BriefTowerInfoBox(double posX, double posY, Tower tower){
        this.height = getTowerInfo(tower).length * yMargin;
        this.width = getTowerInfoWidth(getTowerInfo(tower));

        this.posX = posX-width-15;
        this.posY = posY;
        this.tower = tower;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, 0xFFb1ffae);

        int infoCounter = 0;
        setHeight(getTowerInfo(tower).length * yMargin);
        for(String info : getTowerInfo(tower)){
            r.drawText(info, (int)posX, (int)posY+yMargin*infoCounter, textColor, scale);
            infoCounter++;
        }
    }

    private String[] getTowerInfo(Tower tower){
        return new String[]{
                "TIER: " + tower.getTier(),
                "NAME: " + tower.getName(),
                "HEALTH: " + (int) tower.getHealth(),
                "DMG: " + (int) tower.getDamage(),
                "COST: " + tower.getCost()
        };
    }

    private int getTowerInfoWidth(String[] towerInfo){
        int longestInfoRow = 0;

        for(String string : towerInfo){
            if(LetterSizes.stringWidth(string) > longestInfoRow){
                longestInfoRow = LetterSizes.stringWidth(string);
            }
        }

        return (longestInfoRow*scale);
    }
}
