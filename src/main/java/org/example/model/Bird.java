package org.example.model;

import java.awt.*;

import static org.example.common.Config.*;

public class Bird {

    private final Rectangle bird;

    public Bird(int x, int y, int width, int height) {
        this.bird = new Rectangle(x, y, width, height);
    }

    public Bird() {
        this.bird = new Rectangle(INITIAL_BIRD_X, INITIAL_BIRD_Y, BIRD_SIZE, BIRD_SIZE);
    }

    public Rectangle getBird() {
        return bird;
    }

    public void reset() {
        bird.x = INITIAL_BIRD_X;
        bird.y = INITIAL_BIRD_Y;
    }

    public void setXY(int x, int y) {
        bird.x = x;
        bird.y = y;
    }
}
