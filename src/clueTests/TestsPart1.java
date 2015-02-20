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
import clueGame.DoorDirection;
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
	
	@Test
	public void TestDoorDirections() {
		RoomCell room = board.getRoomCellAt(31,9);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(28,29);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getRoomCellAt(18,31);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		room = board.getRoomCellAt(14,31);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
	}
	
	@Test
	public void testRoomInitials() {
		assertEquals('C',board.getRoomCellAt(0,30));
		assertEquals('K',board.getRoomCellAt(1,1));
		assertEquals('B',board.getRoomCellAt(1,15));
		assertEquals('R',board.getRoomCellAt(12,30));
		assertEquals('L',board.getRoomCellAt(18,30));
		assertEquals('S',board.getRoomCellAt(30,22));
		assertEquals('D',board.getRoomCellAt(29,5));
		assertEquals('H',board.getRoomCellAt(29,14));
		assertEquals('W',board.getRoomCellAt(14,2));
		assertEquals('X',board.getRoomCellAt(14,14));
		assertEquals('P',board.getRoomCellAt(9,31));
		
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		
	}
	

}
