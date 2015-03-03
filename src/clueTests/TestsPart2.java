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
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner in a room
		LinkedList<BoardCell> testList = board.getAdjList(0,0);
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(3, 4);
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(18, 8);
		Assert.assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(7,6);
		Assert.assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(9, 10);
		Assert.assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(21,0);
		Assert.assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<BoardCell> testList = board.getAdjList(9, 7);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(9, 8)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(21,21);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(21, 20)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(6,13);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(7, 13)));
		//TEST DOORWAY UP
		testList = board.getAdjList(16, 2);
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.getCellAt(15, 2)));

	}

	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		
		// Test beside a door direction RIGHT
		LinkedList<BoardCell> testList = board.getAdjList(9, 8);
		// TEST DOORWAY LEFT 
		Assert.assertTrue(testList.contains(board.getCellAt(9, 7)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(8, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(10, 8)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(10,20);
		Assert.assertTrue(testList.contains(board.getCellAt(10, 19)));
		Assert.assertTrue(testList.contains(board.getCellAt(10, 21)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 20)));
		Assert.assertTrue(testList.contains(board.getCellAt(11, 20)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(21, 20);
		Assert.assertTrue(testList.contains(board.getCellAt(21,19)));
		Assert.assertTrue(testList.contains(board.getCellAt(20,20)));
		Assert.assertTrue(testList.contains(board.getCellAt(21,21)));
		Assert.assertEquals(3, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(17,9);
		Assert.assertTrue(testList.contains(board.getCellAt(16, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(18, 9)));
		Assert.assertTrue(testList.contains(board.getCellAt(17, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(17, 10)));
		Assert.assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board
		LinkedList<BoardCell> testList = board.getAdjList(0, 21);
		Assert.assertTrue(testList.contains(board.getCellAt(0, 20)));
		Assert.assertTrue(testList.contains(board.getCellAt(1, 21)));
		Assert.assertTrue(testList.contains(board.getCellAt(0, 22)));
		Assert.assertEquals(3, testList.size());

		// Test on left edge of board, three walkway pieces
		testList = board.getAdjList(5,0);
		Assert.assertTrue(testList.contains(board.getCellAt(4, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(5, 1)));
		Assert.assertTrue(testList.contains(board.getCellAt(6, 0)));
		Assert.assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(15, 1);
		Assert.assertTrue(testList.contains(board.getCellAt(15, 0)));
		Assert.assertTrue(testList.contains(board.getCellAt(15, 2)));
		Assert.assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(8,14);
		Assert.assertTrue(testList.contains(board.getCellAt(8, 15)));
		Assert.assertTrue(testList.contains(board.getCellAt(8, 13)));
		Assert.assertTrue(testList.contains(board.getCellAt(9, 14)));
		Assert.assertTrue(testList.contains(board.getCellAt(7, 14)));
		Assert.assertEquals(4, testList.size());

		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(21, 11);
		Assert.assertTrue(testList.contains(board.getCellAt(21, 12)));
		Assert.assertTrue(testList.contains(board.getCellAt(20, 11)));
		Assert.assertEquals(2, testList.size());

		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(10, 22);
		Assert.assertTrue(testList.contains(board.getCellAt(10, 21)));
		Assert.assertTrue(testList.contains(board.getCellAt(11, 22)));
		Assert.assertEquals(2, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(10,7);
		Assert.assertTrue(testList.contains(board.getCellAt(10, 6)));
		Assert.assertTrue(testList.contains(board.getCellAt(10, 8)));
		Assert.assertTrue(testList.contains(board.getCellAt(11, 7)));
		Assert.assertEquals(3, testList.size());
	}


	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(21, 7, 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(20, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(21, 6)));	

		board.calcTargets(14, 0, 1);
		targets= board.getTargets();
		Assert.assertEquals(0, targets.size());		
	}

	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(21, 7, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(19, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(21, 5)));
		

		board.calcTargets(15, 0, 2);
		targets= board.getTargets();
		Assert.assertEquals(1, targets.size());	
		Assert.assertTrue(targets.contains(board.getCellAt(15, 2)));			
	}

	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(21, 7, 4);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(7, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(21, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(20, 4)));
		Assert.assertTrue(targets.contains(board.getCellAt(19, 5)));
		Assert.assertTrue(targets.contains(board.getCellAt(19, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(18, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(17,7)));

		// Includes a path that doesn't have enough length
		board.calcTargets(3, 21, 4);
		targets= board.getTargets();
		Assert.assertEquals(10, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(4,18)));
		Assert.assertTrue(targets.contains(board.getCellAt(5,20)));
		Assert.assertTrue(targets.contains(board.getCellAt(4,20)));	
		Assert.assertTrue(targets.contains(board.getCellAt(4,22)));	
		Assert.assertTrue(targets.contains(board.getCellAt(3,17)));
		Assert.assertTrue(targets.contains(board.getCellAt(3,19)));
		Assert.assertTrue(targets.contains(board.getCellAt(2,22)));	
		Assert.assertTrue(targets.contains(board.getCellAt(1,21)));	
		Assert.assertTrue(targets.contains(board.getCellAt(0,22)));	
		Assert.assertTrue(targets.contains(board.getCellAt(0,20)));
	}	

	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(4,5,6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(21, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(5, 0)));
		Assert.assertTrue(targets.contains(board.getCellAt(4, 1)));	
		Assert.assertTrue(targets.contains(board.getCellAt(6, 1)));	
		Assert.assertTrue(targets.contains(board.getCellAt(5, 2)));	
		Assert.assertTrue(targets.contains(board.getCellAt(4, 3)));	
		Assert.assertTrue(targets.contains(board.getCellAt(6, 3)));	
		Assert.assertTrue(targets.contains(board.getCellAt(5, 4)));	
		Assert.assertTrue(targets.contains(board.getCellAt(5, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 6)));	
		Assert.assertTrue(targets.contains(board.getCellAt(4, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(2, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(0, 7)));	
		Assert.assertTrue(targets.contains(board.getCellAt(1, 8)));	
		Assert.assertTrue(targets.contains(board.getCellAt(3, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(5, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 8)));	
		Assert.assertTrue(targets.contains(board.getCellAt(6, 9)));	
		Assert.assertTrue(targets.contains(board.getCellAt(4, 9)));	
		Assert.assertTrue(targets.contains(board.getCellAt(2, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 0)));	
		Assert.assertTrue(targets.contains(board.getCellAt(1,6)));	
	}	

	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(1, 8, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(1, 6)));
		Assert.assertTrue(targets.contains(board.getCellAt(0,7)));
		Assert.assertTrue(targets.contains(board.getCellAt(2, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(3, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(2, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(0, 9)));
		
	}

	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(9, 8, 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(10,7)));
		Assert.assertTrue(targets.contains(board.getCellAt(9, 7)));
		Assert.assertTrue(targets.contains(board.getCellAt(11, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 8)));
		Assert.assertTrue(targets.contains(board.getCellAt(8, 9)));
		Assert.assertTrue(targets.contains(board.getCellAt(10, 9)));
		
		board.calcTargets(10, 7, 2);
		targets= board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(10,5)));
		Assert.assertTrue(targets.contains(board.getCellAt(11,6)));
		Assert.assertTrue(targets.contains(board.getCellAt(12,7)));
		Assert.assertTrue(targets.contains(board.getCellAt(11,8)));
		Assert.assertTrue(targets.contains(board.getCellAt(9,8)));
		Assert.assertTrue(targets.contains(board.getCellAt(10,9)));
	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(6, 13, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(7, 13)));
		// Take two steps
		board.calcTargets(6, 13, 2);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(7, 12)));
		Assert.assertTrue(targets.contains(board.getCellAt(8, 13)));
		Assert.assertTrue(targets.contains(board.getCellAt(7, 14)));
	}
	
}