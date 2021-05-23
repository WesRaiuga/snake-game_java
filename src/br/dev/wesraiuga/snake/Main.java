package br.dev.wesraiuga.snake;

import javax.swing.*;

/**
 * Snake Game
 *
 * Author: Weslley Aguiar (WesRaiuga)
 *
 */
public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
        // TODO: possibilitar reiniciar o jogo quando dá game over
    }

    public Main() {
        add(new Game());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 440);
        setLocationRelativeTo(null);
        setTitle("Snake Game");
        setResizable(false);
        setVisible(true);
    }

}
