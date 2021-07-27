package game.unit.tower.TierTwo;

import game.gameplay.builder.BuildSquare;
import game.unit.tower.Tower;

public abstract class TierTwo extends Tower {
    public TierTwo(String name, String path, double posX, double posY, double maxHealth, double damage, double range, double attackSpeed, BuildSquare square, int cost) {
        super(name, path, posX, posY, maxHealth, damage, range, attackSpeed, square, cost);
    }

    @Override
    public int getTier(){
        return 2;
    }
}