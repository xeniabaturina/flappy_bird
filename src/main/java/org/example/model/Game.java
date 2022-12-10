package org.example.model;

import static org.example.model.Game.GameStatus.*;

public class Game {

    private int passedColumns = 0;
    private GameStatus gameStatus = INITIAL;
    private int columnDistance = 120;

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getColumnDistance() {
        return columnDistance;
    }

    public void setColumnDistance(int newDistance) {
        if (gameNotPlaying()) {
            columnDistance = newDistance;
        }
    }

    public boolean startNewGame() {
        if (gameNotPlaying()) {
            gameStatus = GAME_PLAYING;
            return true;
        } else {
            return false;
        }
    }

    public void gameOver() {
        gameStatus = GAME_OVER;
        passedColumns = 0;
    }

    public boolean gameNotPlaying() {
        return gameStatus != GAME_PLAYING;
    }

    public boolean gamePlaying() {
        return gameStatus == GAME_PLAYING;
    }

    public int getPassedColumns() {
        return passedColumns;
    }

    public void passColumn() {
        passedColumns++;
    }

    public enum GameStatus {
        INITIAL, GAME_PLAYING, GAME_OVER,

    }
}
