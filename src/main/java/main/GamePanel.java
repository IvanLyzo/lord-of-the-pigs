package main;

import input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable {

    final int FPS = 60;

    Thread gameLoop;
    GameController gameController;

    InputHandler inputHandler;

    public GamePanel() {
        inputHandler = new InputHandler();
        gameController = new GameController(new Random().nextInt(), inputHandler);

        this.setPreferredSize(new Dimension(gameController.game.camera.screenWidth, gameController.game.camera.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);

        this.addKeyListener(inputHandler);
        this.addMouseListener(inputHandler);
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
                // INPUT


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
        gameController.interact();

        gameController.update();

        resetInputStates();
    }

    private void resetInputStates() {
        inputHandler.clickPoint = null;
        inputHandler.clickFlag = InputHandler.ClickFlag.INVALID;

        inputHandler.keysPressed.clear();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        gameController.draw(g2);
    }
}
