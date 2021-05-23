package br.dev.wesraiuga.snake;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Snake {
    private final int[] x = new int[1600];
    private final int[] y = new int[Config.ALL_POINTS];

    private int bodyPoints = Config.SNAKE_INITIAL_BODY_POINTS;
    private int movementDelay = Config.SNAKE_MOVEMENT_DELAY;

    private final Image body = new ImageIcon("resources/img/body.png").getImage();
    private final Image head = new ImageIcon("resources/img/head.png").getImage();

    public int[] x() {
        return x;
    }

    public int[] y() {
        return y;
    }

    public int getBodyPoints() {
        return this.bodyPoints;
    }

    public void addBodyPoints(int increment) {
        this.bodyPoints = this.bodyPoints + increment;
    }

    public int getMovementDelay() {
        return this.movementDelay;
    }

    public void increaseSpeed(int increment) {
        this.movementDelay = movementDelay + increment;
    }

    public Image getBody() {
        return this.body;
    }

    public Image getHead() {
        return this.head;
    }

    public void move(String direction) {
        if (direction.equals("left")) {
            this.x[0] -= Config.POINT;
        }
    }
}
