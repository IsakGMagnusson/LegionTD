package game.display.popup;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.tower.Tower;

public class TowerInfoBox extends GameObject {
    private Tower tower;
    private int scale = 3;
    private int yMargin = 20;
    private int textColor = 0xff000000;

    private boolean isShrunk = false;


    public TowerInfoBox(double posX, double posY, Tower tower){
        this.posX = posX;
        this.posY = posY;
        this.tower = tower;

        height = getTowerInfo(tower).length * yMargin;
        width = getTowerInfoWidth(getTowerInfo(tower));
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(this.isHoovered(gc) && gc.getInput().isButtonDown(1)){
            isShrunk = !isShrunk;
        }
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
        String[] allInfo = {
                "Tier " + tower.getTier(),
                "Name " + tower.getName(),
                "Dmg " + (int) tower.getDamage(),
                "attack/sec " +  tower.getAttackSpeed(),
                "Health " + (int) tower.getHealth(),
                "Cost " + tower.getCost()
        };
        return allInfo;
    }

    private int getTowerInfoWidth(String[] towerInfo){
        int longestInfoRow = 0;
        for(String string : towerInfo){
            if(string.length() > longestInfoRow){
                longestInfoRow = string.length();
            }
        }

        int fontWidth = 4;
        return (longestInfoRow*scale*fontWidth)+3;
    }
}
