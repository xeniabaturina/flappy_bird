package org.example.logic;

import org.example.logic.column_manager.ColumnManager;
import org.example.model.*;

import java.util.ArrayList;

import static org.example.common.Config.INITIAL_GAME_SPEED;

public class ChangeWorld {

    private Game game;
    private ColumnManager columnManager;
    private Bird bird;
    private Screen screen;
    private int speed;

    public ChangeWorld(Builder builder) {
        this.speed = builder.speed;
        this.game = builder.game;
        this.columnManager = builder.columnManager;
        this.bird = builder.bird;
        this.screen = builder.screen;
    }

    public static class Builder {
        private Game game;
        private ColumnManager columnManager;
        private Bird bird;
        private Screen screen;
        private int speed;

        public static Builder newInstance(
                Game game,
                ColumnManager columnManager,
                Bird bird,
                Screen screen
        ) {
            Builder builder = new Builder();
            builder.game = game;
            builder.columnManager = columnManager;
            builder.bird = bird;
            builder.screen = screen;
            return builder;
        }

        public Builder setSpeed(int speed) {
            this.speed = speed;
            return this;
        }

        public ChangeWorld build() {
            return new ChangeWorld(this);
        }

    }

    private void crashCheck() {
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
            columnManager.addColumn();
        }
    }

    // move columns on screen & move bird
    public void nextFrame() {
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
        columnManager.onNextFrame();
        addColumns(screen, columnManager, columnsToRemove.size());
        bird.getBird().y += Math.min(speed / 2, (screen.getHeight() - columnManager.getGrassHeight()) - bird.getBird().y - bird.getBird().height);
        crashCheck();
    }

    //when user clicks to fly up
    public void jump() {
        bird.getBird().y -= Math.min(speed * 5, bird.getBird().y);
        crashCheck();
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
