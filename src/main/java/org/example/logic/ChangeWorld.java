package org.example.logic;

import org.example.model.*;

import java.util.ArrayList;

import static org.example.common.Config.INITIAL_GAME_SPEED;

public class ChangeWorld {

    private int speed = INITIAL_GAME_SPEED;

    public ChangeWorld(int speed) {
        this.speed = speed;
    }

    public ChangeWorld() {
    }

    private void crashCheck(Game game, ColumnManager columnManager, Bird bird, Screen screen) {
        if (bird.getBird().y == 0 || bird.getBird().y == screen.getHeight() - 140) {
            game.setGameStatus(GameStatus.GAME_OVER);
        } else {
            for (Column column : columnManager.getColumns()) {
                int birdX = bird.getBird().x;
                int birdY = bird.getBird().y;
                int birdWidth = bird.getBird().width;
                int birdHeight = bird.getBird().height;

                if (birdX + birdWidth >= column.getX() && birdX <= column.getX() + column.getWidth()) {
                    if (birdY + birdHeight >= column.getUpperColumnMinY() && birdY <= column.getUpperColumnMaxY() || birdY + birdHeight >= column.getBottomColumnMinY() && birdY <= column.getBottomColumnMaxY()) {
                        game.setGameStatus(GameStatus.GAME_OVER);
                        return;
                    }
                }
            }
        }
    }

    public void reset() {
        speed = INITIAL_GAME_SPEED;
    }

    public void addColumns(Screen screen, ColumnManager columnManager, int amount) {
        for (int i = 0; i < amount; i++) {
            columnManager.addColumn(screen);
        }
    }

    // move columns on screen & move bird
    public void nextFrame(Game game, ColumnManager columnManager, Bird bird, Screen screen) {
        int passedColumns = 0;
        ArrayList<Integer> columnsToRemove = new ArrayList<>();
        for (int i = 0; i < columnManager.getSize(); i++) {
            Column column = columnManager.getColumns().get(i);
            int birdX = bird.getBird().x;
            int columnEdgeX = (column.getX() + column.getWidth());
            int columnCenterX = columnEdgeX - (column.getWidth() / 2);

            column.moveLeft(speed);
            if (columnCenterX - speed <= birdX && columnCenterX > birdX) {
                passedColumns++;
            }
            if (columnEdgeX < 0) {
                columnsToRemove.add(i);
            }
        }

        for (int i = 0; i < passedColumns; i++) {
            game.passColumn();
        }
        for (int i = 0; i < columnsToRemove.size(); i++) {
            columnManager.remove();
        }
        addColumns(screen, columnManager, columnsToRemove.size());
        bird.getBird().y += Math.min(speed / 2, (screen.getHeight() - columnManager.getGrassHeight()) - bird.getBird().y - bird.getBird().height);
        crashCheck(game, columnManager, bird, screen);
    }

    //when user clicks to fly up
    public void jump(Game game, ColumnManager columnManager, Bird bird, Screen screen) {
        bird.getBird().y -= Math.min(speed * 5, bird.getBird().y);
        crashCheck(game, columnManager, bird, screen);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
