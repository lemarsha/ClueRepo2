package clueGame;

public class RoomCell extends BoardCell{
	
	private int row, column;
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public RoomCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	@Override
	public boolean isRoom() {
		return true;
	}
	
	public boolean isDoorway() {
		return false;
	}

	public DoorDirection getDoorDirection() {
		return DoorDirection.RIGHT;
	}
	
	

	


}
