package game.gameplay;

import engine.GameContainer;
import game.GameManager;
import game.GameObject;

import java.util.ArrayList;

public class Camera {

    private int cameraSpeed = 8;
    private int cameraPosX = 0;
    private int cameraPosY = 0;

    public void moveCamera(GameContainer gc, GameManager gm) {
        //todo: add maxheight/maxwidth for camera instead of magic numbers in if cases

        if(gc.getInput().getMouseX() < 5 && (cameraPosX < 750)){
            cameraPosX += cameraSpeed;
            for(ArrayList<GameObject> object : gm.getObjects()){
                object.forEach((obj) -> obj.setPosX(obj.getPosX()+cameraSpeed));
            }
        }
        if(gc.getInput().getMouseX() > GameManager.SCREEN_WIDTH-5 && !(cameraPosX < -250)){
            cameraPosX -= cameraSpeed;
            for(ArrayList<GameObject> object : gm.getObjects()){
                object.forEach((obj) -> obj.setPosX(obj.getPosX()-cameraSpeed));
            }
        }

        if(gc.getInput().getMouseY() < 5 && (cameraPosY < 350)){
            cameraPosY += cameraSpeed;
            for(ArrayList<GameObject> object : gm.getObjects()){
                object.forEach((obj) -> obj.setPosY(obj.getPosY()+cameraSpeed));
            }
        }

        if(gc.getInput().getMouseY() > GameManager.SCREEN_HEIGHT-25 && !(cameraPosY < -350)){
            cameraPosY -= cameraSpeed;
            for(ArrayList<GameObject> object : gm.getObjects()){
                object.forEach((obj) -> obj.setPosY(obj.getPosY()-cameraSpeed));
            }
        }
    }
}
