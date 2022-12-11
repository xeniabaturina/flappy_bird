package org.example.logic.column_manager;

import org.example.model.Column;
import org.example.model.Screen;

import java.util.ArrayList;


public interface ColumnManager {

    void reset();

    void addColumn(Screen screen);

    void remove();

    void onNextFrame();

    int getColumnWidth();

    int getColumnGap();

    int getSpaceBetweenColumns();

    int getGrassHeight();

    int getSize();

    ArrayList<Column> getColumns();

    void setColumnWidth(int columnWidth);

    void setColumnGap(int columnGap);

    void setSpaceBetweenColumns(int spaceBetweenColumns);
}
