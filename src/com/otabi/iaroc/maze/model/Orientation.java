package com.otabi.iaroc.maze.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stephen on 5/31/2014.
 */
public enum Orientation {
    NORTH(0, "North"),
    EAST(90, "East"),
    SOUTH(180, "South"),
    WEST(270, "West");

    private int degrees;
    private String label;

    private static Map<Integer, Orientation> degreesToOrientationMapping;

    private Orientation(int degrees, String label) {
        this.degrees = degrees;
        this.label = label;
    }

    private int getDegrees() {
        return this.degrees;
    }

    private static Orientation fromDegrees(int degrees) {
        if (degreesToOrientationMapping == null) {
            initMapping();
        }
        return degreesToOrientationMapping.get(degrees);
    }

    private static void initMapping() {
        degreesToOrientationMapping = new HashMap<Integer, Orientation>();
        for (Orientation o : Orientation.values())
            degreesToOrientationMapping.put(o.degrees, o);
    }

    public Orientation turnRight() {
        return Orientation.fromDegrees((degrees + 90) % 360);
    }

    public Orientation turnLeft() {
        return Orientation.fromDegrees((degrees + 270) % 360);
    }

    public Orientation turnAround() {
        return Orientation.fromDegrees((degrees + 180) % 360);
    }

}
