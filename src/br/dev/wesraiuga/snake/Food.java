package br.dev.wesraiuga.snake;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Food {
    private final Image food = new ImageIcon("resources/img/food.png").getImage();
    private int position_X;
    private int position_Y;

    public Image getFood() {
        return this.food;
    }

    public int getPosition_X() {
        return this.position_X;
    }

    public void setPosition_X(int position_X) {
        this.position_X = position_X;
    }

    public int getPosition_Y() {
        return this.position_Y;
    }

    public void setPosition_Y(int position_Y) {
        this.position_Y = position_Y;
    }

}
