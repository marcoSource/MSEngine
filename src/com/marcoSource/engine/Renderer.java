package com.marcoSource.engine;

import com.marcoSource.engine.gfx.Image;

import java.awt.image.DataBufferInt;

public class Renderer {

    private int pixelWidth, pixelHeight;
    private int[] pixels;

    public Renderer(Game game) {
        pixelHeight = game.getHeight();
        pixelWidth = game.getWidth();
        pixels = ((DataBufferInt) game.getScreen().getBufferedImage().getRaster().getDataBuffer()).getData();
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = 0;
        }
    }

    public void setPixels(int x, int y, int value){
        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) && value == 0xffff00ff){
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY) {
        for (int y = 0; y < image.getHeight(); y++){
            for (int x = 0; x < image.getWidth(); x++){
                setPixels(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }
}
