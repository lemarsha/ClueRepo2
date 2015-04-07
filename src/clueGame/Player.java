package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public abstract class Player {
	private String name;
	private String color;
	private BoardCell location;
	private static final int PEOPLE_DIMEN = 25;
	//protected int locX, locY;
	protected ArrayList<Card> hand = new ArrayList<Card>();
	
	
	public Player(String name, String color, String x, String y) {
		super();
		this.name = name;
		this.color = color;
		
		int locX = Integer.parseInt(x);
		int locY = Integer.parseInt(y); 
		BoardCell bd = new WalkwayCell(locX,locY, "W");
		this.location = bd;
	}
	
	abstract String getType();
	
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

	public void setLocation(int x, int y) {
		location.setRow(x);
		location.setColumn(y);
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

	public Card disproveSuggestion(String person, String weapon, String place) {
		ArrayList<Card> picks = new ArrayList<Card>();
		for(Card c : hand) {
			if(c.getName().equals(person)){
				picks.add(c);
			}
			else if(c.getName().equals(weapon)) {
				picks.add(c);
			}
			else if(c.getName().equals(place)) {
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
	
	public void draw(Graphics g, Board board) {
		if(color.equals("red")) g.setColor(Color.red);
		else if(color.equals("green")) g.setColor(Color.GREEN);
		else if(color.equals("blue")) g.setColor(Color.BLUE);
		else if(color.equals("white")) g.setColor(Color.white);
		else if(color.equals("yellow")) g.setColor(Color.orange);
		else if(color.equals("purple")) g.setColor(Color.MAGENTA);
		g.fillOval(location.getColumn()*PEOPLE_DIMEN, location.getRow()*PEOPLE_DIMEN, PEOPLE_DIMEN,PEOPLE_DIMEN);
		g.setColor(Color.BLACK);
		g.drawOval(location.getColumn()*PEOPLE_DIMEN, location.getRow()*PEOPLE_DIMEN, PEOPLE_DIMEN,PEOPLE_DIMEN);
	}
	
	
	public abstract BoardCell pickLocation(Set<BoardCell> targs);
	
	public abstract Solution makeSuggestion(Card local, ArrayList<Card> allSeen,
			ArrayList<Card> allPeople, ArrayList<Card> allWeapons);
}
