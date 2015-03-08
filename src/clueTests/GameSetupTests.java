package clueTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import junit.framework.Assert;

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
	
	@BeforeClass
	public static void setUp() {
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
		Assert.assertEquals(p.getName(),"Tupac");
		Assert.assertEquals(p.getColor(), "red");
		Assert.assertEquals(p.getLocation(), b);
		
		p = testList.get(2);
		b = new WalkwayCell(21,5,"W");
		Assert.assertEquals(p.getName(),"Floyd Mayweather");
		Assert.assertEquals(p.getColor(), "purple");
		Assert.assertEquals(p.getLocation(),  b);
		
		p = testList.get(5);
		b = new WalkwayCell(4,22,"W");
		Assert.assertEquals(p.getName(),"Ronda Rousey");
		Assert.assertEquals(p.getColor(), "white");
		Assert.assertEquals(p.getLocation(), b);
		
		
	}
	
	@Test
	public void loadCards() {
		Assert.assertEquals(21, game.getDeckSize());
		Assert.assertEquals(6, game.getPlayersTot());
		Assert.assertEquals(6, game.getWeaponsTot());
		Assert.assertEquals(9, game.getRoomTot());
		ArrayList<Card> decky = game.getDeck();
		Card c = new Card("Tupac", Card.cardType.PERSON);
		Assert.assertTrue(decky.contains(c));
		c = new Card("Hall", Card.cardType.ROOM);
		Assert.assertTrue(decky.contains(c));
		c = new Card("Shank", Card.cardType.WEAPON);
		Assert.assertTrue(decky.contains(c));
		
	}

	@Test
	public void dealTest() {
		Assert.assertEquals(0, game.getDeckSize());
		
		ArrayList<Player> parr = game.getPlayers();
		Assert.assertTrue(2>(parr.get(0).getHandSize() - parr.get(5).getHandSize()));
		Assert.assertTrue(2>(parr.get(0).getHandSize() - parr.get(4).getHandSize()));
		Assert.assertTrue(2>(parr.get(0).getHandSize() - parr.get(3).getHandSize()));
		Assert.assertTrue(2>(parr.get(0).getHandSize() - parr.get(2).getHandSize()));
		Assert.assertTrue(2>(parr.get(0).getHandSize() - parr.get(1).getHandSize()));
		
		Card c = new Card("Tupac", Card.cardType.PERSON);
		Assert.assertFalse(parr.get(0).getHand().contains(c) && parr.get(1).getHand().contains(c));
		Assert.assertFalse(parr.get(0).getHand().contains(c) && parr.get(2).getHand().contains(c));
		Assert.assertFalse(parr.get(0).getHand().contains(c) && parr.get(3).getHand().contains(c));
		Assert.assertFalse(parr.get(0).getHand().contains(c) && parr.get(4).getHand().contains(c));
		Assert.assertFalse(parr.get(0).getHand().contains(c) && parr.get(5).getHand().contains(c));
	}
}
