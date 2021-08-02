package game.display.HUD.BotHud;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.display.Buttons.Button;
import game.display.popup.Toast;
import game.gameplay.GamePlay;
import game.unit.tower.Tower;

public class TowerHud extends BotHud {

    private String info;
    private Tower tower;
    private static Boolean isSelling = false;
    private static Button sellTower;

    public TowerHud(Tower tower) {
        this.tower = tower;

        sellTower = new Button(getPosX()+5, getPosY()+5, new Image("/images/sell.png", 1));
        info = "Name: " + tower.getName() + " hp: " + (int)tower.getHealth() + " Dmg: " + (int)tower.getDamage();
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        setSellingOnOff(GamePlay.getState());
        sellTower.update(gc, gm, dt);

        info = "Name: " + tower.getName() + " hp: " + (int)tower.getHealth() + " Dmg: " + (int)tower.getDamage();

        if(sellTower.getIsPressed(gc) && sellTower.getIsActive()){
            setIsSelling(true);
        } else if(sellTower.getIsPressed(gc) && !sellTower.getIsActive()){
            gm.addToast(new Toast("Can't sell in combat", false));
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)getPosX(), (int)getPosY(), getWidth(), getHeight(), 0xFFFFFFFF);
        r.drawText(info, (int) (getPosX()+sellTower.getPosX()+sellTower.getWidth()), (int)getPosY(), 0xff000000, 4);

        sellTower.render(gc, r);
    }

    public static void setIsSelling(boolean sell){
        isSelling = sell;
    }

    private void setSellingOnOff(GamePlay.State state){
        if(state.equals(GamePlay.State.BUYTIME)) sellTower.setActive(true);
        else sellTower.setActive(false);
    }

    public static boolean getSell() {
        return isSelling;
    }
}
