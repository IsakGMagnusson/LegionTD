package game.unit.tower;

public class TowerFactory {

    public Tower getTier1(Integer id) {
        switch (id) {
            case 0:
                return new TowerTier1(0,0,null);
            case 1:
                return new TowerTier1(0,0, null);
            default:
                return null;
        }
    }

    public Tower getTier2(Integer id){
        if(id == null){
            return null;
        }
        if(id.equals(0)){
            return new TowerTier2(0,0, null);
        }

        return null;
    }

}
