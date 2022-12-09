package org.example.logic;

import org.example.model.Bird;
import org.example.model.Columns;
import org.example.model.Game;
import org.example.model.Screen;

import java.awt.*;
import java.util.Random;

public class ChangeWorld {
    private int speed;

    public ChangeWorld(int speed) {
        this.speed = speed;
    }

    private void crashCheck(
            Game game,
            Columns columns,
            Bird bird,
            Screen screen
    ) {
        if (bird.getBird().y == 0 || bird.getBird().y == screen.getHeight()) {
            game.setGameStatus(Game.GameStatus.GAME_OVER);
        } else {
            for (Rectangle column : columns.getColumns()) {
                int birdX = bird.getBird().x;
                int birdY = bird.getBird().y;
                if (birdX >= column.x && birdX <= column.x + column.width
                        && birdY >= column.y && birdY <= column.y + column.height) {
                    game.setGameStatus(Game.GameStatus.GAME_OVER);
                    return;
                }
            }
        }
    }

    // move columns on screen & move bird
    public void nextFrame(
            Game game,
            Columns columns,
            Bird bird,
            Screen screen
    ) {
        for (Rectangle column : columns.getColumns()) {
            int birdX = bird.getBird().x;
            int columnEdgeX = (column.x + column.width);
            int columnCenterX = (columnEdgeX / 2);

            column.x -= speed;
            if (column.y == 0) {
                if (columnCenterX < birdX && columnCenterX + speed >= birdX) {
                    game.passColumn();
                }
                if (columnEdgeX < 0) {
                    Random random = new Random();
                    int space = 300;
                    int width = 100;
                    int height = 50 + random.nextInt(300);

                    int firstColumnX = columns.getColumns().get(columns.getColumns().size() - 1).x;
                    columns.add(firstColumnX + 600, screen.getHeight() - height - 120, width, height);
                    columns.add(firstColumnX, 0, width, screen.getHeight() - height - space);
                    columns.remove();
                    columns.remove();
                }
            }
        }
        bird.getBird().y += Math.min(speed / 2, screen.getHeight() - bird.getBird().y);
        crashCheck(game, columns, bird, screen);
    }

    //when user clicks to fly up
    public void jump(
            Game game,
            Columns columns,
            Bird bird,
            Screen screen
    ) {
        bird.getBird().y -= Math.min(10, bird.getBird().y);
        crashCheck(game, columns, bird, screen);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
