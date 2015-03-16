package clueGame;

import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private RoomCell lastRoom = new RoomCell(9,7,"R");

	public ComputerPlayer(String name, String color, String x, String y) {
		super(name, color, x,y);
		// TODO Auto-generated constructor stub
	}

	public BoardCell pickLocationCOM(Set<BoardCell> targs){
		boolean status = false;
		BoardCell tripCell = new RoomCell();
		
		for (BoardCell cells : targs){
			if(!cells.equals(lastRoom)) {
				status = true;
				tripCell = cells;
			}
			else if(cells.isRoom()) {
				//lastRoom = (RoomCell)cells;
				return cells;
			}
		}
		
		if(status) targs.remove(tripCell);
		Random rng = new Random();
		int i = rng.nextInt(targs.size());
		int item = 0;
		for(BoardCell cells : targs){
			if(i == item) return cells;
			item++;
		}

		return null;
	}


}
