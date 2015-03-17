package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.Player;
import clueGame.HumanPlayer;
import clueGame.ComputerPlayer;
import clueGame.ClueGame;
import clueGame.WalkwayCell;
import clueGame.Card.cardType;

public class GameSetupTests {
	
	private static Board board;
	private static ClueGame game;
	
	@Before
	public void setUp() {
		game = new ClueGame();
		game.loadConfigFiles();
		board = game.getBoard();
		board.calcAdjacencies();
	}
	
	
	//Loading People from File
	//And Testing if the names, color and starting locations match up
	@Test
	public void testplayers() {
		ArrayList<Player> testList =game.returnPlayers();
		
		Player p = testList.get(0);
		BoardCell b = new WalkwayCell(0,11,"W");
		assertEquals(p.getName(),"Tupac");
		assertEquals(p.getColor(), "red");
		assertEquals(p.getLocation(), b);
		
		p = testList.get(2);
		b = new WalkwayCell(21,5,"W");
		assertEquals(p.getName(),"Floyd Mayweather");
		assertEquals(p.getColor(), "purple");
		assertEquals(p.getLocation(),  b);
		
		p = testList.get(5);
		b = new WalkwayCell(4,22,"W");
		assertEquals(p.getName(),"Ronda Rousey");
		assertEquals(p.getColor(), "white");
		assertEquals(p.getLocation(), b);
		
		
	}
	
	//This test also loads for the card.txt file
	@Test
	public void loadCards() {
		//Checks that all cards are read in
		assertEquals(21, game.getDeckSize());
		
		//Checks proper totals of cards are read
		assertEquals(6, game.getPlayersTot());
		assertEquals(6, game.getWeaponsTot());
		assertEquals(9, game.getRoomTot());
		
		//Make sure it contains an element we know it true
		ArrayList<Card> decky = game.getDeck();
		Card c = new Card("Tupac", Card.cardType.PERSON);
		assertTrue(decky.contains(c));
		c = new Card("Hall", Card.cardType.ROOM);
		assertTrue(decky.contains(c));
		c = new Card("Shank", Card.cardType.WEAPON);
		assertTrue(decky.contains(c));
		
	}

	//Tests dealing all the cards to the players in random order
	@Test
	public void dealTest() {
		//Checks the size of remaining deck after the shuffle
		game.deal();
		assertEquals(0, game.getDeckSize());
		
		//Makes sure size of player hands are relatively the same
		ArrayList<Player> parr = game.getPlayers();
		assertTrue(2>(parr.get(0).getHandSize() - parr.get(5).getHandSize()));
		assertTrue(2>(parr.get(0).getHandSize() - parr.get(4).getHandSize()));
		assertTrue(2>(parr.get(0).getHandSize() - parr.get(3).getHandSize()));
		assertTrue(2>(parr.get(0).getHandSize() - parr.get(2).getHandSize()));
		assertTrue(2>(parr.get(0).getHandSize() - parr.get(1).getHandSize()));
		
		//Checks that no player has two of the same cards
		Card c = new Card("Tupac", Card.cardType.PERSON);
		assertFalse(parr.get(0).getHand().contains(c) && parr.get(1).getHand().contains(c));
		assertFalse(parr.get(0).getHand().contains(c) && parr.get(2).getHand().contains(c));
		assertFalse(parr.get(0).getHand().contains(c) && parr.get(3).getHand().contains(c));
		assertFalse(parr.get(0).getHand().contains(c) && parr.get(4).getHand().contains(c));
		assertFalse(parr.get(0).getHand().contains(c) && parr.get(5).getHand().contains(c));
	}
}
