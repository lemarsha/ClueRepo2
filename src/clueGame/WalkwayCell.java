package clueGame;

public class WalkwayCell extends BoardCell{
	
	private int row, column;
	
	@Override
	public boolean isWalkway() {
		return true;
	}
	

}
