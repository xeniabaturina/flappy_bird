package org.example.model;

import java.awt.*;
import java.util.ArrayList;

public class Columns {
    private final ArrayList<Rectangle> columns;

    public Columns(){
        this.columns = new ArrayList<Rectangle>();
    }

    public int getSize(){
        return columns.size();
    }

    public ArrayList<Rectangle> getColumns(){ return columns;};

    public void add(int x, int y, int width, int height){
        columns.add(new Rectangle(x, y, width, height));
    }

    public void remove(){
        columns.remove(0);
    }

}
