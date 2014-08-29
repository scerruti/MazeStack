package com.otabi.iaroc.maze;


import com.otabi.iaroc.maze.model.Maze;
import com.otabi.iaroc.maze.model.MazeNotBuiltException;
import com.otabi.iaroc.maze.model.Robot;
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
            if (!myRobot.isWallRight()) {
            	myRobot.turnRight();
            } else if (myRobot.isWallFront()) {
            	myRobot.turnLeft();
            	if (myRobot.isWallFront()) {
            		myRobot.turnLeft();
            		// Hint: Two left turns makes a U-Turn 
            	} 
            }
            myRobot.move();
            moves++;
        }

        // Navigate back to the beginning of the maze
        
        String returnPath = myRobot.returnToStart();
        System.out.println("Solved in " + moves + " moves.");
        int count = 0;
        for (char a: returnPath.toCharArray()) {
        	if (a == 'F') count++;
        }
        System.out.println("Return path is " + count + " moves.");
        return (moves);
    }
}
