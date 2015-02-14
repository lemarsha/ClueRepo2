package board;
import java.util.*;

public class IntBoard {

	private int rows,columns;
	private Map<BoardCell,LinkedList<BoardCell>> adjacent_cells;
	private Set<BoardCell> target_cells;
	private Set<BoardCell> visited;

	
	
	IntBoard(int rows, int columns) {
		super();
		this.rows = rows;
		this.columns = columns;
	}

	public LinkedList<BoardCell> getAdjList(BoardCell bc) {
		/*
		LinkedList<BoardCell> adjacencies = new LinkedList<BoardCell>();
		BoardCell b;
		if (bc.getColumn()-1>=0) {
			adjacencies.add(b = new BoardCell(bc.getRow(), bc.getColumn()-1));
		}
		if (bc.getColumn()+1>=0) {
			adjacencies.add(b = new BoardCell(bc.getRow(), bc.getColumn()+1));
		}
		if (bc.getRow()-1>=0) {
			adjacencies.add(b = new BoardCell(bc.getRow()-1, bc.getColumn()));
		}
		if (bc.getRow()+1>=0) {
			adjacencies.add(b = new BoardCell(bc.getRow()+1, bc.getColumn()));
		}
		*/
		//return adjacencies;
		return null;
	}
	
	public void calcAdjacencies(int rows,int columns) {
		/*
		adjacent_cells = new HashMap<BoardCell,LinkedList<BoardCell>>();
		for (int i = 0; i<rows; ++i) {
			for (int j = 0; j<columns; ++j) {
				LinkedList<BoardCell> adj = new LinkedList<BoardCell>();
				BoardCell b = new BoardCell(i,j);
				adj=getAdjList(b);
				
			}
		}
		*/
	}
	
	public void calcTargets(BoardCell thisCell, int numSteps){
/*		
		LinkedList<BoardCell> current_adj_cells = new LinkedList<BoardCell>();
		if (numSteps == 1) {
			current_adj_cells = adjacent_cells.get(thisCell);
			for (BoardCell b: current_adj_cells) {
				if (!visited.contains(b)) {
					target_cells.add(b);
				}
			}
			return;
		}
		current_adj_cells = adjacent_cells.get(thisCell);
		for (BoardCell b: current_adj_cells) {
			calcTargets(b,numSteps-1);
		}
*/
		
	}
	
	public Set<BoardCell> getTargets() {
		//return target_cells;
		return null;
	}
	
	public BoardCell getCell(int row, int col) {
		BoardCell b = new BoardCell(row,col);
		return b;
	}

}
