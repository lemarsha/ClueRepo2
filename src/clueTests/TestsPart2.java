package clueTests;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.ClueGame;

public class TestsPart2 {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		ClueGame game = new ClueGame();
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}
	
	@Test
	public void testAdjacenciesInsideRooms() {
		//test a corner
		LinkedList<BoardCell> testList = board.getAdjList(0, 0);
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(8,27);
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(10,1);
		Assert.assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(3,3);
		Assert.assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(6,22);
		Assert.assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(11,22);
		Assert.assertEquals(0, testList.size());
	}
	
	@Test
	public void testAdjacencyRoomExit() {
		
	}
	
	@Test
	public void testAdjacencyDoorways() {
		
	}
	
	@Test
	public void testAdjacencyWalkways() {
		
	}
	
	public void testTargetsOneStep() {
		
	}
	
	@Test
	public void testTargetsTwoSteps() {
		
	}
	
	@Test
	public void testTargetsFourSteps() {
		
	}
	
	@Test
	public void testTargetsSixSteps() {
		
	}
	
	@Test
	public void testTargetsIntoRoom() {
		
	}
	
	@Test
	public void testTargetsIntoRoomsShortcut() {
		
	}
	
	@Test
	public void testRoomExit() {
		
	}

	
}