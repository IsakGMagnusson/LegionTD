package game.unit.enemy.Waves;

import game.unit.enemy.Enemy;
import java.util.ArrayList;

public abstract class Wave {
    private ArrayList<Enemy> units;
    private int gold;

    public static final Wave[] WAVES = new Wave[]{RegularWaves.wave1, RegularWaves.wave2, RegularWaves.wave3};

    public Wave(int gold, ArrayList<Enemy> units) {
        this.gold = gold;
        this.units = units;
    }

    public ArrayList<Enemy>  getWaveUnits(){
        return units;
    }

    public int getGold() {
        return gold;
    }

    public static boolean areEnemiesDead(Wave wave){
        if (wave.getWaveUnits().isEmpty())
            return true;

        return false;
    }
}
