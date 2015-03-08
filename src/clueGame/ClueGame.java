package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;



public class ClueGame {

	private static final String LAYOUTFILE = "boardLayout.csv";
	private static final String LEGENDFILE = "legend.txt";
	private static final String CARDFILE = "cards.txt";

	//private static final String LAYOUTFILE = "ClueLayout.csv";
	//private static final String LEGENDFILE = "ClueLegend.txt";

	private Map<Character,String> rooms = null;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	private String layoutFile, legendFile, cardFile;
	private int totalPlayers, totalWeapons, totalRooms;
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
		deal();
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
				totalRooms++;
			}
			else {
				String s = in.nextLine();
				Card c = new Card(s, Card.cardType.WEAPON);
				deck.add(c);
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

	public void shuffleDeck() {
		
	}
	
	public void deal(){
		//Deals the cards to all the players so that they can hold the cards
		
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

	public static void main(String[] args) {
		ClueGame game = new ClueGame("boardLayout.csv", "legend.txt", "cards.txt");
		game.loadConfigFiles();
		Board b = game.getBoard();
		RoomCell r = b.getRoomCellAt(31, 10);
		System.out.println(r.isDoorway());
	}



}
