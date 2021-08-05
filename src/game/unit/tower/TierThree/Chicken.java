package game.unit.tower.TierThree;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.gameplay.builder.BuildSquare;
import game.unit.ability.Ability;
import game.unit.ability.DeathAndDecay;

public class Chicken extends TierThree {

    public Chicken(double posX, double posY, BuildSquare square) {
        super("Chicken", "/images/Towers/chicken/", posX, posY, 45, 12, 15*5, 0.6, square, 55);
        unitAbilities = new Ability[]{new DeathAndDecay(this)};
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        super.update(gc, gm ,dt);
        for(Ability ability : unitAbilities){
            ability.update(gc,gm,dt);
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        super.render(gc, r);
        for(Ability ability : unitAbilities){
            ability.render(gc, r);
        }
    }
}
