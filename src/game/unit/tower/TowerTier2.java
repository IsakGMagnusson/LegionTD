package game.unit.tower;

import game.gameplay.builder.BuildSquare;

public class TowerTier2 extends Tower {

    public TowerTier2(double posX, double posY, BuildSquare square) {
        super(0,"/images/testTwo.png", posX, posY, 300, 50, 30*3, 1, square, 50);
    }
}
