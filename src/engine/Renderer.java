package engine;

import engine.gfx.Image;
import engine.gfx.ImageTile;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pW, pH;
    private int[] p;

    public Renderer(GameContainer gc) {

        pW = gc.getWidth();
        pH = gc.getHeight();
        p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i = 0; i < p.length; i++) {
            p[i] = 0;
        }
    }

    public void setPixel(int x, int y, int value) {
        if (x < 0 || x >= pW || y < 0 || y >= pH || ((value >> 24) & 0xff) == 0) {
            return;
        }

        p[x + y * pW] = value;
    }

    public void drawImage(Image image, int offX, int offY) {

        //dont render
        if (offX < -image.getW()) return;
        if (offY < -image.getH()) return;
        if (offX >= pW) return;
        if (offY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = image.getW();
        int newHeight = image.getH();

        //clipping code
        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pW) newWidth -= newWidth + offX - pW;
        if (newHeight + offY >= pH) newHeight -= newHeight + offY - pH;

        for (int y = newY; y < newHeight; y++)
            for (int x = newX; x < newWidth; x++)
                setPixel(x + offX, y + offY, image.getP()[x + y * image.getW()]);
    }

    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY) {

        //dont render
        if (offX < -image.getTileW()) return;
        if (offY < -image.getTileH()) return;
        if (offX >= pW) return;
        if (offY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = image.getTileW();
        int newHeight = image.getTileH();

        //clipping code
        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pW) newWidth -= newWidth + offX - pW;
        if (newHeight + offY >= pH) newHeight -= newHeight + offY - pH;

        for (int y = newY; y < newHeight; y++)
            for (int x = newX; x < newWidth; x++)
                setPixel(x + offX, y + offY, image.getP()[(x + tileX * image.getTileW()) + (y + tileY * image.getTileH()) * image.getW()]);

    }

    public void drawRect(int offX, int offY, int width, int height, int color) {


        for (int y = 0; y <= height; y++) {
            setPixel(offX, y + offY, color);
            setPixel(offX + width, y + offY, color);
        }

        for (int x = 0; x <= width; x++) {
            setPixel(x + offX, offY, color);
            setPixel(x + offX, offY + height, color);
        }
    }

    //todo BUG making 0 = 1 and 1 = etc. So smallest rect is 0.
    public void drawFillRect(int offX, int offY, int width, int height, int color) {

        //dont render
        if (offX < -width) return;
        if (offY < -height) return;
        if (offX >= pW) return;
        if (offY >= pH) return;

        int newX = 0;
        int newY = 0;
        int newWidth = width;
        int newHeight = height;

        //clipping code
        if (offX < 0) newX -= offX;
        if (offY < 0) newY -= offY;
        if (newWidth + offX >= pW) newWidth -= newWidth + offX - pW;
        if (newHeight + offY >= pH) newHeight -= newHeight + offY - pH;


        for (int y = newY; y <= newHeight; y++) {
            for (int x = newX; x <= newWidth; x++) {
                setPixel(x + offX,y + offY, color);
            }
        }

    }

}
