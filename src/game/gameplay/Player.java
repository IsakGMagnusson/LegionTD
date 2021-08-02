package game.gameplay;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.display.HUD.BotHud.BotHud;
import game.display.HUD.BotHud.TowerHud;
import game.display.HUD.RightHud.RightHud;
import game.gameplay.builder.Builder;
import game.unit.King;
import game.unit.Unit;
import game.unit.tower.Tower;

import java.util.ArrayList;

public class Player {

    private Builder builder;
    private King king;
    boolean isKingCreated = false;
    private GameObject selectedObject;
    private final int unitSelectColor = 0xFF49ffa0;

    private RightHud rightHud;
    private BotHud botHud;

    private int randT1 = 0;
    private int randT2 = 0;
    private int randT3 = 0;

    private int gold;
    private ArrayList<Tower> ownedTowers = new ArrayList();

    public Player(GamePlay gamePlay){
        this.gold = 100;
        builder = new Builder(this, gamePlay, randT1, randT2, randT3);
        king = new King();
        rightHud = new RightHud();
        botHud = new BotHud();

    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        builder.update(gc, gm);
        king.update(gc, gm, dt);
        rightHud.update(gc, gm, dt);
        botHud.update(gc, gm, dt);

        if(!isKingCreated){
            createKing(gm);
        }

        if(gc.getInput().isButtonDown(1)) selectedObject = selectUnit(gm, gc);
    }

    public void render(GameContainer gc, Renderer r) {
        builder.render(gc, r);
       // king.render(gc, r);
        rightHud.render(gc, r);
        botHud.render(gc, r);

        if(selectedObject != null && !selectedObject.isDead())
            r.drawCircle((int)Math.floor(selectedObject.getPosX()+selectedObject.getWidth()/2+5),
                    (int)Math.floor(selectedObject.getPosY())+selectedObject.getWidth()/2+3, selectedObject.getWidth(), unitSelectColor,4);
    }

    public GameObject selectUnit(GameManager gm, GameContainer gc){
        for(Unit unit : gm.getSelectAble()){
            if (unit.isHoovered(gc)){
                TowerHud.setIsSelling(false);
                BotHud.selectedNewObject(unit);
                return unit;
            }
        }
        return selectedObject;
    }

    public int getGold() {
        return gold;
    }

    public Builder getBuilder() {
        return builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
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

    private void createKing(GameManager gm){
        gm.addKing(king);
        isKingCreated = true;
    }
}
