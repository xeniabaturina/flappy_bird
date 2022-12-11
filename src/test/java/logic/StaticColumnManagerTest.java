package logic;

import org.example.logic.column_manager.ColumnManager;
import org.example.logic.column_manager.StaticColumnManager;
import org.example.model.Column;
import org.example.model.Screen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StaticColumnManagerTest {

    public ColumnManager columnManager;

    private final int DEFAULT_WIDTH = 100;
    private final int DEFAULT_SCREEN_SIZE = 500;
    private final int DUMMY_INT = 1;

    @BeforeEach
    public void init() {
        Screen screenMock = mock(Screen.class);
        when(screenMock.getWidth()).thenReturn(DEFAULT_SCREEN_SIZE);
        when(screenMock.getHeight()).thenReturn(DEFAULT_SCREEN_SIZE);

        columnManager = new StaticColumnManager(mock(Screen.class));
    }

    @Test
    public void addColumnTest() {
        assertEquals(columnManager.getColumns().size(), 0);


        columnManager.addColumn();

        ArrayList<Column> addedColumns = columnManager.getColumns();

        assertEquals(addedColumns.size(), 1);

        Column adddedColumn = addedColumns.get(0);

        int expectedX = DEFAULT_SCREEN_SIZE + DEFAULT_WIDTH;

        assertEquals(adddedColumn.getUpperColumn().x, expectedX);
        assertEquals(adddedColumn.getBottomColumn().x, expectedX);
    }
}
