package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

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

	@Override
	public void draw(Graphics g, Board board) {
		// TODO Auto-generated method stub
		//super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillRect(column*ROOM_DIMEN, row*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN);
		g.setColor(Color.BLACK);
		g.drawRect(column*ROOM_DIMEN,row*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN);
	}
}
