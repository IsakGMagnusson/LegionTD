package game.display.HUD.BotHud;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.unit.enemy.Enemy;

public class EnemyHud extends BotHud {
    private String info;
    private Enemy enemy;

    public EnemyHud(Enemy enemy) {
        this.enemy = enemy;
        info = "hp: " + (int)enemy.getHealth() + " Dmg: " + (int)enemy.getDamage();
    }

    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        info = "hp: " + (int)enemy.getHealth() + " Dmg: " + (int)enemy.getDamage();
    }

    @Override
    public void render(GameContainer gc, Renderer r) {
       // r.drawFillRect((int)getPosX(), (int)getPosY(), getWidth(), getHeight(), 0xFFFFFFFF);
        r.drawText(info, (int) getPosX(), (int)getPosY(), 0xff000000, 2);
    }
}
