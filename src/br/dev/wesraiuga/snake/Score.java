package br.dev.wesraiuga.snake;

public class Score {
    private int total = 0;

    public int getTotal() {
        return this.total;
    }

    public void add(int increment) {
        this.total = this.total + increment;
    }

}
