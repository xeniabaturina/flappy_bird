package org.example.logic;

import org.example.model.Bird;
import org.example.model.Columns;
import org.example.model.Game;
import org.example.model.Screen;

import java.awt.*;

public class Painter {

    Color skyColor = Color.cyan;
    Color groundColor = Color.orange;
    Color grassColor = Color.green;
    Color birdColor = Color.red;
    Color columnColor = Color.green.darker();
    Color titleColor = Color.white;

    private void drawRectangle(Graphics graphics, Color color, Rectangle rectangle){
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    private void drawTitle(Graphics graphics, Game game, Screen screen){
        graphics.setColor(titleColor);
        graphics.setFont(new Font("Arial", Font.BOLD, 100));

        switch (game.getGameStatus()) {
            case INITIAL -> graphics.drawString("Click to start!", 75, screen.getHeight() / 2 - 50);
            case GAME_OVER -> graphics.drawString("Game Over!", 100, screen.getHeight() / 2 - 50);
            case GAME_PLAYING -> graphics.drawString(String.valueOf(game.getPassedColumns()), screen.getWidth() / 2 - 25, 100);
        }
    }

    public void paint(Graphics graphics, Game game, Columns columns, Bird bird, Screen screen) {
        drawRectangle(graphics, skyColor, new Rectangle(0, 0, screen.getWidth(), screen.getHeight()));

        drawRectangle(graphics, groundColor, new Rectangle(0, screen.getHeight() - 120, screen.getWidth(), 120));

        drawRectangle(graphics, grassColor, new Rectangle(0, screen.getHeight() - 120, screen.getWidth(), 20));

        drawRectangle(graphics, birdColor, bird.getBird());

        for (Rectangle column : columns.getColumns()){
            drawRectangle(graphics, columnColor, column);
        }

        drawTitle(graphics, game,  screen);
    }
}
