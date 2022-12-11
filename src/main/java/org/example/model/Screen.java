package org.example.model;

import static org.example.common.Config.SCREEN_HEIGHT;
import static org.example.common.Config.SCREEN_WIDTH;

public class Screen {

    private final int width;
    private final int height;
    private String title;

    public Screen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Screen() {
        width = SCREEN_WIDTH;
        height = SCREEN_HEIGHT;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
