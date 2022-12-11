package org.example.model;

import javax.inject.Inject;

import static org.example.model.GameStatus.*;

public class Game {

    private int passedColumns = 0;
    private GameStatus gameStatus = INITIAL;

    public void reset() {
        gameStatus = INITIAL;
        passedColumns = 0;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
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

}
