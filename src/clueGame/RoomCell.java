package clueGame;

public class RoomCell extends BoardCell{
	
	private int row, column;
	private DoorDirection doorDirection;
	private Character symbol;
	private boolean isDoorway;
	private char doorDir;
	
	
	public RoomCell(int row, int column, String Symbol) {
		super(row,column,Symbol);
		if (Symbol.length() == 2) {
			if (Symbol.charAt(1)!='N') {
				isDoorway = true;
				doorDir = Symbol.charAt(1);
			}
			
		}
		
		this.row = row;
		this.column = column;
		symbol = Symbol.charAt(0);
	}
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isDoorway() {
		return isDoorway;
	}
	
	public boolean isRoom() {
		return true;
	}

	public DoorDirection getDoorDirection() {
		if (doorDir == 'R') {
			doorDirection= DoorDirection.RIGHT;
		}
		if (doorDir == 'U') {
			doorDirection = DoorDirection.UP;
		}
		if (doorDir == 'D') {
			doorDirection = DoorDirection.DOWN;
		}
		if (doorDir == 'L') {
			doorDirection = DoorDirection.LEFT;
		}
		return doorDirection;
	}
	
	public Character getInitial() {
		return symbol;
	}
	
	
}
