package com.otabi.iaroc.maze;

import java.util.Stack;

/*
 * Simple stack based navigator for recording a maze path
 */
public class Navigator {
	private Stack<Character> moves;
	
	public Navigator() {
		moves = new Stack<Character>();
	}
	
	public void recordMove(char move) {
		System.out.print(move);
		System.out.print('-');
		moves.push(move);
	}

	public Character getNextMove() {
		if (moves.isEmpty()) return null;
		
		char nextMove = moves.pop();
		
		if (nextMove == 'R') {
			nextMove = 'L';
		} else if (nextMove == 'L'){
			nextMove = 'R';
		}
		
		return (new Character(nextMove));
	}
	
	public String returnPath() {
		System.out.println();
		StringBuilder path = new StringBuilder();
		Character nextMove;;
		while ((nextMove = getNextMove()) != null) {
			path.append(nextMove);
			System.out.print(nextMove);
			System.out.print('-');
		}

		System.out.println();

		return path.toString();
	}
}
