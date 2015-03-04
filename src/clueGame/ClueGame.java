package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;



public class ClueGame {

	private static final String LAYOUTFILE = "boardLayout.csv";
	private static final String LEGENDFILE = "legend.txt";
	private static final String PLAYERFILE = "characters.txt";

	//private static final String LAYOUTFILE = "ClueLayout.csv";
	//private static final String LEGENDFILE = "ClueLegend.txt";

	private Map<Character,String> rooms = null;
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Card> deck = new ArrayList<Card>();
	private String layoutFile, legendFile, playerFile;
	private int totalPlayers, totalWeapons, totalRooms;
	private Board b;

	public ClueGame(String layoutFile, String legendFile, String playerFile) {
		super();
		this.layoutFile = layoutFile;
		this.legendFile = legendFile;
		this.playerFile = playerFile;
	}

	public ClueGame() {
		super();
		layoutFile = LAYOUTFILE;
		legendFile = LEGENDFILE;
		playerFile = PLAYERFILE;
	}

	public void loadConfigFiles() {
		loadRoomConfig();
		/*
		b = new Board(layoutFile,legendFile);
		try {
		b.loadBoardConfig();
		} catch(FileNotFoundException e) {
			e.getLocalizedMessage();
		}catch (BadConfigFormatException e) {
			e.getLocalizedMessage();
		}
		 */
		loadPlayers();
		deal();
	}

	public void loadPlayers() {
		//Have player select name to play as that character, rest of names
		//are sent to the computerplayer class, also can select how many players 
		//are playing, for additional work
		boolean human = true;
		FileReader reader = null;
		Scanner in = null;
		try{
			reader = new FileReader(playerFile);
			in = new Scanner(reader);

		}catch (FileNotFoundException e) {
			System.out.println(e.getLocalizedMessage());
		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] pStat = new String[4];
			pStat= line.split(",");
			Player p;
			
			if(human) {
				 p = new HumanPlayer(pStat[0], pStat[1], pStat[2], pStat[3]);
				human = false;
			}
			else
				p = new ComputerPlayer(pStat[0], pStat[1], pStat[2], pStat[3]);
			
			players.add(p);

		}
		in.close();

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

	public ArrayList<Card> deal(){
		return null;
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

	public static void main(String[] args) {
		ClueGame game = new ClueGame("boardLayout.csv", "legend.txt", "characters.txt");
		game.loadConfigFiles();
		Board b = game.getBoard();
		RoomCell r = b.getRoomCellAt(31, 10);
		System.out.println(r.isDoorway());
	}



}
