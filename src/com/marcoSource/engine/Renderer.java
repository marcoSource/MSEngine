package com.marcoSource.engine;

import com.marcoSource.engine.gfx.Image;
import com.marcoSource.engine.gfx2d.ImageTile;

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

    public void setPixels(int x, int y, int value) {
        if ((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) && value == 0xffff00ff) {
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY) {
        //Dont render
        if(offX < -image.getWidth()) { return; }
        if(offY < -image.getHeight()) { return; }
        if(offX >= pixelWidth) {return;}
        if(offY >= pixelHeight){ return;}

        int newX = 0;
        int newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        //Render what is visible
        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newWidth + offX >= pixelWidth) { newWidth -= newWidth + offX - pixelWidth; }
        if(newHeight + offY >= pixelHeight) { newHeight -= newHeight + offY - pixelHeight; }

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                setPixels(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){
        //Dont render
        if(offX < -image.getTileWidth()) { return; }
        if(offY < -image.getTileHeight()) { return; }
        if(offX >= pixelWidth) {return;}
        if(offY >= pixelHeight){ return;}

        int newX = 0;
        int newY = 0;
        int newWidth = image.getTileWidth();
        int newHeight = image.getTileHeight();

        //Render what is visible
        if(offX < 0) { newX -= offX; }
        if(offY < 0) { newY -= offY; }
        if(newWidth + offX >= pixelWidth) { newWidth -= newWidth + offX - pixelWidth; }
        if(newHeight + offY >= pixelHeight) { newHeight -= newHeight + offY - pixelHeight; }

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                setPixels(x + offX, y + offY, image.getPixels()[(x + tileX * image.getTileWidth()) + (y + tileY * image.getTileHeight()) * image.getWidth()]);
            }
        }
    }
}
