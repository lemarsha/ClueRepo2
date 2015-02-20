package clueGame;

public abstract class BoardCell {
	
	private int row, column;
	
	public boolean isWalkway(){
		return false;
	}
	public boolean isRoom() {
		return false;
	}
	public boolean isDooray() {
		return false;
	}
	//abstract void draw();
	
	

}
