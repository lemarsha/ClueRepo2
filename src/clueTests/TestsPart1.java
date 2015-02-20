package clueTests;

//Doing a static import allows me to write assertEquals rather than
//Assert.assertEquals
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;
import clueGame.RoomCell;


public class TestsPart1 {

	private static Board board;
	public static final int NUM_ROOMS = 9;
	public static final int NUM_ROWS = 32;
	public static final int NUM_COLUMNS = 32;
	
	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame("ClueLayout.csv","ClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
	}
	
	@Test
	public void testRooms() {
		Map<Character,String> rooms = board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		assertEquals("Conservatory", rooms.get('C'));
		assertEquals("Kitchen", rooms.get('K'));
		assertEquals("Ballroom", rooms.get('B'));
		assertEquals("Billiard room", rooms.get('R'));
		assertEquals("Library", rooms.get('L'));
		assertEquals("Study", rooms.get('S'));
		assertEquals("Dining room", rooms.get('D'));
		assertEquals("Hall", rooms.get('H'));
		assertEquals("Wine Cellar", rooms.get('W'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Path", rooms.get('P'));
	}
	
	@Test
	public void testBoardConfig() {
		assertEquals(NUM_ROWS,board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	

}
