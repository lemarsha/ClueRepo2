package clueTests;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import clueGame.*;

public class GameActionTests {
	
	private static Board board;
	private static ClueGame game;
	
	@Before
	public void setUp() {
		game = new ClueGame();
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}
	
	//Tests that an accusation can be checked against the real solution
	@Test
	public void accusation() {
		//Temp public victory to test for the accusation
		game.victory.person = "Tupac";
		game.victory.place = "Library";
		game.victory.weapon = "Ak-47";
		
		//Temperary guess for the solution, all correct: true
		Solution temp = new Solution();
		temp.person = "Tupac";
		temp.place = "Library";
		temp.weapon="Ak-47";
		Assert.assertTrue(game.checkAccusation(temp));
		
		
		//Tests if it fails if one portion of the solution is wrong
		temp.weapon="shank";
		Assert.assertFalse(game.checkAccusation(temp));
		
		temp.place = "Kitchen";
		temp.weapon="Ak-47";
		Assert.assertFalse(game.checkAccusation(temp));
		
		temp.person = "Nikki Manaj";
		temp.place = "Library";
		Assert.assertFalse(game.checkAccusation(temp));
	}

	@Test
		public void testTargetRandomSelection() {
			ComputerPlayer player = new ComputerPlayer("CRAIDER", "Crimson","21","11" );
			// Pick a location with no rooms in target, just three targets
			board.calcTargets(21, 11, 2);
			int loc_12_0Tot = 0;
			int loc_14_2Tot = 0;
			int loc_15_1Tot = 0;
			// Run the test 100 times
			for (int i=0; i<100; i++) {
				BoardCell selected = player.pickLocation(board.getTargets());
				if (selected == board.getCellAt(12, 0))
					loc_12_0Tot++;
				else if (selected == board.getCellAt(14, 2))
					loc_14_2Tot++;
				else if (selected == board.getCellAt(15, 1))
					loc_15_1Tot++;
				else
					fail("Invalid target selected");
			}
			// Ensure we have 100 total selections (fail should also ensure)
			assertEquals(100, loc_12_0Tot + loc_14_2Tot + loc_15_1Tot);
			// Ensure each target was selected more than once
			assertTrue(loc_12_0Tot > 10);
			assertTrue(loc_14_2Tot > 10);
			assertTrue(loc_15_1Tot > 10);
		
	}
}

