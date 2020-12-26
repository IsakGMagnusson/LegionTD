package game.unit.enemy.Waves;

import game.GameManager;
import game.GameObject;
import game.unit.enemy.Enemy;

public abstract class Wave {
    private Enemy[] units;
    private int gold;

    public static final Wave[] WAVES = new Wave[]{RegularWaves.wave1, RegularWaves.wave2, RegularWaves.wave3};

    public Wave(int gold, Enemy[] units) {
        this.gold = gold;
        this.units = units;
    }

    public Enemy[] getWaveUnits(){
        return units;
    }

    public int getGold() {
        return gold;
    }

    public static boolean areEnemiesDead(GameManager gm){
        for (GameObject enemy : gm.getObjects())
            if (enemy instanceof Enemy)
                return true;

        return false;
    }





}
