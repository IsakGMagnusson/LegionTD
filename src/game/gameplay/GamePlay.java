package game.gameplay;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.HUD.TopHud;
import game.unit.enemy.Enemy;
import game.unit.enemy.Waves.Wave;
import game.unit.tower.Tower;


public class GamePlay{

    public enum State{
        BATTLE,
        BUYTIME
    }

    private Player player;
    private State state = State.BUYTIME;
    private double buildTime = 5;
    private int waveCount = 1;
    private double timeLeft = buildTime;
    private Wave currentWave;

    public GamePlay(){
        player = new Player(this);
    }

    public void update(GameContainer gc, GameManager gm, float dt) {
        timeLeft -= dt;
        TopHud.setInfo("Time: " + (int)timeLeft + " |  State: " + state + "  |  wave: " + waveCount + "  |  Gold: " + player.getGold());
        player.update(gc, gm);

        System.out.println(player.getOwnedTowers().size());

        if(timeLeft <= 0 && state == State.BUYTIME) startWave(gm);
        if(state == State.BATTLE && !Wave.areEnemiesDead(gm)) endWave(gm);
    }

    private void endWave(GameManager gm){
        state = State.BUYTIME;
        timeLeft = buildTime;
        player.incGold(currentWave.getGold());
        waveCount++;

        for (GameObject tower : gm.getObjects())
            if (tower instanceof Tower)
                tower.setDead(true);

        for(Tower t : player.getOwnedTowers()){
            gm.addObject(t);
            t.resetTower();
        }
    }

    private void startWave(GameManager gm){
        state = State.BATTLE;
        currentWave = Wave.WAVES[waveCount-1];
        for(Enemy enemy : currentWave.getWaveUnits())
            gm.getObjects().add(enemy);
    }

    public void render(GameContainer gc, Renderer r) {
        player.render(gc, r);
    }

    public boolean isBuyState(){
        if(state == State.BUYTIME) return true;
        else return false;
    }
}
