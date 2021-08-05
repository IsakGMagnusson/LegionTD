package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import game.display.popup.GoldPop;
import game.display.popup.Toast;
import game.gameplay.GamePlay;
import game.gameplay.builder.BuildSquare;
import game.unit.King;
import game.unit.Unit;
import game.unit.enemy.Enemy;
import game.unit.enemy.EnemySpawn;
import game.unit.misc.Projectile;
import game.unit.tower.Tower;

import java.util.ArrayList;

public class GameManager extends AbstractGame {

    public static final int SCREEN_WIDTH =1420;
    public static final int SCREEN_HEIGHT =880;


    private ArrayList<ArrayList> objects = new ArrayList<>();
    private ArrayList<Unit> selectAble = new ArrayList<>();


    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<BuildSquare> buildSquares = new ArrayList<>();
    private ArrayList<EnemySpawn> enemySpawns = new ArrayList<>();
    private ArrayList<Toast> toasts = new ArrayList<>();
    private ArrayList<GoldPop> goldPops = new ArrayList<>();
    private ArrayList<King> kings = new ArrayList<>();

    ArrayList<GameObject> toRemove = new ArrayList();

    GamePlay gamePlay = new GamePlay();

    public GameManager(){

    }

    @Override
    public void init(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, float dt) {
        gamePlay.update(gc, this, dt);

        objects.clear();
        objects.add(buildSquares);
        objects.add(enemySpawns);
        objects.add(towers);
        objects.add(enemies);
        objects.add(projectiles);
        objects.add(kings);
        objects.add(goldPops);
        objects.add(toasts);

        selectAble.clear();
        selectAble.addAll(towers);
        selectAble.addAll(enemies);
        selectAble.addAll(kings);

        toRemove.clear();
        for(ArrayList<GameObject> arr : objects) {
            for(GameObject obj : arr) {
                obj.update(gc, this, dt);
                if(obj.isDead()){
                    toRemove.add(obj);
                }
            }
        }
        objects.forEach((arr) -> arr.removeIf((obj) -> toRemove.contains(obj)));
    }


    @Override
    public void render(GameContainer gc, Renderer r) {
        for(ArrayList<GameObject> arr : objects) {
            for (GameObject obj : arr) {
                obj.render(gc, r);
            }
        }
        gamePlay.render(gc, r);
    }

    public void addObject(ArrayList object){
        objects.add(object);
    }

    public ArrayList<ArrayList> getObjects(){
        return objects;
    }

    public ArrayList<Unit> getSelectAble(){
        return selectAble;
    }


    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void addTower(Tower tower){
        towers.add(tower);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void addEnemy(Enemy enemy){
        enemies.add(enemy);
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public ArrayList<BuildSquare> getBuildSquares() {
        return buildSquares;
    }

    public void addBuildSquares(BuildSquare buildSquare){
        buildSquares.add(buildSquare);
    }


    public void addEnemySpawn(EnemySpawn enemySpawn){
        enemySpawns.add(enemySpawn);
    }

    public void addToast(Toast toast){
        toasts.add(toast);
    }

    public void addGoldPop(GoldPop goldPop){
        goldPops.add(goldPop);
    }

    public void addKing(King king){
        kings.add(king);
    }

    public ArrayList<King> getKings() {
        return kings;
    }


    public static void main(String args[]){
        GameContainer gc = new GameContainer(new GameManager());
        gc.setWidth(SCREEN_WIDTH);
        gc.setHeight(SCREEN_HEIGHT);
        gc.start();
    }
}
