package game;

import engine.AbstractGame;
import engine.GameContainer;
import engine.Renderer;
import engine.gfx.Image;
import game.gameplay.GamePlay;

import java.util.ArrayList;

public class GameManager extends AbstractGame {

    public static final int SCREEN_WIDTH =1420;
    public static final int SCREEN_HEIGHT =880;


    private ArrayList<GameObject> objects = new ArrayList<GameObject>();

    GamePlay gamePlay = new GamePlay();

    public GameManager(){

    }

    @Override
    public void init(GameContainer gc) {

    }

    @Override
    public void update(GameContainer gc, float dt) {
        gamePlay.update(gc, this, dt);

        for(int i = 0; i < objects.size(); i++){
            objects.get(i).update(gc, this, dt);
            if(objects.get(i).isDead()){
                objects.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(GameContainer gc, Renderer r) {

        gamePlay.render(gc, r);

        for(GameObject obj : objects){
            obj.render(gc, r);
        }
    }

    public void addObject(GameObject object){
        objects.add(object);
    }

    public ArrayList<GameObject> getObjects(){
        return objects;
    }

    public static void main(String args[]){
        GameContainer gc = new GameContainer(new GameManager());
        gc.setWidth(SCREEN_WIDTH);
        gc.setHeight(SCREEN_HEIGHT);
       // gc.setWidth(1920/4);
      //  gc.setHeight(1080/4);
        //gc.setScale(3f);
        gc.start();
    }
}
