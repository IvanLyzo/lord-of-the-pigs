package main;

import model.Man;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int FPS = 60;

    // SCREEN SETTINGS
    final int tileSize = 32; // 32px x 32px

    final int tileScreenWidth = 40;
    final int tileScreenHeight = 30;

    final int screenWidth = tileSize * tileScreenWidth;
    final int screenHeight = tileSize * tileScreenHeight;

    Thread gameLoop;

    Man man;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        man = new Man(100, 100, tileSize, tileSize);

        this.addMouseListener(man.mouseHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        long timer = 0;
        int drawCount = 0;

        while (gameLoop != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 0) {
                // UPDATE
                update();

                // DRAW
                repaint();

                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }

    public void update() {
        man.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        man.draw(g2);
    }
}
