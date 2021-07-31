package game.unit.tower.TierThree;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.gameplay.builder.BuildSquare;
import game.unit.tower.Tower;

public class TierThree extends Tower {

    public TierThree(String name, String path, double posX, double posY, double maxHealth, double damage, double range, double attackSpeed, BuildSquare square, int cost) {
        super(name, path, posX, posY, maxHealth, damage, range, attackSpeed, square, cost);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        super.update(gc, gm ,dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        super.render(gc, r);
    }

    @Override
    public int getTier(){
        return 3;
    }

}
