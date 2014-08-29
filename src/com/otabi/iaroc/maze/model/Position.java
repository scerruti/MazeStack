package com.otabi.iaroc.maze.model;

/**
 * Created by Stephen on 5/31/2014.
 */
public class Position {

    private int x, y;

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position() {
        x = 0;
        y = 0;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(Position p) {
        this.x = p.x;
        this.y = p.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInside(int dimensionX, int dimensionY) {
        return isInside(0, 0, dimensionX, dimensionY);
    }

    public boolean isInside(int minX, int minY, int maxX, int maxY) {
        return ((minX <= x) && (x < maxX) && (minY <= y) && (y < maxY));
    }

    public void move(Orientation orientation) {
        switch (orientation) {
            case NORTH:
                moveNorth();
                break;
            case EAST:
                moveEast();
                break;
            case SOUTH:
                moveSouth();
                break;
            case WEST:
                moveWest();
                break;
        }
    }

    public void moveNorth() {
        y += 1;
    }

    public void moveSouth() {
        y -= 1;
    }

    public void moveEast() {
        x += 1;
    }

    public void moveWest() {
        x -= 1;
    }

    public Position neighbor(Orientation orientation) {
        Position neighbor = new Position(this);
        neighbor.move(orientation);
        return neighbor;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public final boolean equals(Object obj2) {
        if (obj2 == this) return true;
        if (!(obj2 instanceof Position)) return false;
        Position that = (Position) obj2;
        return (x == that.getX() && y == that.getY());
    }

}
