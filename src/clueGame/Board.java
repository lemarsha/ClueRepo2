package clueGame;

import java.util.*;
import java.io.*;

import clueGame.BoardCell;

public class Board {

	private Map<Character,String> rooms;
	private int numRows, numColumns;
	BoardCell[][] grid;
	Set<BoardCell> targets;
	private String legendFile, layoutFile;
	private Set<BoardCell> target_cells;
	private Set<BoardCell> visited ;

	public Board(String layoutFile, String legendFile) {
		this.legendFile = legendFile;
		this.layoutFile = layoutFile;
	}

	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException{
		testBoardConfig();
		testLegend();
		getRooms();
		grid = new BoardCell[numRows][numColumns];
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(layoutFile);
			in = new Scanner(reader);
		}catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		for (int i = 0; i<numRows; ++i) {
			String line = in.nextLine();
			String[] legend = line.split(",");
			for (int j = 0; j<legend.length; ++j) {
				if (legend[j].charAt(0) == 'W') {
					WalkwayCell w = new WalkwayCell(i,j,legend[j]);
					grid[i][j] = w;
				} else if (!rooms.containsKey(legend[j].charAt(0))){
					throw new BadConfigFormatException("Bad room: doesn't exist in legend");
				} else if (legend[j].length() ==2){
					if (legend[j].charAt(1)=='R'||legend[j].charAt(1)=='L'||legend[j].charAt(1)=='U'||legend[j].charAt(1)=='D'||legend[j].charAt(1)=='N') {
						RoomCell r = new RoomCell(i,j,legend[j]);
						grid[i][j] = r;
					}else {
						throw new BadConfigFormatException("Bad door: direction does not exist");
					}
				} else {
					RoomCell r = new RoomCell(i,j,legend[j]);
				    grid[i][j] = r;
				}
			}
		}
	}

	public void testBoardConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = null;
		Scanner in = null;
		try {
			reader = new FileReader(layoutFile);
			in = new Scanner(reader);
		}catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		boolean firstline = true;

		while (in.hasNextLine()) {
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
		}
		in.close();
		//reader.close();
	}
	//this method reads the layout file and creates a map of rooms
	public Map<Character, String> getRooms() {
		rooms = new HashMap<Character,String>();
		FileReader reader = null;
		Scanner in = null;
		try{
			reader = new FileReader(legendFile);
			in = new Scanner(reader);

		}catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] legend = new String[2];
				legend = line.split(", ");
				rooms.put(legend[0].charAt(0),legend[1]);
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
	
	public void testLegend() throws BadConfigFormatException{
		FileReader reader = null;
		Scanner in = null;
		try{
			reader = new FileReader(legendFile);
			in = new Scanner(reader);

		}catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] legend = new String[2];
			legend = line.split(", ");
			if (legend.length>2) {
				throw new BadConfigFormatException("Error in legend format");
			}
		}
		in.close();
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
	
	public BoardCell getCellAt(int row, int col) {
		return grid[row][col];
	}

	public static void main(String[] args) {
		Board b = new Board("ClueLayout.csv","ClueLegend.txt");
		try {
			b.loadBoardConfig();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (b.getRooms().containsKey('Z')) {
			System.out.println("contains z");
		}else {
			System.out.println("hidlajfd");
		}
	}
	
	


}
