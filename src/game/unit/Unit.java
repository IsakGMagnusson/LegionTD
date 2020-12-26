package game.unit;

import engine.GameContainer;
import game.GameObject;

public abstract class Unit extends GameObject {
    protected int view = 80;
    protected int fView = view*3;


    protected double health, maxHealth, percentHealth;
    protected double damage, range, attackSpeed;

    protected boolean isSelected;
    protected boolean hasDetected = false;


    public double getpercentHealth() {
        return percentHealth;
    }
    public void setpercentHealth(double percentHealth) {
        this.percentHealth = percentHealth;
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


    protected void selectUnit(GameContainer gc){
        if (gc.getInput().isButtonDown(1)){
            if (isHoovered(gc)){
                isSelected = true;
            } else{
                isSelected = false;
            }
        }
    }


    protected double getDistTo(GameObject unitToAttack){
        return Math.sqrt(Math.pow(posX - unitToAttack.getPosX(), 2) + Math.pow(posY - unitToAttack.getPosY(), 2));
    }

}
