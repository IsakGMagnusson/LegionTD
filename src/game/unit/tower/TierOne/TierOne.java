package game.unit.tower.TierOne;

import game.gameplay.builder.BuildSquare;
import game.unit.tower.Tower;

public abstract class TierOne extends Tower {
    public TierOne(int id, String path, double posX, double posY, double maxHealth, double damage, double range, double attackSpeed, BuildSquare square, int cost) {
        super(id, path, posX, posY, maxHealth, damage, range, attackSpeed, square, cost);
    }


}
