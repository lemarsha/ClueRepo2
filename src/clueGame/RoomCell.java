package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RoomCell extends BoardCell{
	
	public static DoorDirection DoorDirection;
	
	private int row, column;
	private DoorDirection doorDirection;
	private Character symbol;
	private boolean isDoorway;
	private char doorDir;
	
	
	
	public RoomCell(int row, int column, String Symbol) {
		super(row,column,Symbol);
		if (Symbol.length() == 2) {
			if (Symbol.charAt(1)!='N') {
				isDoorway = true;
				doorDir = Symbol.charAt(1);
			}
			else doorDir = Symbol.charAt(1);
		}
		this.row = row;
		this.column = column;
		symbol = Symbol.charAt(0);
	}
	
	public RoomCell() {
		// TODO Auto-generated constructor stub
		super();
	}

	public boolean isWalkway() {
		return false;
	}
	
	public boolean isDoorway() {
		return isDoorway;
	}
	
	public boolean isRoom() {
		return true;
	}

	public clueGame.DoorDirection getDoorDirection() {
		if (doorDir == 'R') {
			doorDirection= DoorDirection.RIGHT;
		}
		if (doorDir == 'U') {
			doorDirection = DoorDirection.UP;
		}
		if (doorDir == 'D') {
			doorDirection = DoorDirection.DOWN;
		}
		if (doorDir == 'L') {
			doorDirection = DoorDirection.LEFT;
		}
		return doorDirection;
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
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(column*ROOM_DIMEN, row*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN);
		
		if(isDoorway) {
			g.setColor(Color.BLUE);
			
			if (doorDir == 'R') {
				g.fillRect(column*ROOM_DIMEN+(4*(ROOM_DIMEN/5)),row*ROOM_DIMEN,ROOM_DIMEN/5,ROOM_DIMEN);
			}
			if (doorDir == 'U') {
				g.fillRect(column*ROOM_DIMEN,row*ROOM_DIMEN,ROOM_DIMEN,ROOM_DIMEN/5);
			}
			if (doorDir == 'D') {
				g.fillRect(column*ROOM_DIMEN,row*ROOM_DIMEN+(4*(ROOM_DIMEN/5)),ROOM_DIMEN,ROOM_DIMEN/5);
			}
			if (doorDir == 'L') {
				g.fillRect(column*ROOM_DIMEN, row*ROOM_DIMEN,ROOM_DIMEN/5,ROOM_DIMEN);
			}
			
			//g.fillRect(x, y, width, height);
		}
		if(doorDir == 'N') {
			g.setColor(Color.BLUE);
			if(symbol == 'C')
				g.drawString("Conservatory", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'S')
				g.drawString("Study", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'B')
				g.drawString("Ballroom", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'R')
				g.drawString("Billiard Room", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'K')
				g.drawString("Kitchen", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'L')
				g.drawString("Library", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'D')
				g.drawString("Dining Room", column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'H')
				g.drawString("Hall",column*ROOM_DIMEN, row*ROOM_DIMEN);
			else if(symbol == 'O')
				g.drawString("Lounge", column*ROOM_DIMEN, row*ROOM_DIMEN);
		}
	}
	

	
	
	
}
