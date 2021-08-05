package game.display.HUD;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;

public class TopHud  {

    private int color = 0xFFfda3ff;
    private int topHudHeight = 12;

    private int posX, posY, width, height;
    private int xMargin = 75;

    private static String info;

    public TopHud(){
        this.posX = 0;
        this.posY = 0;
        this.width = GameManager.SCREEN_WIDTH;
        this.height = topHudHeight;

    }

    public void update(GameContainer gc, GameManager gm, float dt) {

    }

    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect(posX, posY, width, height, color);
        r.drawText(info, 0, 0, 0xFF000000, 2);

    }

    public static void setInfo(String info) {
        TopHud.info = info;
    }

}
