package clueGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	private char lastRoomVisited;

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
		
		ArrayList<BoardCell> targetArray= new ArrayList<BoardCell>();
		for (BoardCell b: targs) {
			if (b.isDoorway() && b.getInitial() != lastRoomVisited) {
				lastRoomVisited = b.getInitial();
				return b;
			}
			targetArray.add(b);
		}
		Random r = new Random();
		int rannum = r.nextInt(targetArray.size());
		return targetArray.get(rannum);
		
		
	}

	@Override
	public Solution makeSuggestion(Card local, ArrayList<Card> allSeen,
			ArrayList<Card> allPeople, ArrayList<Card> allWeapons) {
		
		Solution s = new Solution();
		s.setPlace(local.getName());
		
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
		s.setPerson(peopleSelection.get(i).getName());
		
		i = rng.nextInt(weaponSelection.size());
		returnList.add(weaponSelection.get(i));
		s.setWeapon(weaponSelection.get(i).getName());
		
		return s;
	}
	

}
