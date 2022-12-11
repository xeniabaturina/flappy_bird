package logic;

import org.example.logic.ColumnManager;
import org.example.logic.Painter;
import org.example.logic.Renderer;
import org.example.model.Bird;
import org.example.model.Game;
import org.example.model.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.mockito.Mockito.*;

public class RendererTest {
    private final Game gameMock = mock(Game.class);
    private final ColumnManager columnManagerMock = mock(ColumnManager.class);
    private final Bird birdMock = mock(Bird.class);
    private final Screen screenMock = mock(Screen.class);
    private final Painter painterMock = mock(Painter.class);
    private final Graphics graphicsMock = mock(Graphics.class);
    private Renderer renderer;

    @BeforeEach
    void init() {
        renderer = new Renderer(gameMock, columnManagerMock, birdMock, screenMock, painterMock);
    }

    @Test
    void paintCalledTest() {
        doNothing().when(painterMock).paint(graphicsMock, gameMock, columnManagerMock, birdMock, screenMock);

        renderer.paintComponent(graphicsMock);

        verify(painterMock, times(1))
                .paint(graphicsMock, gameMock, columnManagerMock, birdMock, screenMock);
    }
}
