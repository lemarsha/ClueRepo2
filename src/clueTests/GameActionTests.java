package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.*;

public class GameActionTests {
	
	private static Board board;
	private static ClueGame game;
	private static Card person,weapon,place,p1,p2,w1,w2,l1,l2;
	
	@Before
	public void setUp() {
		game = new ClueGame();
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}
	
	@BeforeClass
	public static void makeCards() {
		person = new Card("Nikki Manaj", Card.cardType.PERSON);
		p1 = new Card("Tyrone", Card.cardType.PERSON);
		p2 = new Card("ChickenMan", Card.cardType.PERSON);
		weapon = new Card("Shank", Card.cardType.WEAPON);
		w1 = new Card("Sub Machine Gun", Card.cardType.WEAPON);
		w2 = new Card("Lego", Card.cardType.WEAPON);
		place = new Card("Library", Card.cardType.ROOM);
		l1 = new Card("Billiard Room", Card.cardType.ROOM);
		l2 = new Card("Tree-House", Card.cardType.ROOM);
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
		assertTrue(game.checkAccusation(temp));
		
		
		//Tests if it fails if one portion of the solution is wrong
		temp.weapon="shank";
		assertFalse(game.checkAccusation(temp));
		
		temp.place = "Kitchen";
		temp.weapon="Ak-47";
		assertFalse(game.checkAccusation(temp));
		
		temp.person = "Nikki Manaj";
		temp.place = "Library";
		assertFalse(game.checkAccusation(temp));
	}

	//Tests that a ComputerPlayer can randomly select a location based of x
	//amount of steps, while keeping in mind what rooms it was just in
	//to play the game more effectively
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
				if (selected == board.getCellAt(19,11))
					loc_12_0Tot++;
				else if (selected == board.getCellAt(20,12))
					loc_14_2Tot++;
				else if (selected == board.getCellAt(21,13))
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
		
			
			player =  new ComputerPlayer("CRAIDER", "Crimson","4","0" );
			// Pick a Location that has a door that hasn't been entered yet
			board.calcTargets(4, 0, 2);
			loc_12_0Tot = 0;
			loc_14_2Tot = 0;
			loc_15_1Tot = 0;
			// Run the test 100 times
			for (int i=0; i<100; i++) {
				BoardCell selected = player.pickLocation(board.getTargets());
				if (selected == board.getCellAt(3,0))
					loc_12_0Tot++;
				else
					fail("Invalid target selected");
			}
			// Ensure we have 100 total selections (fail should also ensure)
			assertEquals(100, loc_12_0Tot + loc_14_2Tot + loc_15_1Tot);
			// Ensure that only the door was selected
			assertTrue(loc_12_0Tot == 100);
			assertTrue(loc_14_2Tot == 0);
			assertTrue(loc_15_1Tot == 0);
			
			
			
			player =  new ComputerPlayer("CRAIDER", "Crimson","8","8" );
			// Pick a location with rooms that have been gone to, and other walkways
			board.calcTargets(8, 8, 2);
			loc_12_0Tot = 0;
			loc_14_2Tot = 0;
			loc_15_1Tot = 0;
			int loc_4_1Tot = 0;
			int loc_5_1Tot = 0;
			
			// Run the test 100 times
			for (int i=0; i<100; i++) {
				
				BoardCell selected = player.pickLocation(board.getTargets());
				if (selected == board.getCellAt(7,9))
					loc_12_0Tot++;
				else if (selected == board.getCellAt(9,9))
					loc_14_2Tot++;
				else if (selected == board.getCellAt(10,8))
					loc_15_1Tot++;
				else if( selected == board.getCellAt(6, 8))
						loc_4_1Tot++;
				else if( selected == board.getCellAt(9, 7))
					loc_5_1Tot++;
				else
					fail("Invalid target selected");
			}
			// Ensure we have 100 total selections (fail should also ensure)
			assertEquals(100, loc_12_0Tot + loc_14_2Tot + loc_15_1Tot+loc_4_1Tot+loc_5_1Tot);
			// Ensure each target was selected more than once, and the door never was
			assertTrue(loc_12_0Tot > 2);
			assertTrue(loc_14_2Tot > 2);
			assertTrue(loc_15_1Tot > 2);
			assertTrue(loc_4_1Tot > 2);
			assertTrue(loc_5_1Tot==0);
	}	
	
	@Test
	public void suggestionHandling() {
		//Making a player and adding cards to the hand
		Player playa = new ComputerPlayer();
		playa.addToHand(p1);
		playa.addToHand(p2);
		playa.addToHand(w1);
		playa.addToHand(w2);
		playa.addToHand(l1);
		playa.addToHand(l2);
		
		//No cards are in common
		assertTrue(null == playa.disproveSuggestion(person, weapon, place));
		
		//Person is in common
		assertTrue(p1.equals(playa.disproveSuggestion(p1, weapon, place)));
		
		//Weapom is in common
		assertTrue(w1.equals(playa.disproveSuggestion(person, w1, place)));
		
		//Person is in common
		assertTrue(l1.equals(playa.disproveSuggestion(person, weapon, l1)));
	}
}

