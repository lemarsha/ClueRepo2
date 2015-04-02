package clueGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	private RoomCell lastRoom = new RoomCell(9,7,"R");

	public ComputerPlayer(String name, String color, String x, String y) {
		super(name, color, x,y);
		// TODO Auto-generated constructor stub
	}

	public ComputerPlayer() {
		super();
	}
	
	@Override
	public String getType() {
		return "Computer";
	}
	

	@Override
	public BoardCell pickLocation(Set<BoardCell> targs){
		
		
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

	@Override
	public ArrayList<Card> makeSuggestion(Card local, ArrayList<Card> allSeen,
			ArrayList<Card> allPeople, ArrayList<Card> allWeapons) {
		
		ArrayList<Card> returnList = new ArrayList<Card>();
		ArrayList<Card> peopleSelection = new ArrayList<Card>();
		ArrayList<Card> weaponSelection = new ArrayList<Card>();
		returnList.add(local);
		
		for(Card c: allPeople) {
			if(!allSeen.contains(c) && !hand.contains(c)){
				peopleSelection.add(c);
			}
		}
		
		for(Card c: allWeapons) {
			if(!allSeen.contains(c) && !hand.contains(c)){
				weaponSelection.add(c);
			}
		}
		
		Random rng = new Random();
		int i = rng.nextInt(peopleSelection.size());
		returnList.add(peopleSelection.get(i));
		i = rng.nextInt(weaponSelection.size());
		returnList.add(weaponSelection.get(i));
		
		return returnList;
	}
	

}
