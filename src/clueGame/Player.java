package clueGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private BoardCell location;
	private ArrayList<Card> hand = new ArrayList<Card>();
	
	
	public Player(String name, String color, String x, String y) {
		super();
		this.name = name;
		this.color = color;
		
		int locX, locY;
		locX = Integer.parseInt(x);
		locY = Integer.parseInt(y);
		BoardCell bd = new WalkwayCell(locX,locY, "W");
		this.location = bd;
	}
	
	public Player() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BoardCell getLocation() {
		return location;
	}

	public BoardCell setLocation(String x, String y) {
		return null;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public int getHandSize() {
		return hand.size();
	}

	public void addToHand(Card c) {
		hand.add(c);
	}

	public Card disproveSuggestion(Card person, Card weapon, Card place) {
		ArrayList<Card> picks = new ArrayList<Card>();
		for(Card c : hand) {
			if(c.equals(person)){
				picks.add(c);
			}
			else if(c.equals(weapon)) {
				picks.add(c);
			}
			else if(c.equals(place)) {
				picks.add(c);
			}
		}
		
		if(picks.size()>1) {
			Random rng = new Random();
			int i = rng.nextInt(picks.size());
			return picks.get(i);
		}
		else if(picks.size() ==1) {
			return picks.get(0);
		}
		else {
			return null;
		}
	}
	
	abstract BoardCell pickLocation(Set<BoardCell> targs);
}
