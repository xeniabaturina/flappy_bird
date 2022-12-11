package org.example.logic;

import org.example.model.Column;
import org.example.model.Screen;

import javax.inject.Inject;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static org.example.common.Config.*;


public class ColumnManager {

    private int columnWidth = INITIAL_COLUMN_WIDTH;
    private int columnGap = INITIAL_COLUMN_GAP;
    private int spaceBetweenColumns = INITIAL_SPACE_BTW_COLUMNS;

    private final int grassHeight = GRASS_HEIGHT;
    private final ArrayList<Column> columns;
    private final Random random;

    public @Inject ColumnManager() {
        this.columns = new ArrayList<>();
        this.random = new Random();
    }

    public void reset() {
        columns.clear();
        columnGap = INITIAL_COLUMN_GAP;
    }

    public void addColumn(Screen screen) {
        int height = screen.getHeight() - grassHeight;
        int heightWithoutGap = (height - columnGap);
        int indent = heightWithoutGap / 10;
        int gapHeight = indent + random.nextInt(heightWithoutGap - indent);

        int columnX;
        if (columns.size() == 0) {
            columnX = screen.getWidth() + columnWidth;
        } else {
            int firstColumnX = columns.get(columns.size() - 1).getX();
            columnX = firstColumnX + spaceBetweenColumns;
        }

        int bottomColumnY = gapHeight + columnGap;

        Rectangle upperColumn = new Rectangle(
                columnX, 0, columnWidth, gapHeight);
        Rectangle bottomColumn = new Rectangle(
                columnX, bottomColumnY, columnWidth, height - bottomColumnY);

        columns.add(new Column(upperColumn, bottomColumn, columnGap));
    }

    public void remove() {
        columns.remove(0);
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public int getColumnGap() {
        return columnGap;
    }

    public int getSpaceBetweenColumns() {
        return spaceBetweenColumns;
    }

    public int getGrassHeight() {
        return grassHeight;
    }

    public int getSize() {
        return columns.size();
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public void setColumnGap(int columnGap) {
        this.columnGap = columnGap;
    }

    public void setSpaceBetweenColumns(int spaceBetweenColumns) {
        this.spaceBetweenColumns = spaceBetweenColumns;
    }
}
