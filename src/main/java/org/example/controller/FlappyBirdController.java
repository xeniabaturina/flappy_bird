package org.example.controller;

import org.example.logic.Renderer;
import org.example.logic.ChangeWorld;
import org.example.model.Bird;
import org.example.logic.ColumnManager;
import org.example.model.Game;
import org.example.model.Screen;

import javax.swing.*;
import java.awt.event.*;

public class FlappyBirdController implements ActionListener, MouseListener, KeyListener {

    private Game game;
    private ColumnManager columnManager;

    private final Bird bird;
    private final Screen screen;
    private final ChangeWorld changeWorld;
    private final Renderer renderer;
    private final Timer timer;

    int SCREEN_WIDTH = 800;
    int SCREEN_HEIGHT = 600;
    int GRASS_HEIGHT = 140;
    int BIRD_SIZE = 20;
    int INITIAL_GAME_SPEED = 3;
    int INITIAL_COLUMN_WIDTH = 100;
    int INITIAL_COLUMN_GAP = 250;
    int INITIAL_SPACE_BTW_COLUMNS = 400;
    int INITIAL_BIRD_X = SCREEN_WIDTH / 2 - 10;
    int INITIAL_BIRD_Y = SCREEN_HEIGHT / 2 - 10;

    public FlappyBirdController() {
        this.game = new Game();
        this.columnManager = new ColumnManager(
                INITIAL_COLUMN_WIDTH,
                INITIAL_COLUMN_GAP,
                INITIAL_SPACE_BTW_COLUMNS,
                GRASS_HEIGHT);

        this.bird = new Bird(INITIAL_BIRD_X, INITIAL_BIRD_Y, BIRD_SIZE, BIRD_SIZE);
        this.screen = new Screen(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.changeWorld = new ChangeWorld(INITIAL_GAME_SPEED);
        this.renderer = new Renderer(game, columnManager, bird, screen);
        this.timer = new Timer(20, this);
    }

    public void start() {
        changeWorld.addColumns(screen, columnManager, 4);
        timer.restart();
    }

    public void restart() {
        game = new Game();
        bird.setXY(INITIAL_BIRD_X, INITIAL_BIRD_Y);
        columnManager = new ColumnManager(
                INITIAL_COLUMN_WIDTH,
                INITIAL_COLUMN_GAP,
                INITIAL_SPACE_BTW_COLUMNS,
                GRASS_HEIGHT);

        renderer.setGame(game);
        renderer.setColumns(columnManager);
        start();
    }

    private void startGameIfNotYetPlaying(){
        if (game.getGameStatus() == Game.GameStatus.INITIAL) {
            game.setGameStatus(Game.GameStatus.GAME_PLAYING);
        }
    }

    private void checkGameOverAndRepaint(){
        if (game.getGameStatus() == Game.GameStatus.GAME_OVER) {
            restart();
        }
        renderer.repaint();
    }

    public Screen getScreen() {
        return screen;
    }

    public Renderer getRenderer(){
        return renderer;
    }

    // region: ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.getGameStatus() == Game.GameStatus.GAME_PLAYING) {
            columnManager.setColumnGap(INITIAL_COLUMN_GAP - game.getPassedColumns() * 3);
            changeWorld.setSpeed(INITIAL_GAME_SPEED + game.getPassedColumns() / 3);
            changeWorld.nextFrame(game, columnManager, bird, screen);
            checkGameOverAndRepaint();
        }
    }
    // end region

    // region: MouseListener

    @Override
    public void mouseClicked(MouseEvent e) {
        startGameIfNotYetPlaying();
        changeWorld.jump(game, columnManager, bird, screen);
        checkGameOverAndRepaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {/* ignore */}

    @Override
    public void mouseReleased(MouseEvent e) {/* ignore */}

    @Override
    public void mouseEntered(MouseEvent e) {/* ignore */}

    @Override
    public void mouseExited(MouseEvent e) {/* ignore */}

    // end region

    // region: KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        startGameIfNotYetPlaying();
        changeWorld.jump(game, columnManager, bird, screen);
        checkGameOverAndRepaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {/* ignore */}

    @Override
    public void keyReleased(KeyEvent e) {/* ignore */}

    // end region
}
