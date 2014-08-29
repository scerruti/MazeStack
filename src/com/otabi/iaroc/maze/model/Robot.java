package com.otabi.iaroc.maze.model;

import com.otabi.iaroc.maze.Navigator;

/**
 * Created by Stephen on 5/31/2014.
 */
public class Robot {
    private Orientation currentOrientation = Orientation.EAST;
    private Position currentPosition = null;
    private Maze maze;
    private Navigator navigator;

    public Robot() {
        this(new Maze(), null);
    }

    public Robot(Maze maze) {
    	this(maze, null);
    }
    
    public Robot(Maze maze, Navigator navigator) {
        this.maze = maze;
        this.currentPosition = maze.getStartPostion();
        this.navigator = navigator;
    }
    

    public void move() throws RobotHitsWallException, MazeNotBuiltException {
        //System.out.println("@"+currentPosition+" Move "+currentOrientation);
        if (maze.isWall(currentPosition, currentOrientation)) {
            throw new RobotHitsWallException();
        }
        currentPosition.move(currentOrientation);
		if (navigator != null) {
			navigator.recordMove('F');
		}
    }

    public boolean isWallLeft() throws MazeNotBuiltException {
        return maze.isWall(currentPosition, currentOrientation.turnLeft());
    }

    public boolean isWallRight() throws MazeNotBuiltException {
        return maze.isWall(currentPosition, currentOrientation.turnRight());
    }

    public boolean isWallFront() throws MazeNotBuiltException {
        return maze.isWall(currentPosition, currentOrientation);
    }

    public void turnRight() {
        currentOrientation = currentOrientation.turnRight();
		if (navigator != null) {
			navigator.recordMove('R');
		}
    }

    public void turnLeft() {
        currentOrientation = currentOrientation.turnLeft();
		if (navigator != null) {
			navigator.recordMove('L');
		}
   }
    
    public boolean isAtEnd() {
        return maze.atEnd(currentPosition);
    }
	
	public String returnToStart() {
		String path = "";
		if (navigator != null) {
			path = navigator.returnPath();
		} else {
			System.err.println("You must have a navigator for this function.");
		}
		return path;
	}
}
