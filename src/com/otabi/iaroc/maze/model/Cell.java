package com.otabi.iaroc.maze.model;

import java.util.EnumMap;

/**
 * Created by Stephen on 5/31/2014.
 */
public class Cell {
    private EnumMap<Orientation, Boolean> walls;

    public Cell() {
        walls = new EnumMap<Orientation, Boolean>(Orientation.class);
        for (Orientation o : Orientation.values()) {
            buildWall(o);
        }
    }

    public boolean hasWall(Orientation orientation) {
        return walls.get(orientation);
    }

    public boolean isIntact() {
        for (Orientation o : Orientation.values()) {
            if (!walls.get(o)) return false;
        }
        return true;
    }

    public void buildWall(Orientation o) {
        walls.put(o, true);
    }

    public void demolishWall(Orientation o) {
        walls.put(o, false);
    }
}
