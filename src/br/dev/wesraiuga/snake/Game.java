package br.dev.wesraiuga.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel implements ActionListener {

    private final Snake snake = new Snake();
    private final Food food = new Food();
    private final Score score = new Score();

    private boolean isGameRunning;

    private boolean moveLeft = false;
    private boolean moveRight = false;
    private boolean moveUp = false;
    private boolean moveDown = false;

    public Game() {
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
        setSize(Canvas.WIDTH_PXL, Canvas.HEIGHT_PXL);

        startGame();
    }

    public void startGame() {
        isGameRunning = true;

        snakesStartingPosition();

        generateFood();

        Timer timer = new Timer(snake.getMovementDelay(), this);
        timer.start();
    }

    private void snakesStartingPosition() {
        for (int i = 0; i < snake.getBodyPoints(); i++) {
            snake.x()[i] = 50 - i * Config.POINT;
            snake.y()[i] = 50;
        }
    }

    public void generateFood() {
        int random = (int) (Math.random() * (Canvas.WIDTH_PT - 1));
        food.setPosition_X(random * Config.POINT);

        random = (int) (Math.random() * (Canvas.HEIGHT_PT - 1));
        food.setPosition_Y(random * Config.POINT);
    }

    @Override
    public void paint (Graphics graphics) {
        super.paint(graphics);

        if (isGameRunning) {
            drawFood(graphics);
            drawSnake(graphics);
            drawScore(graphics);

            Toolkit.getDefaultToolkit().sync();
            graphics.dispose();
        }
        else {
            gameOver(graphics);
        }
    }

    public void drawFood(Graphics graphics) {
        graphics.drawImage(food.getFood(), food.getPosition_X(), food.getPosition_Y(), this);
    }

    private void drawSnake(Graphics graphics) {
        for (int i = 0; i < snake.getBodyPoints(); i++) {
            if (i == 0) {
                graphics.drawImage(snake.getHead(), snake.x()[i], snake.y()[i], this);
            }
            else {
                graphics.drawImage(snake.getBody(), snake.x()[i], snake.y()[i], this);
            }
        }
    }

    public void drawScore(Graphics graphics) {
        Message message = Message.builder()
                                        .text("SCORE: " + score.getTotal())
                                        .font("Consolas", 12, Font.BOLD)
                                    .build();

        FontMetrics fontMetrics = this.getFontMetrics(message.getFont());

        final int position_X = (Canvas.WIDTH_PXL - fontMetrics.stringWidth(message.getText())) - 10;
        final int position_Y = Canvas.HEIGHT_PXL - 10;

        graphics.setColor(Color.white);
        graphics.setFont(message.getFont());
        graphics.drawString(
                message.getText(),
                position_X,
                position_Y
        );
    }

    public void gameOver(Graphics graphics) {
        Message message = Message.builder()
                                        .text("GAME OVER!   Score: " + score.getTotal())
                                        .font("Consolas", 16, Font.BOLD)
                                    .build();

        FontMetrics fontMetrics = this.getFontMetrics(message.getFont());

        final int position_X = (Canvas.WIDTH_PXL - fontMetrics.stringWidth(message.getText())) / 2;
        final int position_Y = Canvas.HEIGHT_PXL / 2;

        graphics.setColor(Color.white);
        graphics.setFont(message.getFont());
        graphics.drawString(
                message.getText(),
                position_X,
                position_Y
        );
    }

    public void checkIfFoodWasEaten() {
        if ( (snake.x()[0] == food.getPosition_X()) && (snake.y()[0] == food.getPosition_Y()) ) {
            snake.addBodyPoints(1);
            score.add(1);
            generateFood();
        }
    }

    public void move() {
        // Para cada ponto da cobrinha desenha em (x,y)
        for (int i = snake.getBodyPoints(); i > 0; i--) {
            snake.x()[i] = snake.x()[(i - 1)];
            snake.y()[i] = snake.y()[(i - 1)];
        }

        // Se for para esquerda decrementa em x
        if (moveLeft) {
            snake.x()[0] -= Config.POINT;
        }

        // Se for para direita incrementa em x
        if (moveRight) {
            snake.x()[0] += Config.POINT;
        }

        // Se for para cima decrementa em y
        if (moveUp) {
            snake.y()[0] -= Config.POINT;
        }

        // Se for para baixo incrementa em y
        if (moveDown) {
            snake.y()[0] += Config.POINT;
        }

    }

    public void checkCollision() {
        // Snake body
        for (int i = snake.getBodyPoints(); i > 0; i--) {
            if ( i > 3
                    && snake.x()[0] == snake.x()[i]
                    && snake.y()[0] == snake.y()[i] )
            {
                isGameRunning = false;
                break;
            }
        }

        // Edges
        if ( snake.y()[0] >= Canvas.HEIGHT_PXL || snake.y()[0] < 0
                || snake.x()[0] >= Canvas.WIDTH_PXL || snake.x()[0] < 0 )
        {
            isGameRunning = false;
        }

    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (isGameRunning) {
            checkIfFoodWasEaten();
            checkCollision();
            move();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed (KeyEvent e) {
            int key =  e.getKeyCode();

            // Verifica os movimentos e manipula as variáveis, para movimentar
            // corretamente sobre a tela
            if ( key == KeyEvent.VK_LEFT && !moveRight ) {
                moveLeft = true;
                moveUp = false;
                moveDown = false;
//                snake.move("left");
            }

            if ( key == KeyEvent.VK_RIGHT && !moveLeft ) {
                moveRight = true;
                moveUp = false;
                moveDown = false;
            }

            if ( key == KeyEvent.VK_UP && !moveDown ) {
                moveUp = true;
                moveLeft = false;
                moveRight = false;
            }

            if ( key == KeyEvent.VK_DOWN && !moveUp ) {
                moveDown = true;
                moveLeft = false;
                moveRight = false;
            }
        }

    }

}





