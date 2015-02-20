package clueGame;

import java.util.*;

import board.BoardCell;

public class Board {
	
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	BoardCell[][] grid;
	Set<BoardCell> targets;
	
	public Board() {
	
	}
	
	public void loadBoardConfig(Map<Character,String> room) {
		
	}

	public Map<Character, String> getRooms() {
		rooms = new HashMap<Character,String>();
		rooms.put('C',"Conservatory");
		return rooms;
	}
	
	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	

}
