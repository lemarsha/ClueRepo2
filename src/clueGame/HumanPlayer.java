package clueGame;

import java.util.ArrayList;
import java.util.Set;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, String color, String x, String y) {
		super(name, color,x,y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BoardCell pickLocation(Set<BoardCell> targs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Card> makeSuggestion(Card local, ArrayList<Card> allSeen,
			ArrayList<Card> allPeople, ArrayList<Card> allWeapons) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	String getType() {
		return "Human";
	}

	
}
