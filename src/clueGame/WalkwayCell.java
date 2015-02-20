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
	
	public Character getSymbol() {
		return symbol;
	}
	

}
