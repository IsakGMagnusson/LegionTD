package game.display.HUD.RightHud.InfoBox;

import engine.GameContainer;
import engine.Renderer;
import game.GameManager;
import game.GameObject;
import game.unit.ability.Ability;
import game.unit.tower.Tower;

import java.util.ArrayList;

public class DetailedTowerInfoBox extends GameObject {

    private Tower tower;
    private int scale = 2;
    private int yMargin = 20;

    public DetailedTowerInfoBox(Tower tower){
        this.width = 700;
        this.height = 200;
        this.posX = GameManager.SCREEN_WIDTH/2-width/2;
        this.posY = (GameManager.SCREEN_HEIGHT/2)-(height);
        this.tower = tower;
    }


    @Override
    public void update(GameContainer gc, GameManager gm, float dt) {

    }

    @Override
    public void render(GameContainer gc, Renderer r) {
        r.drawFillRect((int)posX, (int)posY, width, height, 0xFF000000);
        r.drawRect((int)posX, (int)posY, width, height, 0xFFF7EF8A);

        printTowerInfo(r, tower);

        if(tower.getUnitAbilities() != null){
            for(Ability ability : tower.getUnitAbilities()){
                printAbilityInfo(r, ability);
            }
        } else{
            r.drawText("No abilities", (int)posX+150, (int)posY, 0xFFFFFFFF, scale);
        }
    }

    private void printTowerInfo(Renderer r, Tower tower){
        int infoCounter = 0;
        r.drawText("Tower: ", (int)posX, (int)posY+yMargin*infoCounter, 0xFFFFFFFF, scale);
        infoCounter++;
        for(String info : getTowerInfo(tower)){
            r.drawText(info, (int)posX, (int)posY+yMargin*infoCounter, 0xFFFFFFFF, scale);
            infoCounter++;
        }
    }

    private void printAbilityInfo(Renderer r, Ability ability){
        int infoCounter = 0;
        r.drawText("Ability: ", (int)posX+150, (int)posY+yMargin*infoCounter, 0xFFFFFFFF, scale);
        infoCounter++;
        for(String info : getAbilityInfo(ability)){
            r.drawText(info, (int)posX+150, (int)posY+yMargin*infoCounter, 0xFFFFFFFF, scale);
            infoCounter++;
        }
    }

    private String[] getTowerInfo(Tower tower){
        String[] allInfo = {
                "Tier: " + tower.getTier(),
                "Name: " + tower.getName(),
                "Dmg: " + (int) tower.getDamage(),
                "Att. speed: " +  tower.getAttackSpeed(),
                "Health: " + (int) tower.getHealth(),
                "Cost: " + tower.getCost()
        };
        return allInfo;
    }

    private ArrayList<String> getAbilityInfo(Ability ability){
        ArrayList<String> allInfo = new ArrayList<>();
        allInfo.add("Name: " + ability.getName());
        allInfo.add("Damage: " +ability.getDamage());
        allInfo.add("Cooldown: " +ability.getCooldown());
        allInfo.add("Castrange: " +ability.getCastRange());
        allInfo.add("Description: " +   ability.getDescription());

        //linebreak every descRowLength words
        String description = allInfo.get(allInfo.size()-1);
        allInfo.remove(description);

        int descRowMaxLength = 25;
        while (true){
            String toBeAdded = "";
            String addWord = "";
            int counter = 0;
            for(char c : description.toCharArray()){
                addWord += c;

                //each time a new word ends: add it to string to be added to array
                if(c == ' '){
                    toBeAdded = addWord;
                }

                counter++;
                if (counter >= descRowMaxLength){
                    break;
                }
            }

            //if counter less than descRowLength means out of chars. Add last of string
            if(counter < descRowMaxLength){
                allInfo.add(addWord);
                break;
            }

            allInfo.add(toBeAdded);
            //Remove from description what has just been added
            description = description.substring(toBeAdded.length());
        }

        return allInfo;
    }
}
