package clueGame;

public class WalkwayCell extends BoardCell{
	
	private int row, column;
	private DoorDirection doorDirection;
	private Character symbol;
	private boolean isDoorway;
	private char doorDir;
	
	public WalkwayCell(int row, int column, String Symbol) {
		super(row,column,Symbol);
		this.row = row;
		this.column = column;
		symbol = Symbol.charAt(0);

	}
	
	@Override
	public boolean isWalkway() {
		return true;
	}
	
	@Override
	public boolean isDoorway() {
		return false;
	}
	@Override
	public boolean isRoom() {
		return false;
	}
	
	@Override
	public DoorDirection getDoorDirection() {
		return null;
	}
	
	public Character getInitial() {
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
