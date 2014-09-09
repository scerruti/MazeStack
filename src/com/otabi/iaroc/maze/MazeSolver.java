package com.otabi.iaroc.maze;


import com.otabi.iaroc.maze.model.Maze;
import com.otabi.iaroc.maze.model.MazeNotBuiltException;
import com.otabi.iaroc.maze.model.Orientation;
import com.otabi.iaroc.maze.model.Robot;
import com.otabi.iaroc.maze.model.Robot.Turn;
import com.otabi.iaroc.maze.model.RobotHitsWallException;


/**
 * Created by Stephen on 6/1/2014.
 */
public class MazeSolver {
    private static final int MAXMOVES = 3000;

    public static int solve(Maze maze) throws MazeNotBuiltException, Exception {
    	Navigator	myNavigator = new Navigator();
        Robot myRobot = new Robot(maze, myNavigator);
        int moves = 0;

        while (!myRobot.isAtEnd()) {
            if (moves > MAXMOVES) throw new Exception("Exceeded maximum number of moves allowed.");

            /*
             *  Right hand rule to navigate robot 'myRobot' through the maze
             */
            Turn primaryDirection = Turn.RIGHT;
            Turn alternateDirection = Turn.LEFT;
            if (!myRobot.isWall(primaryDirection)) {
            	myRobot.turn(primaryDirection);
            } else if (!myRobot.isWallFront()) {
            	myRobot.turn(Turn.NO);
            } else {
            	if (!myRobot.isWall(alternateDirection)) {
            		myRobot.turn(alternateDirection);
            	} else {
	            	myRobot.turn(Turn.U);
            	} 
            }
            myRobot.move();
            moves++;
        }

        System.out.println();
        System.out.println("Solved in " + moves + " moves.");
        
        // Navigate back to the beginning of the maze
        String returnPath = myRobot.returnToStart();
        myRobot.turn(Turn.U);
        
        int count = 0;
        for (char a: returnPath.toCharArray()) {
        	// Move first, then turn
        	myRobot.move();

        	switch (a) {
        	case 'L':
        		myRobot.turn(Turn.LEFT);
        		break;
        	case 'R':
        		myRobot.turn(Turn.RIGHT);
        		break;
        	case 'U':
        		myRobot.turn(Turn.U);
        		break;
        	}
        	count++;
        }
        if (myRobot.isAtStart()) {
            System.out.println("Return to start in " + count + " moves.");
        } else {
        	System.out.println("Return path failed!");
        }
        return (moves);
    }
}
