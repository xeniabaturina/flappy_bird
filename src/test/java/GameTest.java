import org.example.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    private Game game;


    @BeforeEach
    public void init() {
        game = new Game();
    }

    @Test
    public void gameStarted() {
        assertTrue(game.gameNotPlaying());
        assertFalse(game.gamePlaying());
        game.startNewGame();
        assertTrue(game.gamePlaying());
        assertFalse(game.gameNotPlaying());
    }

    @Test
    public void passColumns() {
        assertEquals(game.getPassedColumns(), 0);
        game.passColumn();
        assertEquals(game.getPassedColumns(), 1);
    }

    @Test
    public void restartGame() {
        game.startNewGame();
        assertFalse(game.startNewGame());
        game.gameOver();
        assertTrue(game.gameNotPlaying());
        assertTrue(game.startNewGame());
    }

}
