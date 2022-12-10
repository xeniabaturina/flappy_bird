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

    public Renderer renderer;
    private Game game;
    private Columns columns;
    private final Bird bird;
    private final Screen screen;
    private final ChangeWorld changeWorld = new ChangeWorld(3);
    Timer timer;
    int WIDTH = 800;
    int HEIGHT = 600;

    public FlappyBirdController() {
        this.game = new Game();
        this.screen = new Screen(WIDTH, HEIGHT);
        this.bird = new Bird(screen.getWidth() / 2 - 10, screen.getHeight() / 2 - 10, 20, 20);
        this.columns = new Columns();
        this.renderer = new Renderer(game, columns, bird, screen);
        timer = new Timer(20, this);
//        timer.start();
    }

    public Screen getScreen() {
        return screen;
    }

    public void start() {
        changeWorld.addColumns(screen, columns, 4);
        timer.restart();
    }

    public Renderer getRenderer(){
        return renderer;
    }

    public void restart() {
        game = new Game();
        columns = new Columns();
        renderer.setGame(game);
        renderer.setColumns(columns);
        start();
    }

    // region: ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        changeWorld.nextFrame(game, columns, bird, screen);
        if (game.getGameStatus() == Game.GameStatus.GAME_OVER) {
            restart();
        }
        renderer.repaint();
    }
    // end region

    // region: MouseListener

    @Override
    public void mouseClicked(MouseEvent e) {
        if (game.getGameStatus() == Game.GameStatus.INITIAL) {
            game.setGameStatus(Game.GameStatus.GAME_PLAYING);
        }
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

    private void checkGameOverAndRepaint(){
        if (game.getGameStatus() == Game.GameStatus.GAME_OVER) {
            restart();
        }
        renderer.repaint();
    }

    // region: KeyListener
    @Override
    public void keyTyped(KeyEvent e) {
        if (game.getGameStatus() == Game.GameStatus.INITIAL) {
            game.setGameStatus(Game.GameStatus.GAME_PLAYING);
        }
        changeWorld.jump(game, columns, bird, screen);
        checkGameOverAndRepaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {/* ignore */}

    @Override
    public void keyReleased(KeyEvent e) {/* ignore */}

    // end region
}
