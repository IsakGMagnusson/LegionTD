package game.gameplay;


import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.gameplay.builder.Builder;
import game.unit.tower.Tower;
import game.unit.tower.TowerFactory;

import java.util.ArrayList;

public class Player {

    private Builder builder;

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
