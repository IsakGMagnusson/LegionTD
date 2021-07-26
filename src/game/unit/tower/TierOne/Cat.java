package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;

public class Cat extends TierOne {

    public Cat(double posX, double posY, BuildSquare square) {
        super("Cat" , "/images/cat/", posX, posY, 20, 4, 15*5, 0.8, square, 20);
    }

}