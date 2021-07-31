package game.unit.tower.TierThree;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.gameplay.builder.BuildSquare;
import game.unit.Ability;

public class Chicken extends TierThree {
    Ability ability = new Ability(this);

    public Chicken(double posX, double posY, BuildSquare square) {
        super("Chicken", "/images/Towers/chicken/", posX, posY, 25, 0, 15*5, 0.3, square, 55);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        super.update(gc, gm ,dt);
        ability.update(gc,gm,dt);
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        super.render(gc, r);
        ability.render(gc, r);
    }
}
