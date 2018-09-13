package com.marcoSource.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Screen {

    private JFrame jFrame;
    private BufferedImage bufferedImage;
    private Canvas canvas;
    private Graphics graphics;
    private BufferStrategy bufferStrategy;

    public Screen(Game game) {
        bufferedImage = new BufferedImage(game.getWidth(), game.getHeight(), BufferedImage.TYPE_INT_RGB);
        canvas = new Canvas();
        Dimension dimension = new Dimension((int)(game.getWidth() * game.getScale()), (int)(game.getHeight() * game.getScale()));
        canvas.setPreferredSize(dimension);
        canvas.setMinimumSize(dimension);
        canvas.setMaximumSize(dimension);
        jFrame = new JFrame(game.getTitle());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new BorderLayout());
        jFrame.add(canvas, BorderLayout.CENTER);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        graphics = bufferStrategy.getDrawGraphics();
    }

    public void update(){
        graphics.drawImage(bufferedImage,0, 0, canvas.getWidth(), canvas.getHeight(), null);
        bufferStrategy.show();
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return jFrame;
    }
}
