package org.example.controller;

import org.example.logic.Renderer;
import org.example.logic.ChangeWorld;
import org.example.model.Bird;
import org.example.model.Columns;
import org.example.model.Game;
import org.example.model.Screen;

import javax.swing.*;
import java.awt.event.*;

public class FlappyBirdController implements ActionListener, MouseListener, KeyListener {

    private Game game;
    private Columns columns;
    private final Bird bird;
    private final Screen screen;
    private final ChangeWorld changeWorld;
    private final Renderer renderer;
    private final Timer timer;
    int WIDTH = 800;
    int HEIGHT = 600;
    int INITIAL_SPEED = 3;

    public FlappyBirdController() {
        this.game = new Game();
        this.columns = new Columns();
        this.bird = new Bird(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);
        this.screen = new Screen(WIDTH, HEIGHT);
        this.changeWorld = new ChangeWorld(INITIAL_SPEED);
        this.renderer = new Renderer(game, columns, bird, screen);
        this.timer = new Timer(20, this);
    }

    public void start() {
        changeWorld.addColumns(screen, columns, 4);
        timer.restart();
    }

    public void restart() {
        game = new Game();
        columns = new Columns();
        renderer.setGame(game);
        renderer.setColumns(columns);
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
            changeWorld.nextFrame(game, columns, bird, screen);
            checkGameOverAndRepaint();
        }
    }
    // end region

    // region: MouseListener

    @Override
    public void mouseClicked(MouseEvent e) {
        startGameIfNotYetPlaying();
        changeWorld.jump(game, columns, bird, screen);
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
        changeWorld.jump(game, columns, bird, screen);
        checkGameOverAndRepaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {/* ignore */}

    @Override
    public void keyReleased(KeyEvent e) {/* ignore */}

    // end region
}
