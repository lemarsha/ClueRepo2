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
		//Assert.assertTrue();
		
	}

}
