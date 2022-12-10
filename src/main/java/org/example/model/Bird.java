package org.example.model;

import java.awt.*;

public class Bird {

    private final Rectangle bird;

    public Bird (int x, int y, int width, int height){
        this.bird = new Rectangle(x, y, width, height);
    }

    public Rectangle getBird() {
        return bird;
    }

    public void setXY(int x, int y) {
        bird.x = x;
        bird.y = y;
    }
}
