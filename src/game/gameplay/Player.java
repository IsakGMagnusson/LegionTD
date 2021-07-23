package game.gameplay;


import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.display.HUD.BotHud;
import game.gameplay.builder.Builder;
import game.unit.enemy.Enemy;
import game.unit.tower.Tower;

import java.util.ArrayList;

public class Player {

    private Builder builder;
    private GameObject selectedObject;

    private int randT1 = 0;
    private int randT2 = 0;

    private final int unitSelectColor = 0xFF49ffa0;

    private int gold;
    private ArrayList<Tower> ownedTowers = new ArrayList();

    public Player(GamePlay gamePlay){
        this.gold = 100;
        builder = new Builder(this, gamePlay, randT1, randT2);
    }

    public void update(GameContainer gc, GameManager gm) {
        builder.update(gc, gm);

        if(gc.getInput().isButtonDown(1))
            selectedObject = selectUnit(gm, gc);

        //TODO: make this change HUD entirely
        if(selectedObject == null){
            BotHud.setInfo("");
            BotHud.selectedObj = BotHud.SelectedObj.NULL;
        }
        else if(selectedObject instanceof Tower) {
            BotHud.setInfo("Type: " + "Tower | " + "hp: " + (int) ((Tower) selectedObject).getHealth() + " | Dmg: " + (int) ((Tower) selectedObject).getDamage() + "     ");
            BotHud.selectedObj = BotHud.SelectedObj.TOWER;
        }
        else if(selectedObject instanceof Enemy){
            BotHud.setInfo("Type: " + "Enemy | " +"hp: " + (int)((Enemy) selectedObject).getHealth() + " | Dmg: " + (int)((Enemy) selectedObject).getDamage() + "     ");
            BotHud.selectedObj = BotHud.SelectedObj.ENEMY;
        }
    }

    public void render(GameContainer gc, Renderer r) {
        builder.render(gc, r);

        if(selectedObject != null && !selectedObject.isDead())
            r.drawCircle((int)Math.floor(selectedObject.getPosX()+Tower.PLAYER_SIZE/2),
                    (int)Math.floor(selectedObject.getPosY())+Tower.PLAYER_SIZE/2, Tower.PLAYER_SIZE, unitSelectColor,4);
    }

    public GameObject selectUnit(GameManager gm, GameContainer gc){
        for(GameObject object : gm.getObjects()){
            if (object.isHoovered(gc)){
                BotHud.setIsSelling(false);
                return object;
            }
        }
        return selectedObject;
    }

    public int getGold() {
        return gold;
    }

    public void incGold(int gold) {
        this.gold += gold;
    }

    public void decGold(int gold) {
        this.gold -= gold;
    }

    public void addOwnedTower(Tower object){
        ownedTowers.add(object);
    }

    public ArrayList<Tower> getOwnedTowers() {
        return ownedTowers;
    }

    public GameObject getSelectedObject() {
        return selectedObject;
    }

}
