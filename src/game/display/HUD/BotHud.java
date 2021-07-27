package game.display.HUD;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;
import game.display.Button;
import game.gameplay.GamePlay;


public class BotHud extends GameObject {

    private static String info = "";
    private static Boolean isSelling = false;
    private static Button sellTower;


    public enum SelectedObj{
        NULL,
        TOWER,
        ENEMY
    }
    public static SelectedObj selectedObj = SelectedObj.NULL;

    public BotHud(double posX, double posY, int width, int height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;

        sellTower = new Button(posX+5, posY+5, 32, 32, new Image("/images/sell.png"));
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        setSellingOnOff(GamePlay.getState());
       // selectHUD();

        sellTower.update(gc, gm, dt);

        if(sellTower.getIsPressed()){
            setIsSelling(true);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, 0xFFFFFFFF);
        r.drawText(info, (int) (posX+sellTower.getPosX()+sellTower.getWidth()), (int)posY, 0xff000000, 4);

        sellTower.render(gc, r);
    }


    public static void setInfo(String info) {
        BotHud.info = info;
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

    /*private void selectHUD(){
        switch (selectedObj) {
            case ENEMY:
                enemyHUD();
                break;

            case TOWER:
                towerHUD();
                break;

            default:
                nullHUD();
        }
    }*/

    //TODO: make different HUDS instead of one HUD hiding/showing components
 /*   private void towerHUD(){
        towerInfo.setVisible(true);
        sellTower.setVisible(true);
    }

    private void enemyHUD(){
        sellTower.setVisible(false);
        towerInfo.setVisible(true);
    }

    private void nullHUD(){
        sellTower.setVisible(false);
        towerInfo.setVisible(false);
    }*/
}
