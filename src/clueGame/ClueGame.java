package clueGame;

import java.util.*;

public class ClueGame {
	
	private Map<Character,String> rooms = null;
	private String layoutFile, legendFile;
	private Board b;
	
	public ClueGame(String layoutFile, String legendFile) {
		super();
		this.layoutFile = layoutFile;
		this.legendFile = legendFile;
	}
	
	public void loadConfigFiles(){
		try {
			b.loadBoardConfig();
		}catch (Exception e){
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	public void loadRoomConfig() {
		
	}
	
	public Board getBoard() {
		b = new Board(layoutFile, legendFile);
		return b;
		
	}
	

}
