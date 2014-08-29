package com.otabi.iaroc.maze;


import com.otabi.iaroc.maze.model.Maze;
import com.otabi.iaroc.maze.model.MazeNotBuiltException;

/**
 * Created by Stephen on 6/6/2014.
 */
public class MazeSimulator {
    public static void main(String[] args) {
        Maze testMaze;

        //Run Once
        testMaze = new Maze();
        testMaze.dump();
        try {
            MazeSolver.solve(testMaze);
        } catch (MazeNotBuiltException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Run Multiple Times
        /**
        int totalMoves = 0;
        int mazesSolved = 0;
        for (int i = 0; i < 1000 ; i++) {
            testMaze = new Maze();
            try {
                totalMoves += MazeSolver.solve(testMaze);
                mazesSolved++;

            } catch (MazeNotBuiltException e) {
                System.err.println("A maze must exist before it can be solved.");
            } catch (Exception e) {
                System.err.println("That one was too tough.");
            }
        }

        System.out.println(mazesSolved + " mazes solved in an average of " + totalMoves/mazesSolved + " moves.");
        */
    }
}
