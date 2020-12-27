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
    private Unit isSelecting;

    TowerFactory towerFactory = new TowerFactory();
    int randT1 = 0;
    int randT2 = 0;

    private int gold;
    private Tower[] availableTowers = new Tower[]{createTier1(), createTier2()};


    private ArrayList<Tower> ownedTowers = new ArrayList();

    public Player(GamePlay gamePlay){
        this.gold = 100;
        builder = new Builder(this, gamePlay);
    }

    public void update(GameContainer gc, GameManager gm) {
        builder.update(gc, gm);

        //TODO: make selected belong to player and not each unit and fix this
        for(GameObject obj : gm.getObjects()){
            if(obj instanceof Unit){
                if (obj instanceof Tower && ((Tower) obj).getIsSelected()){
                    BotHud.setSelectedType(BotHud.SelectedType.TOWER);
                } else if  (obj instanceof Enemy && ((Enemy) obj).getIsSelected()){
                    BotHud.setSelectedType(BotHud.SelectedType.ENEMY);
                } else{
                    BotHud.setSelectedType(BotHud.SelectedType.DEF);
                }
            }

        }
    }

    public void render(GameContainer gc, Renderer r) {
        builder.render(gc, r);
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

}
