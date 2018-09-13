package com.marcoSource.engine.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Image {

    private int width, height;
    private int[] pixels;

    public Image(String path) {
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(Image.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = bufferedImage.getWidth();
        height = bufferedImage.getHeight();
        pixels = bufferedImage.getRGB(0, 0, width, height, null, 0, width);

        bufferedImage.flush();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }
}
