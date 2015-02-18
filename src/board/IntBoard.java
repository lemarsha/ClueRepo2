package board;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;


public class IntBoard {

	private int rows,columns;
	private Map<BoardCell,LinkedList<BoardCell>> adjacent_cells;
	private Set<BoardCell> target_cells;
	private Set<BoardCell> visited ;
	private BoardCell[][] grid;
	
	
	
	public IntBoard(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
		grid = new BoardCell[rows][columns];
		for( int row = 0; row < this.rows; row++){
			for( int col = 0; col < this.columns; col++){
				grid[row][col] = new BoardCell(row,col);
			}
		}

	}

	public LinkedList<BoardCell> getAdjList(BoardCell bc) {
		
		LinkedList<BoardCell> adjacencies = new LinkedList<BoardCell>();
		if (bc.getColumn()-1>=0) {
			adjacencies.add(grid[ bc.getRow()][ bc.getColumn()-1]);
		}
		if (bc.getColumn()+1<this.columns) {
			adjacencies.add(grid[bc.getRow()][ bc.getColumn()+1]);
		}
		if (bc.getRow()-1>=0) {
			adjacencies.add(grid[bc.getRow()-1][ bc.getColumn()]);
		}
		if (bc.getRow()+1<this.rows) {
			adjacencies.add(grid[bc.getRow()+1][ bc.getColumn()]);
		}
		
		return adjacencies;
	}
	
	public void calcAdjacencies() {
		
		adjacent_cells = new HashMap<BoardCell,LinkedList<BoardCell>>();
		for (int i = 0; i<rows; ++i) {
			for (int j = 0; j<columns; ++j) {
				LinkedList<BoardCell> adj = new LinkedList<BoardCell>();
				adj=getAdjList(grid[i][j]);
				adjacent_cells.put(grid[i][j], adj);
			}
		}
		
	}
	
	public void calcTargets(BoardCell thisCell, int numSteps){
		visited = new HashSet<BoardCell>();
		target_cells = new HashSet<BoardCell>();
		visited.add(thisCell);
		findAllTargets(thisCell, numSteps);
	}
	
	public void findAllTargets(BoardCell thisCell, int numSteps) {
		LinkedList<BoardCell> current_adj_cells = new LinkedList<BoardCell>();
		current_adj_cells = adjacent_cells.get(grid[thisCell.getRow()][thisCell.getColumn()]);
		for (BoardCell b: current_adj_cells){
			if(!visited.contains(b)){
		visited.add(b);
		if( numSteps == 1){
			target_cells.add(b);
		}else {
			findAllTargets(b, numSteps-1);
		}
		visited.remove(b);
			}
		
		}
		
		/*
		if (numSteps == 1) {
			current_adj_cells = adjacent_cells.get(grid[thisCell.getRow()][thisCell.getColumn()]);
			for (BoardCell b: current_adj_cells) {
					target_cells.add(b);
			}
			return;
		}

		for (BoardCell b: current_adj_cells) {
			
			calcTargets(b,numSteps-1);
		}
		*/

	}
	public Set<BoardCell> getTargets(){
		return target_cells;
	}
	
	
	public BoardCell getCell(int row, int col) {
		return grid[row][col];
	}
	
	public static void main (String args[]){
		IntBoard board = new IntBoard(4,4);
	
		BoardCell cell = board.getCell(0, 0);
		board.calcAdjacencies();
		board.calcTargets(cell, 3);
		Set targets = board.getTargets();
		System.out.println(targets.contains(board.getCell(3, 0)));
		System.out.println(targets.contains(board.getCell(2, 1)));
		System.out.println(targets.contains(board.getCell(0, 1)));
		System.out.println(targets.contains(board.getCell(1, 2)));
		System.out.println(targets.contains(board.getCell(0, 3)));
		System.out.println(targets.contains(board.getCell(1, 0)));
		

		
	}

}
