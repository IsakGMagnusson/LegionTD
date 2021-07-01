package game.unit.tower;

import game.gameplay.builder.BuildSquare;

public class TowerFactory {

    public Tower getTier1(Integer id, int x, int y, BuildSquare square) {
        switch (id) {
            case 0:
                return new TowerTier1(x,y,square);
            case 1:
                return new TowerTier1(x,y,square);
            default:
                return null;
        }
    }

    public Tower getTier2(Integer id, int x, int y, BuildSquare square){
        if(id == null){
            return null;
        }
        if(id.equals(0)){
            return new TowerTier2(x,y,square);
        }

        return null;
    }
}
