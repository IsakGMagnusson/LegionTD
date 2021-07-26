package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;
import game.unit.tower.Tower;

public class Crab extends Tower {

    public Crab(double posX, double posY, BuildSquare square) {
        super("crab", "/images/crab/", posX, posY, 20, 4, 15*5, 0.8, square, 20);
    }

}
