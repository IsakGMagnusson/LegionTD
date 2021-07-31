package game.display.HUD.BotHud;
import engine.GameContainer;
import engine.Renderer;
import game.GameManager;

public class NullHud extends BotHud {

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawText("Nothing selected", (int)getPosX(), (int)getPosY(), 0xFF000000, 3);
    }
}
