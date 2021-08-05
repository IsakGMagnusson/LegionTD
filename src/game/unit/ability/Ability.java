package game.unit.ability;

import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.unit.Unit;

public class Ability {
    protected Unit abilityOwner;
    protected int castRange;
    protected double posX, posY;
    protected double cooldown, timer, damage;
    protected String name;
    protected String description;
    protected Image icon;

    public Ability(Unit unit){
        this.abilityOwner = unit;
        this.posX = unit.getPosX();
        this.posY = unit.getPosY();
    }

    public void update(GameContainer gc, GameManager gm, float dt) {


    }

    public void render(GameContainer gc, Renderer r) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getAbilityOwner() {
        return abilityOwner;
    }

    public void setAbilityOwner(Unit abilityOwner) {
        this.abilityOwner = abilityOwner;
    }

    public int getCastRange() {
        return castRange;
    }

    public void setCastRange(int castRange) {
        this.castRange = castRange;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
    }
}
