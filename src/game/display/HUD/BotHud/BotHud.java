package game.display.HUD.BotHud;
import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.King;
import game.unit.enemy.Enemy;
import game.unit.tower.Tower;


public class BotHud extends GameObject {

    protected int botHUDheight = 80;

    public enum SelectedObj{
        TOWER,
        ENEMY,
        KING
    }
    public static SelectedObj selectedEnum = SelectedObj.KING;
    public static Object selectedObj;

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
        selectedHud.update(gc,gm,dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        selectedHud.render(gc, r);
    }

    private void updateEnum(){
        if(selectedObj instanceof Tower) selectedEnum = SelectedObj.TOWER;
        if(selectedObj instanceof Enemy) selectedEnum = SelectedObj.ENEMY;
        if(selectedObj instanceof King)  selectedEnum = SelectedObj.KING;
    }

    public static void selectedNewObject(Object object){
        selectedObj = object;
        BotHud.newHudSelected = true;
    }

    private BotHud selectHUD(){
        switch (selectedEnum) {
            case ENEMY:
                return new EnemyHud((Enemy) selectedObj);

            case TOWER:
                return new TowerHud((Tower) selectedObj);

            case KING:
                return new KingHud();

            default:
                return null;
        }
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }
}
