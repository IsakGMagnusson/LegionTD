package game.unit.enemy.Waves;
import game.unit.enemy.Enemy;

import java.util.ArrayList;
import java.util.Arrays;

public class RegularWaves extends Wave{

    private RegularWaves(int gold, ArrayList<Enemy> units) {
        super(gold, units);
    }

    protected static final RegularWaves wave1 = new RegularWaves (15,
            new ArrayList<>(Arrays.asList(new Enemy(150, 20, 5, 20, 3, 90, 1),
                    new Enemy(100, 20, 5, 20, 3, 90, 1))));


    protected static final RegularWaves wave2 = new RegularWaves (1,
            new ArrayList<>(Arrays.asList(new Enemy(150, 20, 5, 20, 3, 90, 2),
                    new Enemy(100, 20, 5, 20, 3, 90, 2))));


    protected static final RegularWaves wave3 = new RegularWaves (1,
            new ArrayList<>(Arrays.asList(new Enemy(150, 20, 5, 20, 3, 90, 2),
                    new Enemy(100, 20, 5, 20, 3, 90, 2))));

}
