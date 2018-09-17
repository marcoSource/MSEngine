package com.marcoSource.engine;

import com.marcoSource.engine.util.ColorUtil;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.security.Key;

public class Game implements Runnable{

    private Thread mainThread;
    private Screen screen;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private boolean running = false;

    private final double UPDATE_CAP = 1.0/60.0;

    private int width = 1280;
    private int height = 720;

    private float scale = 1f;

    private String title = GameConfig.windowTitle;

    public Game(AbstractGame game) {
        this.game = game;
    }

    public void create(){
        screen = new Screen(this);
        renderer = new Renderer(this);
        input = new Input(this);

        mainThread = new Thread(this);
        mainThread.run();
    }

    public void run(){
        running = true;

        boolean render = false;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        int fps = 0;

        while(running){

            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                render = true;

                game.update(this, (float)UPDATE_CAP);

                input.update();
                if(frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if (render){
                game.render(this, renderer);
                if(GameConfig.fps){renderer.drawText("FPS: " + fps, 0, 0, ColorUtil.DARK_BLUE);}
                screen.update();
                renderer.clear();
                frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }

        dispose();
    }

    public void dispose(){

    }

    public void destroy(){

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int mWidth) {
        this.width = mWidth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int mHeight) {
        this.height = mHeight;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float mScale) {
        this.scale = mScale;
    }

    public String getTitle() {
        return title;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setTitle(String mTitle) {
        this.title = mTitle;
    }

    public Input getInput(){
        return input;
    }
}
