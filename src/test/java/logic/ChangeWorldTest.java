package logic;

import org.example.logic.ChangeWorld;
import org.example.logic.column_manager.StaticColumnManager;
import org.example.model.Bird;
import org.example.model.Game;
import org.example.model.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class ChangeWorldTest {

    private ChangeWorld changeWorld;
    private Game game;
    private StaticColumnManager columnManager;
    private Bird bird;
    private Screen screen;

    @BeforeEach
    void init() {
        game = new Game();
        columnManager = new StaticColumnManager(mock(Screen.class));
        bird = new Bird();
        screen = new Screen(500, 500);

        changeWorld = ChangeWorld
                .Builder
                .newInstance(game, columnManager, bird, screen)
                .setSpeed(10)
                .build();
    }

    @Test
    void stressTest() {
        for (int i = 0; i < 50; i++) {
            changeWorld.nextFrame();
        }
        assertTrue(game.gameNotPlaying());
    }
}
