package org.example.logic;

import org.example.model.Bird;
import org.example.model.Columns;
import org.example.model.Game;
import org.example.model.Screen;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ChangeWorld {
    private int speed;
    private final Random random = new Random();

    public ChangeWorld(int speed) {
        this.speed = speed;
    }

    private void crashCheck(
            Game game,
            Columns columns,
            Bird bird,
            Screen screen
    ) {
        if (bird.getBird().y == 0 || bird.getBird().y == screen.getHeight() - 140) {
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

    private void addColumn(Screen screen, Columns columns, int widthColumn, int space){
        int heightColumn = 50 + random.nextInt(250);

        int columnX;
        if (columns.getSize() == 0) {
            columnX = screen.getWidth() + widthColumn;
        } else {
            int firstColumnX = columns.getColumns().get(columns.getColumns().size() - 1).x;
            columnX = firstColumnX + 600;
        }
        columns.add(columnX, screen.getHeight() - heightColumn - 120, widthColumn, heightColumn);
        columns.add(columnX, 0, widthColumn, screen.getHeight() - heightColumn - space);

    }

    public void addColumns(Screen screen, Columns columns, int amount) {
        int widthColumn = 100;
        int space = 300;

        for (int i = 0; i < amount; i++){
            addColumn(screen, columns, widthColumn, space);
        }
    }

    // move columns on screen & move bird
    public void nextFrame(
            Game game,
            Columns columns,
            Bird bird,
            Screen screen
    ) {
        int passedColumns = 0;
        ArrayList<Integer> columnsToRemove = new ArrayList<>();
        for (int i = 0; i < columns.getSize(); i++) {
            Rectangle column = columns.getColumns().get(i);
            int birdX = bird.getBird().x;
            int columnEdgeX = (column.x + column.width);
            int columnCenterX = columnEdgeX - (column.width / 2);

            column.x -= speed;
            if (column.y == 0) {
                if (columnCenterX < birdX && columnCenterX + speed > birdX) {
                    passedColumns++;
                }
                if (columnEdgeX < 0) {
                    columnsToRemove.add(i);
                }
            }
        }

        for (int i = 0; i < passedColumns; i++) {
            game.passColumn();
        }
        for (int i = 0; i < columnsToRemove.size(); i++) {
            columns.remove();
            columns.remove();
        }
        addColumns(screen, columns, columnsToRemove.size());
        bird.getBird().y += Math.min(speed / 2, (screen.getHeight() - 140) - bird.getBird().y);
        crashCheck(game, columns, bird, screen);
    }

    //when user clicks to fly up
    public void jump(
            Game game,
            Columns columns,
            Bird bird,
            Screen screen
    ) {
        bird.getBird().y -= Math.min(25, bird.getBird().y);
        crashCheck(game, columns, bird, screen);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
