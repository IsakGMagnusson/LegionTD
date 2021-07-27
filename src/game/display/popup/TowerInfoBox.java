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
        r.drawRect((int)posX, (int)posY, width, height, 0xFF000000);

        int infoCounter = 0;

        if(!isShrunk){
            setHeight(getTowerInfo(tower).length * yMargin);
            for(String info : getTowerInfo(tower)){
                r.drawText(info, (int)posX, (int)posY+yMargin*infoCounter, textColor, scale);
                infoCounter++;
            }
        } else{
            setHeight(yMargin);
            r.drawText(getTowerInfo(tower)[0], (int)posX, (int)posY+yMargin*infoCounter, textColor, scale);
        }
    }

    private String[] getTowerInfo(Tower tower){
        String[] allInfo = {
                "Tier: " + tower.getTier(),
                "Name: " + tower.getName(),
                "Dmg: " + tower.getDamage(),
                "Health: " + tower.getHealth(),
                "Cost: " + tower.getCost()
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
        return longestInfoRow*scale*fontWidth;
    }

    private void shrink(){
        if(isShrunk){

        } else{
        }
    }


}
