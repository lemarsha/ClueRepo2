package clueGame;

import java.io.FileNotFoundException;
import java.util.*;



public class ClueGame {
	
	private static final String LAYOUTFILE = "boardLayout.csv";
	private static final String LEGENDFILE = "legend.txt";
	
	//private static final String LAYOUTFILE = "ClueLayout.csv";
	//private static final String LEGENDFILE = "ClueLegend.txt";
	
	private Map<Character,String> rooms = null;
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
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame("ClueLayout.csv", "ClueLegend.txt");
		game.loadConfigFiles();
		Board b = game.getBoard();
		RoomCell r = b.getRoomCellAt(31, 10);
		System.out.println(r.isDoorway());
	}

	

}
