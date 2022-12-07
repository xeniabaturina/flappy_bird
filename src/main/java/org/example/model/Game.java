package org.example.model;


import static org.example.model.GameStatus.INITIAL;

public class Game {

    public GameStatus gameStatus = INITIAL;
    public int PassedColumns = 0;

}

enum GameStatus {
    INITIAL,
    GAME_PLAYING,
    GAME_OVER,

}
