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