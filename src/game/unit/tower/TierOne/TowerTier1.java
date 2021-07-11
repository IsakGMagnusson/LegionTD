package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;
import game.unit.tower.Tower;

public class TowerTier1 extends Tower {

    public TowerTier1(double posX, double posY, BuildSquare square) {
        super(55, "/images/testTOne.png", posX, posY, 20, 4, 15*5, 0.3, square, 20);
    }

}
