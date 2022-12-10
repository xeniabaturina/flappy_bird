package org.example.model;

import java.awt.*;

public class Column {

    private final Rectangle upperColumn;
    private final Rectangle bottomColumn;
    private final int gapSize;

    public Column(Rectangle upperColumn, Rectangle bottomColumn, int gapSize) {
        this.upperColumn = upperColumn;
        this.bottomColumn = bottomColumn;
        this.gapSize = gapSize;
    }

    public void moveLeft(int shift) {
        upperColumn.x -= shift;
        bottomColumn.x -= shift;
    }

    public Rectangle getUpperColumn() {
        return upperColumn;
    }

    public Rectangle getBottomColumn() {
        return bottomColumn;
    }

    public int getGapSize() {
        return gapSize;
    }

    public int getWidth() {
        return upperColumn.width;
    }

    public int getX() {
        return upperColumn.x;
    }

    public int getUpperColumnMinY() {
        return upperColumn.y;
    }

    public int getUpperColumnMaxY() {
        return upperColumn.y + upperColumn.height;
    }

    public int getBottomColumnMinY() {
        return bottomColumn.y;
    }

    public int getBottomColumnMaxY() {
        return bottomColumn.y + bottomColumn.height;
    }
}
