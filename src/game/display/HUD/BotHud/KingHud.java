package game.display.HUD.BotHud;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;

public class KingHud extends BotHud{

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, 0xffffffff);
        r.drawText("king selected", 200,  (int)posY+height/2, 0xff000000, 2);

    }
}
