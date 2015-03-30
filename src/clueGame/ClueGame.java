package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



public class ClueGame extends JFrame{

	private static final String LAYOUTFILE = "boardLayout.csv";
	private static final String LEGENDFILE = "legend.txt";
	private static final String CARDFILE = "cards.txt";

	//private static final String LAYOUTFILE = "ClueLayout.csv";
	//private static final String LEGENDFILE = "ClueLegend.txt";

	private Map<Character,String> rooms = null;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> proof = new ArrayList<Card>();
	private ArrayList<Card> seenCards = new ArrayList<Card>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	private ArrayList<Card> people = new ArrayList<Card>();
	private ArrayList<Card> weapons = new ArrayList<Card>();
	private ArrayList<Card> locations = new ArrayList<Card>();
	private Notes notes;
	private String layoutFile, legendFile, cardFile;
	private int totalPlayers, totalWeapons, totalRooms;
	public static Solution victory = new Solution(); 
	private Board b;

	public ClueGame(String layoutFile, String legendFile, String cardFile) {
		super();
		this.layoutFile = layoutFile;
		this.legendFile = legendFile;
		this.cardFile = cardFile;
	}

	public ClueGame() {
		super();
		layoutFile = LAYOUTFILE;
		legendFile = LEGENDFILE;
		cardFile = CARDFILE;
	}

	public void loadConfigFiles() {
		loadRoomConfig();
		loadCards();
		
	}

	public void loadCards() {
		String[] sarr = new String[6];  
		FileReader reader = null;
		Scanner in = null;
		try{
			reader = new FileReader(cardFile);
			in = new Scanner(reader);

		}catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		int counter = 0;
		while(in.hasNextLine()) {
			if(counter < 6) {
				sarr[counter] = in.nextLine();
				totalPlayers++;
			}
			else if(counter < 15) {
				String s = in.nextLine();
				Card c = new Card(s, Card.cardType.ROOM);
				deck.add(c);
				locations.add(c);
				totalRooms++;
			}
			else {
				String s = in.nextLine();
				Card c = new Card(s, Card.cardType.WEAPON);
				deck.add(c);
				weapons.add(c);
				totalWeapons++;
			}
			counter++;
		}
		loadPlayers(sarr);
	}
	
	public void loadPlayers(String[] sarr) {
		//Have player select name to play as that character, rest of names
		//are sent to the computerplayer class, also can select how many players 
		//are playing, for additional work
		boolean human = true;
		for(String s: sarr) {
			String[] pStat = new String[4];
			pStat= s.split(",");
			Player p;
			
			if(human) {
				 p = new HumanPlayer(pStat[0], pStat[1], pStat[2], pStat[3]);
				human = false;
			}
			else
				p = new ComputerPlayer(pStat[0], pStat[1], pStat[2], pStat[3]);
			
			players.add(p);
			Card c = new Card(pStat[0], Card.cardType.PERSON);
			deck.add(c);
			people.add(c);
		}
	}

	public ArrayList<Player> returnPlayers() {
		return players;
	}

	public void loadRoomConfig() {
		b = new Board(layoutFile,legendFile);
		try {
			b.loadBoardConfig();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		} catch (BadConfigFormatException e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}
		b.getRooms();
	}

	public Board getBoard() {
		return b;
	}

	public Map<Character, String> getRooms() {
		return b.getRooms();
	}
	
	public void deal(){
		//Deals the cards to all the players so that they can hold the cards

		Random rng = new Random();
		int i = rng.nextInt(9);
		Card c = deck.get(i);
		victory.setPlace(c.getName());
		i = rng.nextInt(6) + 9;
		c = deck.get(i);
		victory.setPerson(c.getName());
		i = rng.nextInt(6)+15;
		c = deck.get(i);
		victory.setWeapon(c.getName());
		
		int playerGetCard=0;
		Player p = new ComputerPlayer();
		while(deck.size()>0) {
			i = rng.nextInt(deck.size());
			c = deck.get(i);
			deck.remove(i);
			if(playerGetCard == 0) {
				p = players.get(0);
				p.addToHand(c);
			}
			else if(playerGetCard == 1){
				p = players.get(1);
				p.addToHand(c);
			}
			else if(playerGetCard == 2) {
				p = players.get(2);
				p.addToHand(c);
			}
			else if(playerGetCard == 3) {
				p = players.get(3);
				p.addToHand(c);}
			else if( playerGetCard == 4){
				p = players.get(4);
				p.addToHand(c);
			}
			else {
				p = players.get(5);
				p.addToHand(c);
				playerGetCard =-1;
			}
			playerGetCard++;
		}
		
	}
	
	public int  getDeckSize() {
		return deck.size();
	}
	public int getPlayersTot() {
		return totalPlayers;
	}
	public int  getWeaponsTot() {
		return totalWeapons;
	}
	public int  getRoomTot() {
		return totalRooms;
	}
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public ArrayList<Card> getPeople() {
		return people;
	}
	
	public ArrayList<Card> getWeapons() {
		return weapons;
	}
	
	public ArrayList<Card> getProof() {
		return proof;
	}
	
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void handleSuggestion(Card perp, Card loc, Card wep, Player accplaya){
		proof.clear();
		Card c = new Card();
		for(int i =0; i< players.size(); i ++) {
			Player p = players.get(i);
			if(!p.getName().equals(accplaya.getName())){
				c = p.disproveSuggestion(perp, wep, loc);
				if(!(c==null)){
					proof.add(c);
					if(!(c.getcardType() == Card.cardType.ROOM))
						seenCards.add(c);
				}
			}
		}
	}
	
	public boolean checkAccusation(Solution solut) {
		if(solut.getPerson().equals(victory.getPerson()) && solut.getPlace().equals(victory.getPlace())
				&& solut.getWeapon().equals(victory.getWeapon())) return true;
		
		return false;
	}
	
	private JMenu menu(){
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		menu.add(createNotesItem());
		return menu;
	}
	private JMenuItem createNotesItem() {
		JMenuItem item = new JMenuItem("Notes");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				notes = new Notes(people,weapons,locations);
				notes.setVisible(true);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame("boardLayout.csv", "legend.txt", "cards.txt");
		game.loadConfigFiles();
	}			
}
