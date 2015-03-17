package clueGame;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	private RoomCell lastRoom = new RoomCell(9,7,"R");

	public ComputerPlayer(String name, String color, String x, String y) {
		super(name, color, x,y);
		// TODO Auto-generated constructor stub
	}

	public BoardCell pickLocationCOM(Set<BoardCell> targs){
		
		
		for (Iterator<BoardCell> iterator= targs.iterator(); iterator.hasNext();){
			BoardCell cells = iterator.next();
			if(cells.equals(lastRoom)) {
				iterator.remove();
			}
			else if(cells.isRoom()) {
				//lastRoom = (RoomCell)cells;
				return cells;
			}
		}
		
		
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
