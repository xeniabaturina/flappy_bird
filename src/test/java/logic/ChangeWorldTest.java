package logic;

import org.example.logic.ChangeWorld;
import org.example.logic.column_manager.StaticColumnManager;
import org.example.model.Bird;
import org.example.model.Game;
import org.example.model.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChangeWorldTest {

    private ChangeWorld changeWorld;
    private Game game;
    private StaticColumnManager columnManager;
    private Bird bird;
    private Screen screen;

    @BeforeEach
    void init() {
        changeWorld = new ChangeWorld(10);
        game = new Game();
        columnManager = new StaticColumnManager();
        bird = new Bird();
        screen = new Screen(500, 500);
    }

    @Test
    void stressTest() {
        for (int i = 0; i < 50; i++) {
            changeWorld.nextFrame(game, columnManager, bird, screen);
        }
        assertTrue(game.gameNotPlaying());
    }
}
