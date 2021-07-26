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
    public static final int PLAYER_SIZE = 32;
    protected double nextAttack = 0;
    protected Unit unitToAttack;


    protected String name;
    protected String path;
    protected int cost;


    protected Healthbar healthbar = new Healthbar(this);
    protected BuildSquare square;

    private enum AnimationAction{
        IDLE,
        WALKING
    }

    protected float animation = 0;
    private int animationAction;

    public Tower(String name, String path, double posX, double posY, double maxHealth, double damage, double range, double attackSpeed, BuildSquare square, int cost){
        this.name = name;
        this.path = path;
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

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawImageTile(getAnimationTile(), (int)Math.floor(posX), (int)Math.floor(posY), (int)animation, animationAction,1, rotation);
        healthbar.render(gc, r);
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        if(health <= 0) setDead(true);

        nextAttack += dt;
        animation += dt*5;

        if(animation > 4) animation = 0;

        if(detectUnit(gm)){
            if(getDistTo(unitToAttack) <= range){
                if(attackSpeed <= nextAttack){
                    attack(gm);
                }
            }  else{
                moveTowardsUnit(unitToAttack);
            }
        } else{
            animationAction = setAnimationAction(AnimationAction.IDLE);
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

    private void attack(GameManager gm){
        gm.getObjects().add(new Projectile(unitToAttack, damage, posX+(width/2), posY, 5, 5, 0xff00FFFF));
        nextAttack = 0;
    }

    private void moveTowardsUnit(GameObject objMoveTo){

        if(rotateTower(45)){
            animationAction = setAnimationAction(AnimationAction.WALKING);
            moveTowardsUnit(objMoveTo, 0.9);
        }
    }

    //todo fix rotation
    private boolean rotateTower(double angle){
        if(rotation == (int)angle){
            return true;
        } else if(rotation > angle){
                rotation--;
        } else if(rotation < angle){
                rotation++;
        }

        return false;
    }

    public void resetTower(){
        hasDetected = false;
        setPosX(getSquare().getPosX());
        setPosY(getSquare().getPosY());
        setHealth(getMaxHealth());
        setDead(false);
        setpercentHealth((getHealth()/getMaxHealth()) * 100);
    }

    public BuildSquare getSquare(){
        return square;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getPath() {
        return path;
    }

    public ImageTile getAnimationTile() {
        return new ImageTile(path+"animations.png", 32, 32);
    }

    public void setSquare(BuildSquare buildSquare){
        square = buildSquare;
    }

    private int setAnimationAction(AnimationAction animationAction){
        switch(animationAction) {
            case IDLE:
                return 0;
            case WALKING:
                return 1;
            default:
                return 0;
        }
    }


}
