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
	public static final int NUM_ROOMS = 11;
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
		assertEquals("Guest room", rooms.get('G'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Walkway", rooms.get('W'));
	}
	
	@Test
	public void testBoardConfig() {
		assertEquals(NUM_ROWS,board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void testDoorDirections() {
		
		RoomCell room = board.getRoomCellAt(31,9);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getRoomCellAt(29,28);
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
		assertTrue(board.getRoomCellAt(0,30).getInitial().equals('C'));
		assertTrue(board.getRoomCellAt(1,1).getInitial().equals('K'));
		assertTrue(board.getRoomCellAt(1,15).getInitial().equals('B'));
		assertTrue(board.getRoomCellAt(12,30).getInitial().equals('R'));
		assertTrue(board.getRoomCellAt(18,30).getInitial().equals('L'));
		assertTrue(board.getRoomCellAt(30,22).getInitial().equals('S'));
		assertTrue(board.getRoomCellAt(29,5).getInitial().equals('D'));
		assertTrue(board.getRoomCellAt(29,14).getInitial().equals('H'));
		assertTrue(board.getRoomCellAt(14,2).getInitial().equals('G'));
		assertTrue(board.getRoomCellAt(14,14).getInitial().equals('X'));
		
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws Exception {
		ClueGame badColGame = new ClueGame("ClueLayoutBadColumns.csv","ClueLegendCR.txt");
		badColGame.loadConfigFiles();
		Board badColBoard = badColGame.getBoard();
		badColBoard.loadBoardConfig();
		
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws Exception {
		ClueGame badRoomGame = new ClueGame("ClueLayoutBadRoom.csv", "ClueLegendCR.txt");
		badRoomGame.loadConfigFiles();
		Board badRoomBoard = badRoomGame.getBoard();
		badRoomBoard.loadBoardConfig();
	}
	
	@Test
	public void testNumberofDoors() {
		int numDoors = 0;
		int totalCells = board.getNumColumns()*board.getNumRows();
		Assert.assertEquals(1024, totalCells);
		for (int row=0; row<board.getNumRows(); row++) {
			for (int col = 0; col<board.getNumColumns(); ++col) {
				BoardCell cell = board.getCellAt(row,col);
				if (cell.isDoorway()) {
					numDoors++;
				}
			}
		}
		assertEquals(25, numDoors);
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws Exception {
		ClueGame badLegendGame = new ClueGame("ClueLayoutCR.csv","ClueLegendBadFormat.txt");
		badLegendGame.loadConfigFiles();
		Board badLegendBoard = badLegendGame.getBoard();
		badLegendBoard.loadBoardConfig();
	}
	
	
	

}
