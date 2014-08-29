package com.otabi.iaroc.maze.model;

import java.util.*;

/**
 * Created by Stephen on 5/31/2014.
 */
public class Maze {
    private int dimensionX;
    private int dimensionY;

    private Map<Position, Cell> cells;

    public Position getStartPostion() {
        return startPostion;
    }

    public void setStartPostion(Position startPostion) {
        this.startPostion = startPostion;
    }

    private Position startPostion;
    private Position endPosition;

    public Maze() {
        this.dimensionX = 4;
        this.dimensionY = 4;
        this.create(dimensionX, dimensionY);
        startPostion = new Position(0, 0);
        endPosition = new Position(3, 3);
    }

    public boolean isWall(Position position, Orientation orientation) throws MazeNotBuiltException {
        if (cells == null) throw new MazeNotBuiltException();
        Cell cell = cells.get(position);
        return cell.hasWall(orientation);
    }

    private void create(int dimensionX, int dimensionY) {
        Random r = new Random();
        Map<Position, Boolean> processed = new HashMap<Position, Boolean>(dimensionX * dimensionY);

        // Initialize maze with all walls
        cells = new HashMap<Position, Cell>(dimensionX * dimensionY);
        for (int row = 0; row < dimensionY; row++) {
            for (int col = 0; col < dimensionX; col++) {
                Position pos = new Position(col, row);
                cells.put(pos, new Cell());
            }
        }

        int posX = r.nextInt(dimensionX);
        int posY = r.nextInt(dimensionY);
        Position curPos = new Position(posX, posY);

        Deque<Position> stack = new ArrayDeque<Position>();
        stack.push(curPos);

        while (stack.size() > 0) {
            Vector<Orientation> intactNeighbors = new Vector<Orientation>();
            for (Orientation o : Orientation.values()) {
                Position neighbor = curPos.neighbor(o);
                if (neighbor.isInside(dimensionX, dimensionY)) {
                    Cell neighborCell = cells.get(neighbor);
                    if (neighborCell != null && neighborCell.isIntact()) {
                        intactNeighbors.add(o);
                    }
                }
            }

            if (intactNeighbors.size() > 0) {
                Orientation neighborOrientation = intactNeighbors.get(r.nextInt(intactNeighbors.size()));
                cells.get(curPos).demolishWall(neighborOrientation);
                Position temp = curPos.neighbor(neighborOrientation);
                cells.get(temp).demolishWall(neighborOrientation.turnAround());
                stack.push(curPos);
                curPos = temp;
            } else {
                curPos = stack.pop();
            }
        }
    }

    public void setStart(Position start) {
        this.startPostion = start;
    }

    public void setEndPosition(Position end) {
        this.endPosition = end;
    }

    public boolean atEnd(Position currentPosition) {
        return currentPosition.equals(endPosition);
    }

    public void dump() {
        StringBuilder sb = new StringBuilder();

        for (int x = 0; x < dimensionX; x++) {
            sb.append(" __");
        }
        sb.append("\n");
        for (int y = dimensionY - 1; y >= 0; y--) {
            for (int x = 0; x < dimensionX; x++) {
                Position pos = new Position(x, y);
                Cell c = cells.get(pos);

                if (c.hasWall(Orientation.WEST))
                    sb.append("|");
                else
                    sb.append(" ");

                if (c.hasWall(Orientation.SOUTH)) {
                    if (pos.equals(startPostion)) {
                        sb.append('S');
                        sb.append('_');
                    } else if (pos.equals(endPosition)) {
                        sb.append('_');
                        sb.append('E');
                    } else {
                        sb.append("__");
                    }
                } else {
                    if (pos.equals(startPostion)) {
                        sb.append('S');
                        sb.append(' ');
                    } else if (pos.equals(endPosition)) {
                        sb.append(' ');
                        sb.append('E');
                    } else {
                        sb.append("  ");
                    }
                }
            }
            sb.append("|\n");
        }
        System.out.println(sb.toString());
    }
}
