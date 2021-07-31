package game.unit.tower.TierThree;

import game.gameplay.builder.BuildSquare;

public class Chicken extends TierThree {
    public Chicken(double posX, double posY, BuildSquare square) {
        super("Chicken", "/images/Towers/chicken/", posX, posY, 25, 12, 15*5, 0.3, square, 55);
    }
}
