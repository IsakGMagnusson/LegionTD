package game.display.HUD.BotHud;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.display.Buttons.Button;
import game.display.popup.Toast;
import game.gameplay.GamePlay;
import game.unit.ability.Ability;
import game.unit.tower.Tower;

public class TowerHud extends BotHud {

    private Tower tower;
    private static Boolean isSelling = false;
    private static Button sellTower;

    public TowerHud(Tower tower) {
        this.tower = tower;
        sellTower = new Button(getPosX()+5, getPosY()+5, new Image("/images/sell.png", 1));
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        setSellingOnOff(GamePlay.getState());
        sellTower.update(gc, gm, dt);

        if(sellTower.getIsPressed(gc) && sellTower.getIsActive()){
            setIsSelling(true);
        } else if(sellTower.getIsPressed(gc) && !sellTower.getIsActive()){
            gm.addToast(new Toast("Can't sell in combat", false));
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, tower.isDead() ? deadColor: color);

        sellTower.render(gc, r);
        statsArea(r, tower);

        if(tower.getUnitAbilities() != null) {
            for(Ability ability : tower.getUnitAbilities()){
                abilityArea (r, ability);
            }
        } else{
            r.drawFillRect(800, (int)posY, 200, height, 0xffadffad);
            r.drawText("no ability", 800,  (int)posY, 0xff000000, 2);
        }
    }

    private void statsArea(Renderer r, Tower tower){
        int infoWidth = 200;

        r.drawFillRect(width/2-infoWidth, (int)posY, infoWidth, height, 0xffadffad);
        r.drawText(tower.getName(), width/2-infoWidth/2-(tower.getName().length()*5),  (int)posY, 0xff000000, 2);
        r.drawText("Health: " + (int)tower.getHealth(), width/2-infoWidth,  (int)posY+20, 0xff000000, 2);
        r.drawText("Damage: " + (int)tower.getDamage(), width/2-infoWidth, (int)posY+40, 0xff000000, 2);

    }

    private void abilityArea(Renderer r, Ability ability){
        int infoWidth = 200;
        r.drawFillRect(800, (int)posY, infoWidth, height, 0xffadffad);
        r.drawText(ability.getName(), 800,  (int)posY, 0xff000000, 2);
        r.drawRect(800, (int)posY, 200, 20, 0xff000000);
    }

    public static void setIsSelling(boolean sell){
        isSelling = sell;
    }

    private void setSellingOnOff(GamePlay.State state){
        sellTower.setActive(state.equals(GamePlay.State.BUYTIME));
    }

    public static boolean getSell() {
        return isSelling;
    }

}
