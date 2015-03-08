package clueGame;

import java.util.ArrayList;

public class Player {
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
		// TODO Auto-generated constructor stub
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


	public boolean disproveSuggestion(Card c) {
		
		return false;
	}
}
