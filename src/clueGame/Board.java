package clueGame;

import java.util.*;

import board.BoardCell;

public class Board {
	
	private Map<Character,String> rooms;
	private int numRows, numColumns;
	RoomCell[][] grid;
	Set<BoardCell> targets;
	
	public Board() {
		for (int i = 0; i<numRows; ++i) {
			for (int j = 0; j<numColumns; ++j) {
				grid[i][j] = new RoomCell(i,j);
			}
		}
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
	
	public RoomCell getRoomCellAt(int row, int col) {
		RoomCell rm = new RoomCell(row, col);
		return rm;
	}
	

}
