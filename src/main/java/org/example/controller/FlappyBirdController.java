package org.example.controller;

import org.example.logic.Renderer;
import org.example.logic.ChangeWorld;
import org.example.model.*;
import org.example.logic.ColumnManager;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.event.*;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.example.common.Config.BIRD_SIZE;

public class FlappyBirdController implements ActionListener, MouseListener, KeyListener {

    private final Game game;
    private final Bird bird;
    private final ColumnManager columnManager;
    private final Screen screen;
    private final ChangeWorld changeWorld;
    private final Renderer renderer;
    private final Timer timer;
    private boolean isDifficultyIncreased = false;


    public @Inject FlappyBirdController(
            Bird bird,
            Game game,
            Screen screen,
            Renderer renderer,
            ChangeWorld changeWorld,
            ColumnManager columnManager
    ) {
        this.game = game;
        this.bird = bird;
        this.screen = screen;
        this.renderer = renderer;
        this.changeWorld = changeWorld;
        this.columnManager = columnManager;
        this.timer = new Timer(20, this);
    }

    public void start() {
        changeWorld.addColumns(screen, columnManager, 4);
        timer.restart();
    }

    public void restart() {
        game.reset();
        bird.reset();
        changeWorld.reset();
        columnManager.reset();
        start();
    }

    private void startGameIfNotYetPlaying() {
        if (game.getGameStatus() == GameStatus.INITIAL) {
            game.setGameStatus(GameStatus.GAME_PLAYING);
        }
    }

    private void checkGameOverAndRepaint() {
        if (game.getGameStatus() == GameStatus.GAME_OVER) {
            restart();
        }
        renderer.repaint();
    }

    private void jump() {
        startGameIfNotYetPlaying();
        changeWorld.jump(game, columnManager, bird, screen);
        checkGameOverAndRepaint();
    }

    public Screen getScreen() {
        return screen;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    // region: ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.getGameStatus() == GameStatus.GAME_PLAYING) {

            if (game.getPassedColumns() % 10 == 0) {
                if (!isDifficultyIncreased) {
                    isDifficultyIncreased = true;
                    columnManager.setColumnGap(max(BIRD_SIZE * 5, columnManager.getColumnGap() - 1));
                    changeWorld.setSpeed(changeWorld.getSpeed() + 1);
                }
            } else {
                isDifficultyIncreased = false;
            }
            changeWorld.nextFrame(game, columnManager, bird, screen);
            checkGameOverAndRepaint();
        }
    }
    // end region

    // region: MouseListener

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
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
        jump();
    }

    @Override
    public void keyPressed(KeyEvent e) {/* ignore */}

    @Override
    public void keyReleased(KeyEvent e) {/* ignore */}

    // end region
}
