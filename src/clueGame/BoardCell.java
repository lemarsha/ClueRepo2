package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public abstract class BoardCell extends JPanel{
	
	private int row, column;
	private DoorDirection doorDirection;
	private Character symbol;
	private boolean isDoorway;
	private char doorDir;
	protected final static int ROOM_DIMEN = 25;
	
	public int getRoomDim() {
		return ROOM_DIMEN;
	}
	
	public boolean containsClick(int mouseX, int mouseY) {
		Rectangle rect = new Rectangle(getColumn()*ROOM_DIMEN,getRow()*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN);
		if (rect.contains(new Point(mouseX,mouseY))) {
			return true;
		}
		return false;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public BoardCell(int row, int column, String Symbol){
		super();
		this.row = row;
		this.column = column;
		Character x = Symbol.charAt(0);
		this.symbol = x;
	}
	
	public BoardCell() {
		// TODO Auto-generated constructor stub
	}

	abstract public boolean isWalkway();
	abstract public boolean isRoom();
	abstract public boolean isDoorway();
	abstract public DoorDirection getDoorDirection();
	abstract public char getInitial();
	//abstract void draw();
	
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public abstract void draw(Graphics g, Board board);

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoardCell other = (BoardCell) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	public void drawTargets(Graphics g, Board b) {
		g.setColor(Color.PINK);
		g.fillRect(column*ROOM_DIMEN, row*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN);
		g.setColor(Color.BLACK);
		g.drawRect(column*ROOM_DIMEN,row*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN);
	}
	
	

}