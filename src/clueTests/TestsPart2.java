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
		Assert.assertEquals(2, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(8,27);
		Assert.assertEquals(3, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(10,1);
		Assert.assertEquals(3, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(3,3);
		Assert.assertEquals(4, testList.size());
		// Test one beside a door
		testList = board.getAdjList(6,22);
		Assert.assertEquals(4, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(11,22);
		Assert.assertEquals(2, testList.size());
		//test a door
		testList = board.getAdjList(14,31);
		Assert.assertEquals(3,testList.size());
		//test another door
		testList = board.getAdjList(23,6);
		Assert.assertEquals(4,testList.size());
	}
	
	@Test
	public void testAdjacencyRoomExit() {
		// TEST DOORWAY RIGHT 
		LinkedList<BoardCell> testList = board.getAdjList(12, 6);
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(12, 7)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(27, 20);
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(27, 19)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(29, 28);
		Assert.assertEquals(4, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(29, 27)));
		//TEST DOORWAY UP
		testList = board.getAdjList(18, 31);
		Assert.assertEquals(3, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(17, 31)));
		//TEST DOORWAY CORNER
		testList = board.getAdjList(4,19);
		Assert.assertEquals(3,testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(4,20)));
	}
	
	@Test
	public void testAdjacencyDoorways() {
		// Test beside a door direction RIGHT
		LinkedList<BoardCell> testList = board.getAdjList(22, 6);
		Assert.assertTrue(testList.contains(board.getCellAt(22, 7)));
		Assert.assertTrue(testList.contains(board.getCellAt(22, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(21, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(23, 6)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(15, 31);
		Assert.assertTrue(testList.contains(board.getCellAt(15, 30)));
		Assert.assertTrue(testList.contains(board.getCellAt(14, 31)));
		Assert.assertTrue(testList.contains(board.getCellAt(16, 31)));
		Assert.assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(29, 27);
		Assert.assertTrue(testList.contains(board.getCellAt(29, 26)));
		Assert.assertTrue(testList.contains(board.getCellAt(29, 28)));
		Assert.assertTrue(testList.contains(board.getCellAt(28, 27)));
		Assert.assertTrue(testList.contains(board.getCellAt(30, 27)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(28, 17);
		Assert.assertTrue(testList.contains(board.getCellAt(27, 17)));
		Assert.assertTrue(testList.contains(board.getCellAt(29, 17)));
		Assert.assertTrue(testList.contains(board.getCellAt(28, 16)));
		Assert.assertEquals(3, testList.size());
	}
	
	@Test
	public void testAdjacencyWalkways() {
		LinkedList<BoardCell> testList = board.getAdjList(0, 4);

		// Test on left edge of board, three walkway pieces
		testList = board.getAdjList(15, 7);
		Assert.assertTrue(testList.contains(board.getCellAt(14, 7)));
		Assert.assertTrue(testList.contains(board.getCellAt(16, 7)));
		Assert.assertTrue(testList.contains(board.getCellAt(15, 8)));
		Assert.assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(24, 3);
		Assert.assertTrue(testList.contains(board.getCellAt(23, 3)));
		Assert.assertTrue(testList.contains(board.getCellAt(24, 2)));
		Assert.assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(20,5);
		Assert.assertTrue(testList.contains(board.getCellAt(20, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(20, 4)));
		Assert.assertTrue(testList.contains(board.getCellAt(19, 5)));
		Assert.assertTrue(testList.contains(board.getCellAt(21, 5)));
		Assert.assertEquals(4, testList.size());
		
	}
	
	public void testTargetsOneStep() {
		//walkway next to room
		board.calcTargets(31, 11, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(30, 11)));
		Assert.assertTrue(targets.contains(board.getCellAt(31, 10)));	
		
		//in room next to door
		board.calcTargets(13, 31, 1);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(12, 31)));
		Assert.assertTrue(targets.contains(board.getCellAt(14, 31)));	
		Assert.assertTrue(targets.contains(board.getCellAt(13, 30)));	
	}
	
	@Test
	public void testTargetsTwoSteps() {
		//walkway next to room
		board.calcTargets(17, 4, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(17,6)));
		Assert.assertTrue(targets.contains(board.getCellAt(18,5)));	
		Assert.assertTrue(targets.contains(board.getCellAt(19,4)));
				
		//walkway next to room
		board.calcTargets(31, 10, 2);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(30, 11)));
		Assert.assertTrue(targets.contains(board.getCellAt(29, 10)));	
		Assert.assertTrue(targets.contains(board.getCellAt(31, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(30, 9)));	
	}
	

	
	@Test
	public void testTargetsIntoRoom() {
		//walkway next to room
		board.calcTargets(31, 10, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(30, 11)));
		Assert.assertTrue(targets.contains(board.getCellAt(29, 10)));	
		Assert.assertTrue(targets.contains(board.getCellAt(31, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(30, 9)));	
		
		//walkway next to room
		board.calcTargets(17, 31, 1);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(16, 31)));
		Assert.assertTrue(targets.contains(board.getCellAt(18, 31)));	
		Assert.assertTrue(targets.contains(board.getCellAt(17, 30)));		
	}
	

	@Test
	public void testRoomExit() {
		//room next to walkway
		board.calcTargets(19, 31, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(21, 31)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 30)));	
		Assert.assertTrue(targets.contains(board.getCellAt(19, 29)));
		Assert.assertTrue(targets.contains(board.getCellAt(18, 30)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 31)));
		
		//walkway next to room
		board.calcTargets(18, 23, 2);
		targets= board.getTargets();
		Assert.assertEquals(5, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(18, 25)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 24)));	
		Assert.assertTrue(targets.contains(board.getCellAt(16, 23)));
		Assert.assertTrue(targets.contains(board.getCellAt(17, 22)));
		Assert.assertTrue(targets.contains(board.getCellAt(18, 21)));	
	}

	
}