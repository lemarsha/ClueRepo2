package clueGame;

import java.util.*;
import java.awt.Graphics;
import java.io.*;

import javax.swing.JPanel;

import clueGame.BoardCell;

public class Board extends JPanel{

	private Map<Character,String> rooms;
	private int numRows, numColumns;
	private BoardCell[][] grid;
	private Set<BoardCell> targets;
	private String legendFile, layoutFile;
	private Map<BoardCell,LinkedList<BoardCell>> adjacent_cells;
	private Set<BoardCell> target_cells;
	private Set<BoardCell> visited;
	private ArrayList<Player> players;

	//constructor
	public Board(String layoutFile, String legendFile) {
		this.legendFile = legendFile;
		this.layoutFile = layoutFile;
	}

	//generates the map of the legend, tests for correct formats, and creates a grid of all the board cells
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException{
		testBoardConfig();
		testLegend();
		getRooms();
		grid = new BoardCell[numRows][numColumns];
		FileReader reader = null;
		Scanner in = null;
		WalkwayCell w = null;
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
					w = new WalkwayCell(i,j,legend[j]);
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

	//tests the format of the board file
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
				throw new BadConfigFormatException("Not consistent columns");
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

	//tests the format of the legend file
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

	//gets number of rows
	public int getNumRows() {
		return numRows;
	}

	//gets number of columns
	public int getNumColumns() {
		return numColumns;
	}
	
	//gets the room cell at the specified grid location
		public RoomCell getRoomCellAt(int row, int col) {
			BoardCell cell = grid[row][col];
			if (cell.isRoom()) {
				return (RoomCell) cell;
			}
			return null;
		}
	
	//returns the board cell at the specified grid location
	public BoardCell getCellAt(int row, int col) {
		return grid[row][col];
	}

	//creates a list of adjacent cells
	public LinkedList<BoardCell> getAdjList(int row, int col) {
		LinkedList<BoardCell> adjacencies = new LinkedList<BoardCell>();
		if (!grid[row][col].isDoorway() && grid[row][col].isRoom()) {
			return adjacencies;
		}
		if (col-1>=0) {
			if (grid[row][col].getInitial() == grid[row][col-1].getInitial() && grid[row][col].isWalkway()) {
				adjacencies.add(grid[row][col-1]);
			} else if (grid[row][col-1].isDoorway()&&grid[row][col-1].getDoorDirection()==DoorDirection.RIGHT) {
				adjacencies.add(grid[row][col-1]);
			} else if (grid[row][col].isDoorway()&&grid[row][col].getDoorDirection() ==DoorDirection.LEFT) {
				adjacencies.add(grid[row][col-1]);
			}
		}
		if (col+1<numColumns) {
			if (grid[row][col].getInitial() == grid[row][col+1].getInitial()&& grid[row][col].isWalkway()) {
				adjacencies.add(grid[row][col+1]);
			} else if (grid[row][col+1].isDoorway()&&grid[row][col+1].getDoorDirection() ==DoorDirection.LEFT) {
				adjacencies.add(grid[row][col+1]);
			} else if (grid[row][col].isDoorway()&&grid[row][col].getDoorDirection() ==DoorDirection.RIGHT) {
				adjacencies.add(grid[row][col+1]);
			}
		}
		if (row-1>=0) {
			if (grid[row][col].getInitial() == grid[row-1][col].getInitial()&& grid[row][col].isWalkway()) {
				adjacencies.add(grid[row-1][col]);
			} else if (grid[row-1][col].isDoorway()&&grid[row-1][col].getDoorDirection() ==DoorDirection.DOWN) {
				adjacencies.add(grid[row-1][col]);
			} else if (grid[row][col].isDoorway()&&grid[row][col].getDoorDirection() ==DoorDirection.UP) {
				adjacencies.add(grid[row-1][col]);
			}
		}
		if (row+1<numRows) {
			if (grid[row][col].getInitial() == grid[row+1][col].getInitial()&& grid[row][col].isWalkway()) {
				adjacencies.add(grid[row+1][col]);
			} else if (grid[row+1][col].isDoorway()&&grid[row+1][col].getDoorDirection() ==DoorDirection.UP) {
				adjacencies.add(grid[row+1][col]);
			} else if (grid[row][col].isDoorway()&&grid[row][col].getDoorDirection() ==DoorDirection.DOWN) {
				adjacencies.add(grid[row+1][col]);
			}
		}
		if (adjacencies.contains(grid[row][col])) {
			adjacencies.remove(grid[row][col]);
		}
		return adjacencies;
	}

	//creates hash map of all the adjacent cells associated with a board cell
	public void calcAdjacencies() {
		adjacent_cells = new HashMap<BoardCell,LinkedList<BoardCell>>();
		for (int i = 0; i<numRows; ++i) {
			for (int j = 0; j<numColumns; ++j) {
				LinkedList<BoardCell> adj = new LinkedList<BoardCell>();
				adj=getAdjList(i,j);
				adjacent_cells.put(grid[i][j], adj);
			}
		}
	}

	//calculate the targets
	public void calcTargets(int row, int col, int numSteps){
		visited = new HashSet<BoardCell>();
		target_cells = new HashSet<BoardCell>();
		visited.add(grid[row][col]);
		findAllTargets(grid[row][col], numSteps);
	}
	
	//find all the targets a roll can move you too
	public void findAllTargets(BoardCell thisCell, int numSteps) {
		LinkedList<BoardCell> current_adj_cells = new LinkedList<BoardCell>();
		current_adj_cells = adjacent_cells.get(grid[thisCell.getRow()][thisCell.getColumn()]);
		for (BoardCell b: current_adj_cells){
			if(!visited.contains(b)){
				visited.add(b);
				if( numSteps == 1 || b.isDoorway()){
					target_cells.add(b);
				}else {
					findAllTargets(b, numSteps-1);
				}
				visited.remove(b);
			}
		}
	}
	
	public Set<BoardCell> getTargets(){
		return target_cells;
	}
	
	public void setBoardPlayers( ArrayList<Player> players) {
		this.players = players;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < numRows; i++){
			for(int j=0; j< numColumns; j++) {
				grid[i][j].draw(g, this);
			}
		}
		for(Player p : players){
			p.draw(g,this);
		}
		
	}
}

