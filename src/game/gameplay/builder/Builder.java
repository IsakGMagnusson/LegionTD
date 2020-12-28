package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.HUD.BotHud;
import game.HUD.RightHud;
import game.gameplay.GamePlay;
import game.gameplay.Player;
import game.unit.tower.Tower;

public class Builder {
    private BuildArea buildArea = new BuildArea();
    private Player player;
    private GamePlay gamePlay;

    public Builder(Player player, GamePlay gamePlay){
        this.player = player;
        this.gamePlay = gamePlay;
    }

    public void update(GameContainer gc, GameManager gm) {
        if (gc.getInput().isButtonDown(1) && RightHud.buying > -1 && buildArea.isSquareFree(gc) && gamePlay.isBuyState())
            buildTower(gc, gm);

        if (gc.getInput().isButtonDown(3))
            unSelectTower();

        if(BotHud.getSell())
            sellUnit();
    }

    public void render(GameContainer gc, Renderer r) {
        buildArea.render(gc, r);

        if(RightHud.buying > -1)
            r.drawImageTile(player.getTowers()[RightHud.buying].getImg(), gc.getInput().getMouseX(),  gc.getInput().getMouseY(), 0, 0);
    }

    private void buildTower(GameContainer gc, GameManager gm){
        Tower t = createTower();
        if(t.getCost() <= player.getGold()){
            t.setSquare(buildArea.getHooveredSquare(gc));
            t.setPosX(t.getSquare().getPosX());
            t.setPosY(t.getSquare().getPosY());
            gm.addObject(t);
            player.addOwnedTower(t);
            player.decGold(t.getCost());
            buildArea.getHooveredSquare(gc).setIsOccupied(true);
        } else{
            System.out.println("no money");
        }

    }

    private Tower createTower() {
        switch (RightHud.buying) {
            case 0:
                return player.createTier1();
            case 1:
                return player.createTier2();
            default:
                return null;
        }
    }

    private void unSelectTower(){
        RightHud.buying = -1;
    }

    private void sellUnit(){
          player.incGold(((Tower) player.getSelectedObject()).getCost()/2);
          ((Tower) player.getSelectedObject()).setSold(true);
          player.getSelectedObject().setDead(true);
          BotHud.setIsSelling(false);
          ((Tower) player.getSelectedObject()).getSquare().setIsOccupied(false);
          player.setSelectedObject(null);
    }
}
