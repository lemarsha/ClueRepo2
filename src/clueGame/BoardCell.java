package clueGame;

public abstract class BoardCell {
	
	private int row, column;
	private DoorDirection doorDirection;
	private Character symbol;
	private boolean isDoorway;
	private char doorDir;
	
	public BoardCell(int row, int column, String Symbol){
		super();
		this.row = row;
		this.column = column;
		Character x = Symbol.charAt(0);
		this.symbol = x;
	}
	abstract public boolean isWalkway();
	abstract public boolean isRoom();
	abstract public boolean isDoorway();
	abstract public DoorDirection getDoorDirection();
	abstract public Character getSymbol();
	//abstract void draw();

}