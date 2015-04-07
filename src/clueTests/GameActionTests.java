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
	private static Card person,weapon,place,p1,p2,p3,p4,w1,w2,w3,w4,l1,l2,l3,l4;
	
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
		p3 = new Card("Eugene", Card.cardType.PERSON);
		p4 = new Card("Gates", Card.cardType.PERSON);
		weapon = new Card("Shank", Card.cardType.WEAPON);
		w1 = new Card("Sub Machine Gun", Card.cardType.WEAPON);
		w2 = new Card("Lego", Card.cardType.WEAPON);
		w3 = new Card("Penny", Card.cardType.WEAPON);
		w4 = new Card("Jobs", Card.cardType.WEAPON);
		place = new Card("Library", Card.cardType.ROOM);
		l1 = new Card("Billiard Room", Card.cardType.ROOM);
		l2 = new Card("Tree-House", Card.cardType.ROOM);
		l3 = new Card("Basement", Card.cardType.ROOM);
		l4 = new Card("Upstairs", Card.cardType.ROOM);
	}
	
	//Tests that an accusation can be checked against the real solution
	@Test
	public void accusation() {
		//Temp public victory to test for the accusation
		game.victory.setPerson("Tupac");
		game.victory.setPlace("Library");
		game.victory.setWeapon("Ak-47");
		
		//Temperary guess for the solution, all correct: true
		Solution temp = new Solution();
		temp.setPerson("Tupac");
		temp.setPlace("Library");
		temp.setWeapon("Ak-47");
		assertTrue(game.checkAccusation(temp));
		
		
		//Tests if it fails if one portion of the solution is wrong
		temp.setWeapon("shank");
		assertFalse(game.checkAccusation(temp));
		
		temp.setPlace("Kitchen");
		temp.setWeapon("Ak-47");
		assertFalse(game.checkAccusation(temp));
		
		temp.setPerson("Nikki Manaj");
		temp.setPlace("Library");
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
				if (selected == board.getCellAt(3,0)){
					loc_12_0Tot++;
				}
			}
			// Ensure we have at least 10 total selections (fail should also ensure)
			assertTrue(loc_12_0Tot >10);
			// Ensure that the door wasn't selected every time since it's previously been entered
			assertTrue(loc_12_0Tot < 100);
			
			
			
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
			// Ensure each target was selected more than once, and the door only sometimes was
			assertTrue(loc_12_0Tot > 2);
			assertTrue(loc_14_2Tot > 2);
			assertTrue(loc_15_1Tot > 2);
			assertTrue(loc_4_1Tot > 2);
			assertTrue(loc_5_1Tot>0);
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
		assertTrue(null == playa.disproveSuggestion(person.getName(), weapon.getName(), place.getName()));
		
		//Person is in common
		assertTrue(p1.equals(playa.disproveSuggestion(p1.getName(), weapon.getName(), place.getName())));
		
		//Weapom is in common
		assertTrue(w1.equals(playa.disproveSuggestion(person.getName(), w1.getName(), place.getName())));
		
		//Person is in common
		assertTrue(l1.equals(playa.disproveSuggestion(person.getName(), weapon.getName(), l1.getName())));
	}
	
	@Test
	public void sugHandlePartDos () {
		Player playa = new ComputerPlayer();
		playa.addToHand(p1);
		playa.addToHand(w1);
		playa.addToHand(l1);
		
		
		int plays = 0;
		int weps = 0;
		int locs = 0;
		
		for(int i=0;i<100;i++) {
			Card check= new Card(); 
			check=playa.disproveSuggestion(p1.getName(), w1.getName(), l1.getName());
			
			if(p1.equals(check)) {
					plays++;
			}
			else if(w1.equals(check)){
				weps++;
			}
			else if(l1.equals(check)) {
				locs++;
			}
			else
				fail("Invalid choice!");
		}
		
		assertTrue(100 ==(plays+locs+weps));
		assertTrue(plays>10);
		assertTrue(locs>10);
		assertTrue(weps>10);
	}
	
	@Test
	public void handleSuggestionTest () {
		ArrayList<Player> playaList = new ArrayList<Player>();
		Player com= new ComputerPlayer("Eugene", "Yellow", "2","10");
		com.addToHand(person);com.addToHand(place);com.addToHand(weapon);
		playaList.add(com);
		
		com = new ComputerPlayer("Tyrone", "Red", "15", "21");
		com.addToHand(l1); com.addToHand(w1); com.addToHand(p1);
		playaList.add(com);
		
		com = new ComputerPlayer("NiKki Manaj", "Green", "13", "10");
		com.addToHand(l2); com.addToHand(w2); com.addToHand(p2);
		playaList.add(com);
		
		com = new HumanPlayer("ChickenMan", "Blue", "20", "7");
		com.addToHand(l3); com.addToHand(w3); com.addToHand(p3);
		playaList.add(com);
		
		game.setPlayers(playaList);
		
		//Player suggestion, only he can disprove
		game.handleSuggestion(person.getName(), place.getName(), weapon.getName(), playaList.get(0));
		ArrayList<Card> testList = game.getProof();
		assertTrue(testList.size() == 0);
		
		//No Player can disprove
		game.handleSuggestion(p4.getName(), l4.getName(), w4.getName(), playaList.get(0));
		testList = game.getProof();
		assertTrue(testList.size() == 0);
		
		//Only Human can disprove and is the farthest away
		game.handleSuggestion(p3.getName(), l4.getName(), w4.getName(), playaList.get(0));
		testList = game.getProof();
		assertTrue(testList.contains(p3));
		
		//Testing the order of the suggestion check
		game.handleSuggestion(p1.getName(), l2.getName(), w4.getName(), playaList.get(0));
		testList = game.getProof();
		assertTrue(p1.equals(testList.get(0)));
		assertTrue(l2.equals(testList.get(1)));
		assertTrue(testList.size() == 2);
		
		
	}
	
	@Test
	public void ComputerMakesSuggestion() {
		ArrayList<Card> testListPerson = new ArrayList<Card>();
		ArrayList<Card> testListWeapons = new ArrayList<Card>();
		ArrayList<Card> testListAll = new ArrayList<Card>();
		testListPerson.add(person);testListPerson.add(p1);testListPerson.add(p2);
		testListPerson.add(p3);testListPerson.add(p4);
		testListWeapons.add(weapon);testListWeapons.add(w1);testListWeapons.add(w2);
		testListWeapons.add(w3);testListWeapons.add(w4);
		
		testListAll.add(person);testListAll.add(weapon);testListAll.add(p1);
		testListAll.add(p2);testListAll.add(p3);testListAll.add(w1);
		testListAll.add(w2);
		
		
		//Player prepared for a single option
		Player player = new ComputerPlayer("Tyrone", "Red", "12","12");
		player.addToHand(p3);
		player.addToHand(w3);
		
		Solution ans = player.makeSuggestion(l1, testListAll, testListPerson, testListWeapons);
		assertTrue(ans.getPlace().equals(l1.getName()));
		assertTrue(ans.getPerson().equals(p4.getName()));
		assertTrue(ans.getWeapon().equals(w4.getName()));
		
		
		//Testing that the computer can make a guess from a list
		//with correct choices and random possibilities
		testListAll.remove(0);testListAll.remove(1);
		
		int pc = 0;
		int p1c = 0;
		int p4c = 0;
		for(int i =0;i<100;i++){
			 ans = player.makeSuggestion(l1, testListAll, testListPerson,
					testListWeapons);
			 
			 if(ans.getPerson().equals(person.getName())) pc++;
			 else if(ans.getPerson().equals(p1.getName())) p1c++;
			 else if(ans.getPerson().equals(p4.getName()))p4c++;
			 else
				 fail("FAIL");
		}
		
		assertTrue(100 == pc+p1c+p4c);
		assertTrue(pc>10);
		assertTrue(p1c>10);
		assertTrue(p4c>10);
	}
}

