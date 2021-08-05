package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;

public class Crab extends TierOne {

    public Crab(double posX, double posY, BuildSquare square) {
        super("crab", "/images/Towers/crab/", posX, posY, 70, 4, 15*5, 1.8, square, 20);
    }

}
