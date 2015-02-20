package clueGame;

public class RoomCell extends BoardCell{
	
	private int row, column;
	private DoorDirection doorDirection;
	private char roomInitial;
	
	@Override
	public boolean isRoom() {
		return true;
	}


}
