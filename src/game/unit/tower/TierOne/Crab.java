package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;

public class Crab extends TierOne {

    public Crab(double posX, double posY, BuildSquare square) {
        super("Crab", "/images/crab/", posX, posY, 20, 4, 15*5, 0.8, square, 20);
    }

}
