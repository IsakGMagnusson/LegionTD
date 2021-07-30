package game.unit.tower.TierTwo;

import game.gameplay.builder.BuildSquare;

public class Cat extends TierTwo {

    public Cat(double posX, double posY, BuildSquare square) {
        super("Cat" , "/images/cat/", posX, posY, 15, 7, 15*5, 0.5, square, 35);
    }

}