package com.marcoSource.game;

import com.marcoSource.engine.AbstractGame;
import com.marcoSource.engine.Game;
import com.marcoSource.engine.Renderer;
import com.marcoSource.engine.gfx.Image;

import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame {

    private Image image;

    public GameManager() {
        image = new Image("/red.png");
    }

    @Override
    public void update(Game game, float deltaTime) {
        if(game.getInput().isKeyDown(KeyEvent.VK_A))
            System.out.println("fuck me");
    }

    @Override
    public void render(Game game, Renderer renderer) {
        renderer.drawImage(image, game.getInput().getMouseX(), game.getInput().getMouseY());
    }

    public static  void main(String args[]){
        Game game = new Game(new GameManager());
        game.create();
    }
}
