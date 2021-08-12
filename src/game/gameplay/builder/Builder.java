package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.ImageTile;
import game.GameManager;
import game.display.HUD.BotHud.TowerHud;
import game.display.popup.GoldPop;
import game.display.HUD.RightHud.RightHud;
import game.display.popup.Toast;
import game.gameplay.GamePlay;
import game.gameplay.Player;
import game.unit.tower.Tower;
import game.unit.tower.TowerFactory;

public class Builder {
    private BuildArea buildArea = new BuildArea();
    private TowerFactory towerFactory = new TowerFactory();
    private Player player;
    private GamePlay gamePlay;

    private boolean addBuildArea = false;

    private ImageTile[] imageArray;
    int[] towerIDs;
    
    public Builder(Player player, GamePlay gamePlay, int[] towerIDs){
        this.player = player;
        this.gamePlay = gamePlay;
        this.towerIDs = towerIDs;
        
        //null is for (tier1 = first index), and so on. Maybe fix so (tier1 = tier0)?
        imageArray = new ImageTile[]{null,
                towerFactory.getTier1(towerIDs[0], 0, 0, null).getAnimationTile(),
                towerFactory.getTier2(towerIDs[1], 0, 0, null).getAnimationTile(),
                towerFactory.getTier3(towerIDs[2], 0, 0, null).getAnimationTile()
        };
    }

    public void update(GameContainer gc, GameManager gm) {
        if(!addBuildArea){
            for(BuildSquare buildSquare : buildArea.getBuildArea())
            gm.addBuildSquares(buildSquare);
            addBuildArea = true;
        }


        if(isTryingToBuild(gc)){
            if(!gamePlay.isBuyState()){
                gm.addToast(new Toast("not buystate", false));
            } else if(gamePlay.isBuyState()){
                if (buildArea.isSquareFree(gc)){
                    buildTower(gc, gm);
                } else if(getHooveredTower(gc) != null){
                    gm.addToast(new Toast("square occupied", false));
                    getHooveredTower(gc);
                }
                else{
                    gm.addToast(new Toast("bad terrain", false));
                }
            }
        }

        if(gc.getInput().isButtonDown(3)){
            unSelectTower();
        }

        if(TowerHud.getSell()){
            sellUnit((Tower) player.getSelectedObject(), gm);
        }
    }

    public void render(GameContainer gc, Renderer r) {
        //buildArea.render(gc, r);
        if(RightHud.buying > 0){
            r.drawImageTile(imageArray[RightHud.buying], gc.getInput().getMouseX(),  gc.getInput().getMouseY(), 0, 0, 1, 180);
        }
    }

    private boolean isTryingToBuild(GameContainer gc){
        return gc.getInput().isButtonDown(1) && RightHud.buying > -1;
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
        Tower t = createTower((int)buildSquare.getPosX(), (int)buildSquare.getPosY(), buildSquare);
        if(t.getCost() <= player.getGold()){
            gm.addTower(t);
            player.addOwnedTower(t);
            player.decGold(t.getCost());
            buildArea.getHooveredSquare(gc).setIsOccupied(true);
        } else{
            gm.addToast(new Toast("insufficient funds", false));
        }
    }

    private Tower createTower(int x, int y, BuildSquare square) {
        switch (RightHud.buying) {
            case 1:
                return towerFactory.getTier1(towerIDs[0], x, y, square);
            case 2:
                return towerFactory.getTier2(towerIDs[1], x, y, square);
            case 3:
                return towerFactory.getTier3(towerIDs[2], x, y, square);
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
            TowerHud.setIsSelling(false);
            soldTower.getSquare().setIsOccupied(false);
            gm.addGoldPop(new GoldPop((int)soldTower.getSquare().getPosX(), (int)soldTower.getSquare().getPosY(), soldTower.getCost()/2));
        }
    }
}