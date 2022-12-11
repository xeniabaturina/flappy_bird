package org.example.logic;

import org.example.model.Bird;
import org.example.model.Game;
import org.example.model.Screen;

import java.awt.Graphics;
import java.io.Serial;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.swing.JPanel;

public class Renderer extends JPanel {

    private Game game;
    private ColumnManager columnManager;
    private final Bird bird;
    private final Screen screen;


    public @Inject Renderer(Game game, ColumnManager columnManager, Bird bird, Screen screen) {
        this.game = game;
        this.columnManager = columnManager;
        this.bird = bird;
        this.screen = screen;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setColumns(ColumnManager columnManager) {
        this.columnManager = columnManager;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Painter painter = new Painter();
        painter.paint(graphics, game, columnManager, bird, screen);
    }
}
