package org.example.logic;

import org.example.model.Bird;
import org.example.model.Columns;
import org.example.model.Game;
import org.example.model.Screen;

import java.awt.*;

public class Painter {
    private Graphics graphics;
    private Game game;
    private Columns columns;
    private Bird bird;
    private Screen screen;

    Color skyColor = Color.cyan;
    Color groundColor = Color.orange;
    Color grassColor = Color.green;
    Color birdColor = Color.red;
    Color columnColor = Color.green.darker();
    Color titleColor = Color.white;

    private void drawRectangle(Color color, Rectangle rectangle){
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private void drawTitle(){
        graphics.setColor(titleColor);
        graphics.setFont(new Font("Arial", 1, 100));

        switch (game.getGameStatus()) {
            case INITIAL -> graphics.drawString("Click to start!", 75, screen.getHeight() / 2 - 50);
            case GAME_OVER -> graphics.drawString("Game Over!", 100, screen.getHeight() / 2 - 50);
            case GAME_PLAYING -> graphics.drawString(String.valueOf(game.getPassedColumns()), screen.getWidth() / 2 - 25, 100);
        }
    }

    public void paint() {
        drawRectangle(skyColor, new Rectangle(0, 0, screen.getWidth(), screen.getHeight()));

        drawRectangle(groundColor, new Rectangle(0, screen.getHeight() - 120, screen.getWidth(), 120));

        drawRectangle(grassColor, new Rectangle(0, screen.getHeight() - 120, screen.getWidth(), 20));

        drawRectangle(birdColor, bird.getBird());

        for (Rectangle column : columns.getColumns()){
            drawRectangle(columnColor, column);
        }

        drawTitle();
    }
}
