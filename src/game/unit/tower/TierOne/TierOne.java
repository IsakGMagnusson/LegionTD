package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;
import game.unit.tower.Tower;

public abstract class TierOne extends Tower {
    public TierOne(String name, String path, double posX, double posY, double maxHealth, double damage, double range, double attackSpeed, BuildSquare square, int cost) {
        super(name, path, posX, posY, maxHealth, damage, range, attackSpeed, square, cost);
    }

    @Override
    public int getTier(){
        return 1;
    }
}
