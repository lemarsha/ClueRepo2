package clueGame;

public class RoomCell extends BoardCell{
	
	public static DoorDirection DoorDirection;
	
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

	public clueGame.DoorDirection getDoorDirection() {
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
	
	public char getInitial() {
		return symbol;
	}
	
	public int getRow() {
		return super.getRow();
	}

	public int getColumn() {
		return super.getColumn();
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
}
