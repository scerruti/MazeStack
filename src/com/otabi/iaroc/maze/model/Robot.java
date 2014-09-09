package com.otabi.iaroc.maze.model;

import com.otabi.iaroc.maze.Navigator;
import com.otabi.iaroc.maze.model.Robot.Turn;

/**
 * Created by Stephen on 5/31/2014.
 */
public class Robot {
	public enum Turn {
		LEFT ('L'), RIGHT ('R'), NO ('N'), U ('U');
		
		private char identifier;
		Turn(char identifier) {
			this.identifier = identifier;
		}
	};

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
    
    public void turnAround() {
        currentOrientation = currentOrientation.turnAround();
		if (navigator != null) {
			navigator.recordMove('U');
		}
   }
    
    public void noTurn() {
		if (navigator != null) {
			navigator.recordMove('N');
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

	public boolean isAtStart() {
        return maze.atStart(currentPosition);
	}

	public boolean isWall(Turn direction) throws MazeNotBuiltException {
		switch (direction) {
		case RIGHT:
			return isWallRight();
		case LEFT:
			return isWallLeft();
		case NO:
			return isWallFront();
		case U:
			return false;
		}
		return false;
	}

	public void turn(Turn direction) {
		switch (direction) {
		case RIGHT:
			turnRight();
			break;
		case LEFT:
			turnLeft();
			break;
		case NO:
			noTurn();
			break;
		case U:
			turnAround();
			break;
		}
	}
}
