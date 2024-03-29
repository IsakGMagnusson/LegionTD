package game.unit.enemy;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.ImageTile;
import game.GameManager;
import game.GameObject;
import game.display.popup.GoldPop;
import game.unit.misc.Healthbar;
import game.unit.King;
import game.unit.misc.Projectile;
import game.unit.Unit;
import game.unit.tower.Tower;

public class Enemy extends Unit {

    protected ImageTile twrImg = new ImageTile("/images/testanim.png", 16, 16);

    protected int direction = 0;
    protected float animation = 0;
    protected double nextAttack = 0;

    protected int gold;

    public static int PLAYER_SIZE = 16*2;
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
        r.drawImageTile(twrImg, (int)Math.floor(posX), (int)Math.floor(posY), (int)animation, direction, 2, 180);
        healthbar.render(gc, r);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(health <= 0){
            gm.addGoldPop(new GoldPop( (int)Math.floor(posX), (int)Math.floor(posY), gold));
            setDead(true);
        }

        nextAttack += dt;

        animation += dt*10;
        if(animation > 4) animation = 0;

        if(detectUnit(gm)){
            if(getDistTo(unitToAttack) <= range){
                if(attackSpeed <= nextAttack){
                    attack(unitToAttack);
                    gm.addProjectile(new Projectile(unitToAttack, damage, posX+(width/2), posY, 5, 5));
                }
            } else{
                moveTowardsEnemy(unitToAttack);
            }
        }else{
            movement();
        }

        healthbar.update(gc, gm, dt);
    }

    private boolean detectUnit(GameManager gm){
        //if enemy detected
        for(Tower tower : gm.getTowers()){
            if(getDistTo(tower) <= view) {
                hasDetected = true;
                unitToAttack = tower;
                return true;
            }
        }

        for(King king : gm.getKings()){
            if(getDistTo(king) <= view) {
                hasDetected = true;
                unitToAttack = king;
                return true;
            }
        }


        //if moving friend detected enemy
        for(Enemy friendlyEnemy: gm.getEnemies()) {
            if (getDistTo(friendlyEnemy) <= friendView && (friendlyEnemy).hasDetected) {
                moveTowardsEnemy(friendlyEnemy);
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
