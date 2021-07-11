package game.unit.tower;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.ImageTile;
import game.GameManager;
import game.GameObject;
import game.gameplay.builder.BuildSquare;
import game.unit.Healthbar;
import game.unit.Projectile;
import game.unit.Unit;
import game.unit.enemy.Enemy;

public abstract class Tower extends Unit {

    protected ImageTile imgTile;

    protected int animationType = 0;
    protected float animation = 0;
    protected double nextAttack = 0;

    public static final int PLAYER_SIZE = 16;
    protected Unit unitToAttack;
    protected static double SPEED = 0.5;
    protected Healthbar healthbar = new Healthbar(this);
    protected int id;
    protected int cost;
    protected BuildSquare square;


    public Tower(int id, String path, double posX, double posY, double maxHealth, double damage, double range, double attackSpeed, BuildSquare square, int cost){
        this.id = id;
        this.imgTile = new ImageTile(path, PLAYER_SIZE, PLAYER_SIZE);
        this.posX = posX;
        this.posY = posY;
        this.width = PLAYER_SIZE;
        this.height = PLAYER_SIZE;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.percentHealth = (health/maxHealth) * 100;
        this.damage = damage;
        this.range = range+PLAYER_SIZE;
        this.attackSpeed = attackSpeed;
        this.square = square;
        this.cost = cost;
    }

    int d = 180;


    public ImageTile getImg() {
        return imgTile;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {


        r.drawImageTile(imgTile, (int)Math.floor(posX), (int)Math.floor(posY), 0, animationType,2, d);
        healthbar.render(gc, r);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(gc.getInput().isButtonDown(1)){
            d +=5;
        }


        if(health <= 0) setDead(true);

        nextAttack += dt;
        animation += dt*5;

        if(animation > 4) animation = 0;


        if(detectUnit(gm)){
            if(getDistTo(unitToAttack) <= range){
                if(attackSpeed <= nextAttack){
                    attack(unitToAttack);
                    gm.getObjects().add(new Projectile(unitToAttack, posX+(width/2), posY, 5, 5, 0xff00FFFF));
                }
            }  else{
                moveTowardsUnit(unitToAttack);
            }
        }
        healthbar.update(gc, gm, dt);
    }

    private boolean detectUnit(GameManager gm){
        //if enemy detected
        for(GameObject objDetected : gm.getObjects()){
            if(getDistTo(objDetected) <= view && objDetected instanceof Enemy) {
                hasDetected = true;
                unitToAttack = (Unit) objDetected;
                return true;
            }
        }

        //attack enemy if detected by friend in view
        for(GameObject objDetected : gm.getObjects()) {
            if (objDetected instanceof  Tower && getDistTo(objDetected) <= friendView && ((Tower) objDetected).hasDetected) {
                unitToAttack = ((Tower) objDetected).unitToAttack;
                hasDetected = true;
                return true;
            }
        }

        hasDetected = false;
        unitToAttack = null;
        return false;
    }

    private void attack(Unit objToAttack){
        objToAttack.setHealth(objToAttack.getHealth()-damage);
        objToAttack.setpercentHealth((objToAttack.getHealth()/objToAttack.getMaxHealth()) * 100);
        nextAttack = 0;
    }

    private void moveTowardsUnit(GameObject objMoveTo){
        if(objMoveTo.getPosX() > posX){
            posX += SPEED;
        }
        if(objMoveTo.getPosX() < posX){
            posX -= SPEED;
        }
        if(objMoveTo.getPosY() < posY){
            posY -= SPEED;
        }
        if(objMoveTo.getPosY() > posY){
            posY += SPEED;
        }
    }

    private void rotateTower(GameObject rotateTo){



    }

    public void resetTower(){
        hasDetected = false;
        setPosX(getSquare().getPosX());
        setPosY(getSquare().getPosY());
        setHealth(getMaxHealth());
        setDead(false);
        setpercentHealth((getHealth()/getMaxHealth()) * 100);
    }

    public void setSquare(BuildSquare buildSquare){
        square = buildSquare;
    }

    public BuildSquare getSquare(){
        return square;
    }

    public int getCost() {
        return cost;
    }

}
