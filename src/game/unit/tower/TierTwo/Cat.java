package game.unit.tower.TierTwo;

import game.gameplay.builder.BuildSquare;

public class Cat extends TierTwo {

    public Cat(double posX, double posY, BuildSquare square) {
        super("Cat" , "/images/Towers/cat/", posX, posY, 35, 7, 15*5, 1.0, square, 35);
    }

}