package com.marcoSource.game;

import com.marcoSource.engine.AbstractGame;
import com.marcoSource.engine.Game;
import com.marcoSource.engine.Renderer;
import com.marcoSource.engine.gfx.Image;
import com.marcoSource.engine.gfx2d.ImageTile;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {

    private ImageTile image;

    public GameManager() {
        image = new ImageTile("/anim.png", 16, 16);
    }

    @Override
    public void update(Game game, float deltaTime) {
        if(game.getInput().isKeyDown(KeyEvent.VK_A))
            System.out.println("fuck me");

        temp += deltaTime * 20;
        if(temp > 3)
            temp = 0;
    }

    float temp;

    @Override
    public void render(Game game, Renderer renderer) {
        renderer.drawImageTile(image, game.getInput().getMouseX(), game.getInput().getMouseY(), (int)temp, 0);
    }

    public static  void main(String args[]){
        Game game = new Game(new GameManager());
        game.create();
    }
}
