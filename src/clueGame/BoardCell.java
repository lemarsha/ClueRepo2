package clueGame;

public abstract class BoardCell {
	
	private int row, column;
	
	/*
	public BoardCell(int row, int column){
		super();
		this.row = row;
		this.column = column;
	}
	*/
	
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
