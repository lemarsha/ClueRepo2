package clueGame;

import java.io.FileNotFoundException;
import java.util.*;



public class ClueGame {
	
	private static final String LAYOUTFILE = "boardLayout.csv";
	private static final String LEGENDFILE = "legend.txt";
	
	//private static final String LAYOUTFILE = "ClueLayout.csv";
	//private static final String LEGENDFILE = "ClueLegend.txt";
	
	private Map<Character,String> rooms = null;
	private ArrayList<Player> players = new ArrayList<Player>();
	private String layoutFile, legendFile;
	private Board b;
	
	public ClueGame(String layoutFile, String legendFile) {
		super();
		this.layoutFile = layoutFile;
		this.legendFile = legendFile;
	}
	
	public ClueGame() {
		super();
		layoutFile = LAYOUTFILE;
		legendFile = LEGENDFILE;
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
		
		//An imported file
		
		//Player p = new ComputerPlayer("");
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
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
		game.loadConfigFiles();
		Board b = game.getBoard();
		RoomCell r = b.getRoomCellAt(31, 10);
		System.out.println(r.isDoorway());
	}

	
	
}
