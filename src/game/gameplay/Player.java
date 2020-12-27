package game.gameplay;


import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.HUD.BotHud;
import game.gameplay.builder.Builder;
import game.unit.Unit;
import game.unit.enemy.Enemy;
import game.unit.tower.Tower;
import game.unit.tower.TowerFactory;

import java.util.ArrayList;

public class Player {

    private Builder builder;
    private GameObject selectedObject;

    private TowerFactory towerFactory = new TowerFactory();
    private int randT1 = 0;
    private int randT2 = 0;

    private int gold;
    private Tower[] availableTowers = new Tower[]{createTier1(), createTier2()};
    private ArrayList<Tower> ownedTowers = new ArrayList();

    public Player(GamePlay gamePlay){
        this.gold = 100;
        builder = new Builder(this, gamePlay);
    }

    public void update(GameContainer gc, GameManager gm) {
        builder.update(gc, gm);


        if(gc.getInput().isButtonDown(1))
            selectedObject = selectUnit(gm, gc);


        //TODO: make this change HUD entirely
        if(selectedObject == null){
            BotHud.setInfo("");
            BotHud.selectedObj = BotHud.SelectedObj.NULL;

            System.out.println("null");

        }
        else if(selectedObject instanceof Tower) {
            BotHud.setInfo("hp: " + (int) ((Tower) selectedObject).getHealth() + " | Dmg: " + (int) ((Tower) selectedObject).getDamage() + "     ");
            BotHud.selectedObj = BotHud.SelectedObj.TOWER;
            System.out.println("tower");
        }
        else if(selectedObject instanceof Enemy){
            BotHud.setInfo("hp: " + (int)((Enemy) selectedObject).getHealth() + " | Dmg: " + (int)((Enemy) selectedObject).getDamage() + "     ");
            BotHud.selectedObj = BotHud.SelectedObj.ENEMY;

            System.out.println("enemy");
        }


    }

    public void render(GameContainer gc, Renderer r) {

        builder.render(gc, r);

        if(selectedObject != null)
            r.drawRect((int)Math.floor(selectedObject.getPosX()), (int)Math.floor(selectedObject.getPosY()), Tower.PLAYER_SIZE, Tower.PLAYER_SIZE, 0xff00FA9A);
    }


    public GameObject selectUnit(GameManager gm, GameContainer gc){
        for(GameObject object : gm.getObjects()){
            if (object.isHoovered(gc)){
                return object;
            }
        }
        return null;
    }

    public Tower createTier1(){
        return towerFactory.getTier1(randT1);
    }
    public Tower createTier2(){
        return towerFactory.getTier2(randT2);
    }

    public Tower[] getTowers(){
        return availableTowers;
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

    public void setSelectedObject(GameObject selectedObject) {
        this.selectedObject = selectedObject;
    }

}
