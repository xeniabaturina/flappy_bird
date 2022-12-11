package org.example.logic;

import org.example.logic.column_manager.ColumnManager;
import org.example.model.Bird;
import org.example.model.Game;
import org.example.model.Screen;

import javax.inject.Inject;
import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class Renderer extends JPanel {

    private Game game;
    private ColumnManager columnManager;
    private final Bird bird;
    private final Screen screen;
    private final Painter painter;

    public @Inject Renderer(Game game, ColumnManager columnManager, Bird bird, Screen screen, Painter painter) {
        this.game = game;
        this.columnManager = columnManager;
        this.bird = bird;
        this.screen = screen;
        this.painter = painter;
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
    public void paintComponent(Graphics graphics) {
        painter.paint(graphics, game, columnManager, bird, screen);
    }
}
