package game.gameplay.builder;

import engine.GameContainer;
import engine.Renderer;

public class BuildArea {
    private int xSquares = 8; int ySquares = 12;
    private int areaOffX = 100; int areaOffY  = 60;

    private BuildSquare[] buildArea = new BuildSquare[xSquares*ySquares];

    public BuildArea(){
        createSquares();
    }

    public void render(GameContainer gc, Renderer r) {
        for(BuildSquare b : buildArea) b.render(gc, r);
    }

    private void createSquares(){
        int i = 0;
        for(int n = 0; n < xSquares; n++)
            for(int m = 0; m < ySquares; m++)
                buildArea[i++] = new BuildSquare((BuildSquare.SQUARE_WIDTH*n)+areaOffX, (BuildSquare.SQUARE_WIDTH*m)+areaOffY);
    }

    public boolean isSquareFree(GameContainer gc){
        for(BuildSquare square : buildArea)
            if(square.isSquareHoovered(gc) && !square.getIsOccupied())
                return true;

        return false;
    }


    public BuildSquare getHooveredSquare(GameContainer gc){
        for(BuildSquare square : buildArea)
            if(square.isSquareHoovered(gc))
                return square;

        return null;
    }
}