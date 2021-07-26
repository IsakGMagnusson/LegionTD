package game.display.HUD;


import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.GameManager;
import game.GameObject;
import game.display.popup.TowerInfoBox;
import game.unit.tower.Tower;

public class RightHud extends GameObject {

    private int color;

    private Button buttonT1;
    private static Tower tierOne;
    private TowerInfoBox tierOneInfoBox;
    private Image tierOneImage = new Image(tierOne.getPath()+"icon.png");

    public static int buying = -1;

    public RightHud(double posX, double posY, int width, int height, int color){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.color = color;

        this.buttonT1 = new Button(posX+5, posY+5, 32, 32, tierOneImage);
        this.tierOneInfoBox = new TowerInfoBox(posX, posY+buttonT1.getHeight(), width, height, tierOne);

    }


    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {
        buttonT1.update(gc, gm, dt);

        if(buttonT1.getIsPressed()){
            buying = 0;
        }

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, color);

        buttonT1.render(gc, r);
        tierOneInfoBox.render(gc, r);


    }


    public static void setTierOne(Tower tierOne) {
        RightHud.tierOne = tierOne;
    }

}
