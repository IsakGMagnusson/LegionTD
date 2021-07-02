package game.unit.tower;

import game.gameplay.builder.BuildSquare;

public class TowerTier1 extends Tower {

    public TowerTier1(double posX, double posY, BuildSquare square) {
        super(55, "/images/testTOne.png", posX, posY, 20, 4, 15, 0.3, square, 20);
    }

}
