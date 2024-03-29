package engine.gfx;

public class ImageTile extends Image{

    private int tileW, tileH;

    public ImageTile(String path, int tileW, int tileH){
        super(path, 1);
        this.tileW = tileW;
        this.tileH = tileH;
    }

    public int getTileW() {
        return tileW;
    }

    public void setTileW(int tileW) {
        this.tileW = tileW;
    }

    public int getTileH() {
        return tileH;
    }

    public void setTileH(int tileH) {
        this.tileH = tileH;
    }
}
