package com.marcoSource.engine.gfx2d;

import com.marcoSource.engine.gfx.Image;

public class ImageTile extends Image {

    private int tileWidth, tileHeight;

    public ImageTile(String path, int tileWidth, int tileHeight) {
        super(path);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
