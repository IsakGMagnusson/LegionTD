package game.unit.enemy;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.ImageTile;
import game.GameManager;
import game.GameObject;
import game.unit.Healthbar;
import game.unit.Projectile;
import game.unit.Unit;
import game.unit.tower.Tower;

public class Enemy extends Unit {

    protected ImageTile twrImg = new ImageTile("/images/testanim.png", PLAYER_SIZE, PLAYER_SIZE);

    protected int direction = 0;
    protected float animation = 0;
    protected double nextAttack = 0;

    protected int gold;

    public static int PLAYER_SIZE = 16;
    private Unit unitToAttack;
    private static double SPEED = 0.5;
    private Healthbar healthbar = new Healthbar(this);

    public Enemy(double posX, double posY, int gold, double maxHealth, double damage, double range, double attackSpeed){
        this.posX = posX;
        this.posY = posY;
        this.gold = gold;
        this.width = PLAYER_SIZE;
        this.height = PLAYER_SIZE;
        this.health = maxHealth;
        this.maxHealth = maxHealth;
        this.percentHealth = (health/maxHealth) * 100;
        this.damage = damage;
        this.range = range;
        this.attackSpeed = attackSpeed;
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImageTile(twrImg, (int)Math.floor(posX), (int)Math.floor(posY), (int)animation, direction);
        healthbar.render(gc, r);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(health <= 0 ) setDead(true);

        nextAttack += dt;

        animation += dt*10;
        if(animation > 4) animation = 0;

        if(detectUnit(gm)){
            if(getDistTo(unitToAttack) <= range){
                if(attackSpeed <= nextAttack){
                    attack(unitToAttack);
                    gm.getObjects().add(new Projectile(unitToAttack, posX+(width/2), posY, 1, 1, 0xffFF0000));
                }
            }
        }else{
            movement();
        }

        healthbar.update(gc, gm, dt);
    }

    private boolean detectUnit(GameManager gm){

        //if enemy detected
        for(GameObject objDetected : gm.getObjects()){
            if(getDistTo(objDetected) <= view && objDetected instanceof Tower) {
                hasDetected = true;
                unitToAttack = (Unit) objDetected;
                return true;
            }
        }

        //if moving friend detected enemy
        for(GameObject objDetected : gm.getObjects()) {
            if (objDetected instanceof  Enemy && getDistTo(objDetected) <= friendView && ((Enemy) objDetected).hasDetected) {
                moveTowardsEnemy(objDetected);
                return false;
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

    private void moveTowardsEnemy(GameObject objToAttack){
        if(objToAttack.getPosX() > posX){
            direction = 0;
            posX += SPEED;
        }
        if(objToAttack.getPosX() < posX){
            direction = 1;
            posX -= SPEED;
        }
        if(objToAttack.getPosY() < posY){
            direction = 2;
            posY -= SPEED;
        }
        if(objToAttack.getPosY() > posY){
            direction = 3;
            posY += SPEED;
        }
    }

    private void movement(){
        direction = 3;
        posY += SPEED;
    }

    public int getGold() {
        return gold;
    }

}
