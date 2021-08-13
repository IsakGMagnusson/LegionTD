package game.unit.tower;

import game.gameplay.builder.BuildSquare;
import game.unit.tower.TierFour.Cow;
import game.unit.tower.TierThree.Chicken;
import game.unit.tower.TierTwo.Cat;
import game.unit.tower.TierOne.Crab;

public class TowerFactory {

    public Tower getTier1(Integer id, int x, int y, BuildSquare square) {
        switch (id) {
            case 0:
                return new Crab(x,y,square);
            case 1:
                return new Crab(x,y,square);
            default:
                return null;
        }
    }

    public Tower getTier2(Integer id, int x, int y, BuildSquare square) {
        switch (id) {
            case 0:
                return new Cat(x, y, square);
            case 1:
                return new Cat(x, y, square);
            default:
                return null;
        }
    }

    public Tower getTier3(Integer id, int x, int y, BuildSquare square) {
        switch (id) {
            case 0:
                return new Chicken(x, y, square);
            case 1:
                return new Chicken(x, y, square);
            default:
                return null;
        }
    }

    public Tower getTier4(Integer id, int x, int y, BuildSquare square) {
        switch (id) {
            case 0:
                return new Cow(x, y, square);
            case 1:
                return new Cow(x, y, square);
            default:
                return null;
        }
    }
}

