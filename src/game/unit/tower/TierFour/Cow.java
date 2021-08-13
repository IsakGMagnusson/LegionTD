package game.unit.tower.TierFour;

import game.gameplay.builder.BuildSquare;

public class Cow extends TierFour {

    public Cow(double posX, double posY, BuildSquare square) {
        super("dancing cow", "/images/Towers/cow/", posX, posY, 2000, 1000, 15*5, 4, square, 80);
    }

}
