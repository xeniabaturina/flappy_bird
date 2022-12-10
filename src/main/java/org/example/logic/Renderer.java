package org.example.logic;

import org.example.model.Bird;
import org.example.model.Columns;
import org.example.model.Game;
import org.example.model.Screen;

import java.awt.Graphics;
import java.io.Serial;

import javax.swing.JPanel;

public class Renderer extends JPanel
{
    private Game game;
    private Columns columns;
    private final Bird bird;
    private final Screen screen;

    public Renderer(Game game, Columns columns, Bird bird, Screen screen) {
        this.game = game;
        this.columns = columns;
        this.bird = bird;
        this.screen = screen;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setColumns(Columns columns) {
        this.columns = columns;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        Painter painter = new Painter();
        painter.paint(graphics, game, columns, bird, screen);
    }

}
