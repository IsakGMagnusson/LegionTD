package game.gameplay;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.display.HUD.TopHud;
import game.display.popup.Toast;
import game.unit.enemy.Enemy;
import game.unit.enemy.EnemySpawn;
import game.unit.enemy.Waves.Wave;
import game.unit.tower.Tower;

import java.util.Iterator;


public class GamePlay{

    public enum State{
        BATTLE,
        BUYTIME
    }
    private static State state = State.BUYTIME;

    private Player player;
    private double buildTime = 45;
    private int waveCount = 1;
    private double timeLeft = buildTime;
    private Wave currentWave;

    private EnemySpawn enemySpawn = new EnemySpawn();
    private boolean isEnemySpawnMovable = false;

    private final Camera camera = new Camera();

    public GamePlay(){
        player = new Player(this);
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        if(!isEnemySpawnMovable){
            gm.addEnemySpawn(enemySpawn);
            isEnemySpawnMovable = true;
        }


        timeLeft -= dt;
        TopHud.setInfo("Time: " + (int)timeLeft + "   State: " + state + "    wave: " + waveCount + "    Gold: " + player.getGold());
        player.update(gc, gm, dt);
        camera.moveCamera(gc, gm);

        if(state.equals(State.BATTLE)) duringWave(gm);
        if(timeLeft <= 0 && state.equals(State.BUYTIME)) startWave(gm);
    }

    private void duringWave(GameManager gm){
        timeLeft = 0;

        Iterator<Enemy> waveIterator = currentWave.getWaveUnits().iterator();
        while (waveIterator.hasNext()){
            Enemy e = waveIterator.next();
            if(e.isDead()){
                player.incGold(e.getGold());
                waveIterator.remove();
            }
        }

        if(Wave.areEnemiesDead(currentWave)) endWave(gm);
    }

    private void endWave(GameManager gm){
        state = State.BUYTIME;
        timeLeft = buildTime;
        player.incGold(currentWave.getGold());
        gm.addToast(new Toast("gold from wave: " + currentWave.getGold(), true));
        waveCount++;

        gm.getTowers().clear();
        gm.getTowers().addAll(player.getOwnedTowers());

        gm.getProjectiles().forEach((proj) -> proj.setDead(true));
        player.getOwnedTowers().forEach(Tower::resetTower);
    }

    private void startWave(GameManager gm){
        state = State.BATTLE;
        currentWave = Wave.WAVES[waveCount-1];
        int i = 0;

        for(Enemy e: currentWave.getWaveUnits()){
            e.setPosX(enemySpawn.getPosX()+i);
            e.setPosY(enemySpawn.getPosY());
            i+=52;
        }
        gm.getEnemies().addAll(currentWave.getWaveUnits());
    }

    public void render(GameContainer gc, Renderer r) {
        player.render(gc, r);
       // enemySpawn.render(gc, r);

    }

    public boolean isBuyState(){
        return state == State.BUYTIME;
    }

    public static State getState(){
        return state;
    }


}
