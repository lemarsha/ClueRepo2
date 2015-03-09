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
	
	@Test
	public void accusation() {
		//Temp public victory to test for the accusation
		game.victory.person = "Tupac";
		game.victory.place = "Library";
		game.victory.weapon = "Ak-47";
		
		//Temperary guess for the solution
		Solution temp = new Solution();
		temp.person = "Tupac";
		temp.place = "Library";
		temp.weapon="Ak-47";
		Assert.assertTrue(game.checkAccusation(temp));
		
		temp.weapon="shank";
		Assert.assertFalse(game.checkAccusation(temp));
		
		temp.place = "Kitchen";
		temp.weapon="Ak-47";
		Assert.assertFalse(game.checkAccusation(temp));
		
		temp.person = "Nikki Manaj";
		temp.place = "Library";
		Assert.assertFalse(game.checkAccusation(temp));
	}

}
