package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.ImageTile;
import game.GameManager;
import game.display.popup.GoldPop;
import game.display.HUD.BotHud;
import game.display.HUD.RightHud;
import game.display.popup.Toast;
import game.gameplay.GamePlay;
import game.gameplay.Player;
import game.unit.tower.Tower;
import game.unit.tower.TowerFactory;

public class Builder {
    private BuildArea buildArea = new BuildArea();
    private Player player;
    private GamePlay gamePlay;
    private TowerFactory towerFactory = new TowerFactory();
    private int tier1ID;
    private int tier2ID;

    public Builder(Player player, GamePlay gamePlay, int tier1ID, int tier2ID){
        this.player = player;
        this.gamePlay = gamePlay;
        this.tier1ID = tier1ID;
        this.tier2ID = tier2ID;
    }

    public void update(GameContainer gc, GameManager gm) {
        if(isTryingToBuild(gc)){
            if(!gamePlay.isBuyState()){
                Toast toast = new Toast("not buystate", false);
                gm.addObject(toast);
            } else if(gamePlay.isBuyState()){
                if (buildArea.isSquareFree(gc)){
                    buildTower(gc, gm);
                } else if(getHooveredTower(gc) != null){
                    getHooveredTower(gc);
                }
                else{ Toast toast = new Toast("bad terrain", false);
                    gm.addObject(toast);
                }
            }
        }

        if (gc.getInput().isButtonDown(3)){
            unSelectTower();
        }

        if (BotHud.getSell()){
            sellUnit((Tower) player.getSelectedObject(), gm);
        }
    }

    public void render(GameContainer gc, Renderer r) {
        buildArea.render(gc, r);

        if(RightHud.buying > -1){
            r.drawImageTile(getTierImg(RightHud.buying), gc.getInput().getMouseX(),  gc.getInput().getMouseY(), 0, 0, 2, 180);
        }
    }

    private boolean isTryingToBuild(GameContainer gc){
        return gc.getInput().isButtonDown(1) && RightHud.buying > -1;
    }

    private ImageTile getTierImg(int tier){
        switch (tier) {
            case 0:
                return towerFactory.getTier1(tier1ID, 0, 0, null).getImg();
            case 1:
                return towerFactory.getTier2(tier2ID, 0, 0, null).getImg();
            default:
                return null;
        }
    }

    public Tower getHooveredTower(GameContainer gc){
        for(Tower t : player.getOwnedTowers()) {
            if (t.getSquare().equals(buildArea.getHooveredSquare(gc))) {
                return t;
            }
        }
        return null;
    }

    private void buildTower(GameContainer gc, GameManager gm){
        BuildSquare buildSquare = buildArea.getHooveredSquare(gc);
        Tower t = createTower(buildSquare.getPosX(), buildSquare.getPosY(), buildSquare);
        if(t.getCost() <= player.getGold()){
            gm.addObject(t);
            player.addOwnedTower(t);
            player.decGold(t.getCost());
            buildArea.getHooveredSquare(gc).setIsOccupied(true);
        } else{
            Toast toast = new Toast("insufficient funds", false);
            gm.addObject(toast);
        }
    }

    private Tower createTower(int x, int y, BuildSquare square) {
        switch (RightHud.buying) {
            case 0:
                return towerFactory.getTier1(tier1ID, x, y, square);
            case 1:
                return towerFactory.getTier2(tier2ID, x, y, square);
            default:
                return null;
        }
    }

    private void unSelectTower(){
        RightHud.buying = -1;
    }

    private void sellUnit(Tower soldTower, GameManager gm){
        if (!soldTower.isDead()){
            player.incGold(soldTower.getCost()/2);
            player.getOwnedTowers().remove(soldTower);
            soldTower.setDead(true);
            BotHud.setIsSelling(false);
            soldTower.getSquare().setIsOccupied(false);

            GoldPop goldPop = new GoldPop(soldTower.getSquare().getPosX(), soldTower.getSquare().getPosY(), soldTower.getCost()/2);
            gm.addObject(goldPop);
        }
    }
}