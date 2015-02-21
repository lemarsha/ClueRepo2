package clueGame;

import java.util.*;
import java.io.*;

public class Board {

	private Map<Character,String> rooms;
	private int numRows, numColumns;
	BoardCell[][] grid;
	Set<BoardCell> targets;
	private String legendFile, layoutFile;

	public Board(String layoutFile, String legendFile) {
		this.legendFile = legendFile;
		this.layoutFile = layoutFile;
		/*
		for (int i = 0; i<numRows; ++i) {
			for (int j = 0; j<numColumns; ++j) {
				grid[i][j] = new BoardCell(i,j);
			}
		}
		*/
	}

	public void loadBoardConfig() throws Exception{
		testBoardConfig();
		grid = new BoardCell[numRows][numColumns];
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(layoutFile);
			in = new Scanner(reader);
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		for (int i = 0; i<numRows; ++i) {
			String line = in.nextLine();
			String[] legend = line.split(",");
			for (int j = 0; j<legend.length; ++j) {
				if (legend[j].charAt(0) == 'P') {
					WalkwayCell w = new WalkwayCell(i,j,legend[j]);
					grid[i][j] = w;
				} else {
					RoomCell r = new RoomCell(i,j,legend[j]);
					grid[i][j] = r;
				}
			}
		}
	}

	public void testBoardConfig() throws Exception {
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(layoutFile);
			in = new Scanner(reader);
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		boolean firstline = true;
		while (in.hasNextLine()) {
			try {
				String line = in.nextLine();
				numRows++;
				String[] legend = line.split(",");
				if (firstline == true) {
					numColumns = legend.length;
					firstline = false;
				}
				if (legend.length!= numColumns) {
					throw new BadConfigFormatException("Not consistend columns");
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		}
		in.close();
		reader.close();
	}
	//this method reads the layout file and creates a map of rooms
	public Map<Character, String> getRooms() {
		rooms = new HashMap<Character,String>();
		FileReader reader = null;
		Scanner in = null;
		try{
			reader = new FileReader(legendFile);
			in = new Scanner(reader);

		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		while (in.hasNextLine()) {
			try{
				String line = in.nextLine();
				String[] legend = new String[2];
				legend = line.split(", ");
				rooms.put(legend[0].charAt(0),legend[1]);
			}catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}

		}
		in.close();
		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public RoomCell getRoomCellAt(int row, int col) {
		BoardCell cell = grid[row][col];
		if (cell.isRoom()) {
			return (RoomCell) cell;
		}
		return null;
	}
	
	public static void main(String[] args) {
		Board b = new Board("ClueLayout.csv","ClueLegend.txt");
		try {
			b.loadBoardConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomCell room = b.getRoomCellAt(31,9);
		
	}
	
	
}
