package engine;

import engine.gfx.Font;
import engine.gfx.Image;
import engine.gfx.ImageTile;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pW, pH;
    private int[] p;

    private Font font = Font.font;

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
        if (x < 0 || x >= pW || y < 0 || y >= pH) {
            return;
        }

        p[x + y * pW] = value;
    }

    public void drawText(String text, int offX, int offY, int color, int scale){
        text = text.toUpperCase();
        int offset = 0;

        for(int n = 0; n < text.length(); n++){
            int unicdoe = text.codePointAt(n)-32;

            for(int y = 0; y < font.getFontImage().getH(); y++){
                for(int x = 0; x < font.getWidths()[unicdoe]; x++){

                    for(int i = 0; i < scale; i++){
                        for(int j = 0; j < scale; j++) {
                            if(font.getFontImage().getP()[(x + font.getOffsets()[unicdoe]) + y * font.getFontImage().getW()] == 0xffffffff) {
                                setPixel((offX + offset + i + x*scale), (offY + j + y*scale), color);
                            }
                        }
                    }


                }
            }
            offset += font.getWidths()[unicdoe]*scale;
        }
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


    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY, int scale, double rotation){
        double angle2 = Math.toRadians(rotation);
        rotation = angle2;
        int w = image.getTileW();
        int h  = image.getTileH();
        int size =  (int) (Math.sqrt(w * w + h * h));
        int xCenter = w / 2;
        int yCenter = h / 2;
        final int halfSize = size / 2;

        for(int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int samplePointX = x - halfSize;
                int samplePointY = y - halfSize;
                int xData, yData;
                xData = (int) (samplePointX * -Math.cos(rotation) + samplePointY * Math.sin(rotation));
                yData = (int) (samplePointX * -Math.sin(rotation) - samplePointY * Math.cos(rotation));
                xData += xCenter;
                yData += yCenter;


                if (!(xData >= 0 && xData < w)) {
                    continue;
                }
                if (!(yData >= 0 && yData < h)) {
                    continue;
                }
                if ((x) + (y) * size > size * size) {
                    continue;
                }

                for(int i = 0; i < scale; i++){
                    for(int j = 0; j < scale; j++) {
                        setPixel((offX + i + x*scale), (offY + j + y*scale), image.getP()[(xData + tileX * image.getTileW()) + (yData + tileY * image.getTileH()) * image.getW()]);
                    }
                }

            }
        }
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

     public void drawCircle(int x, int y, int r, int color, int thickness) {
        if(thickness > 0) {
            double i, angle, x1, y1;

            for (i = 0; i < 360; i += 1) {
                angle = i;
                x1 = (r - thickness) * Math.cos(angle * Math.PI / 180);
                y1 = (r - thickness) * Math.sin(angle * Math.PI / 180);

                int ElX = (int) (x + x1);
                int ElY = (int) (y + y1);
                setPixel(ElX, ElY, color);
            }
            drawCircle(x, y, r, color, thickness - 1);
        }
    }

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
