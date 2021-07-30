package game.unit;

import game.GameObject;

public abstract class Unit extends GameObject {
    protected int view = 80*15;
    protected int friendView = view*2;

    protected double health, maxHealth, percentHealth;
    protected double damage, range, attackSpeed;

    protected boolean hasDetected = false;

    public double getpercentHealth() {
        return percentHealth;
    }
    public void setpercentHealth(double percentHealth) {
        this.percentHealth = percentHealth;
    }

    public double getDamage() {
        return damage;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        this.health = health;
    }

    public double getMaxHealth() {
        return maxHealth;
    }
    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public double getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
    }

    public double getDistTo(GameObject unitToAttack){
        return Math.sqrt(Math.pow(posX - unitToAttack.getPosX(), 2) + Math.pow(posY - unitToAttack.getPosY(), 2));
    }
}
