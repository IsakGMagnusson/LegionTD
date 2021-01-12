package game.unit.enemy.Waves;
import game.unit.enemy.Enemy;

public class RegularWaves extends Wave{

    private RegularWaves(int gold, Enemy[] units) {
        super(gold, units);
    }

    protected static final RegularWaves wave1 = new RegularWaves (20,
            new Enemy[]{new Enemy(150,-20, 20, 150, 90, 4),
                    new Enemy(100,-20, 20, 150, 90, 4)});


    protected static final RegularWaves wave2 = new RegularWaves (40,
            new Enemy[]{new Enemy(170,-20, 20, 3, 90, 3),
                new Enemy(120,-20, 20, 3, 90, 3)});


    protected static final RegularWaves wave3 = new RegularWaves (60,
            new Enemy[]{new Enemy(170,-20, 20, 3, 90, 3),
                    new Enemy(120,-20, 20, 3, 90, 3)});
}
