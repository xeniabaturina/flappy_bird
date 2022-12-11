package org.example.logic.column_manager;

import org.example.model.Column;
import org.example.model.Screen;

import java.util.ArrayList;


public interface ColumnManager {

    public void reset();

    public void addColumn(Screen screen);

    public void remove();

    public void onNextFrame();

    public int getColumnWidth();

    public int getColumnGap();

    public int getSpaceBetweenColumns();

    public int getGrassHeight();

    public int getSize();

    public ArrayList<Column> getColumns();

    public void setColumnWidth(int columnWidth);

    public void setColumnGap(int columnGap);

    public void setSpaceBetweenColumns(int spaceBetweenColumns);
}