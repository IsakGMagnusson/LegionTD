package game.display.HUD.BotHud;
import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.King;
import game.unit.enemy.Enemy;
import game.unit.tower.Tower;


public class BotHud extends GameObject {

    private int botHUDheight = 50;

    public enum SelectedObj{
        NULL,
        TOWER,
        ENEMY,
        KING
    }
    public static SelectedObj selectedEnum = SelectedObj.NULL;
    public static Object selectedObj;

    /*
    the reason behind the use of this boolean is to avoid
    having to create selectHUD().update()/render()
    and instead use update on <BotHud selectedHud>
    to avoid creating new Hud every iteration
     */
    public static boolean newHudSelected = false;
    private BotHud selectedHud;

    public BotHud(){
        this.posX = 0;
        this.posY = GameManager.SCREEN_HEIGHT-botHUDheight;
        this.width = GameManager.SCREEN_WIDTH;
        this.height = botHUDheight;
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(newHudSelected){
            updateEnum();
            selectedHud = selectHUD();
            newHudSelected = false;
        }

        if(selectedEnum != SelectedObj.NULL) selectedHud.update(gc,gm,dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, 0xFFFFFFFF);

        if(selectedEnum != SelectedObj.NULL)selectedHud.render(gc, r);
        else r.drawText("Nothing selected", (int)getPosX(), (int)getPosY(), 0xFF000000, 3);

    }

    private void updateEnum(){
        if(selectedObj == null)          selectedEnum = SelectedObj.NULL;
        if(selectedObj instanceof Tower) selectedEnum = SelectedObj.TOWER;
        if(selectedObj instanceof Enemy) selectedEnum = SelectedObj.ENEMY;
        if(selectedObj instanceof King)  selectedEnum = SelectedObj.KING;
    }

    public static void selectedNewObject(Object object){
        selectedObj = object;
    }

    private BotHud selectHUD(){
        switch (selectedEnum) {
            case ENEMY:
                return new EnemyHud((Enemy) selectedObj);

            case TOWER:
                return new TowerHud((Tower) selectedObj);

            default:
                return new NullHud();
        }
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
