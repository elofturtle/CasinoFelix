package casino;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;


import fk.examples.onlinecasino.model.Player;
import fk.examples.onlinecasino.service.PlayerService;

public class PlayerServiceTest {

	private final PlayerService ps;
	
	public PlayerServiceTest() {
		ps = new PlayerService();
	}
	
	@Test
	public void testPlayerSaldoTransfer() {
		Player p1 = new Player("0001", "Robin", "Hood", 100, "robin.hood@gmail.com", "monkey123");
		Player p2 = new Player("0002", "Joakim", "von Anka", 1_000_000, "rikjoakim@gmail.com", "anka123");

		boolean result = ps.transfer(p1, p2, 100);
		assertTrue(result);
	}
	
	@Test
	public void testPlayerSaldoOverdrawn() {
		Player p1 = new Player("0001", "Robin", "Hood", 100, "robin.hood@gmail.com", "monkey123");
		Player p2 = new Player("0002", "Joakim", "von Anka", 1_000_000, "rikjoakim@gmail.com", "anka123");

		boolean result = ps.transfer(p1, p2, 100000);
		assertFalse(result);
	}
	
	@Test
	public void testPlayerSaldoIntegrity() {
		Player p1 = new Player("0001", "Robin", "Hood", 100, "robin.hood@gmail.com", "monkey123");
		Player p2 = new Player("0002", "Joakim", "von Anka", 1_000_000, "rikjoakim@gmail.com", "anka123");
		
		int p1Wallet = p1.getCredit();
		int p2Wallet = p2.getCredit();
		int expectedTotal = p1Wallet + p2Wallet;
		
		boolean result = ps.transfer(p1, p2, 100);
		
		int newTotal = p1.getCredit() + p2.getCredit();
		
		assertTrue(expectedTotal == newTotal);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testPlayerNoNegativeTransactions() {
		Player p1 = new Player("0001", "Robin", "Hood", 100, "robin.hood@gmail.com", "monkey123");
		Player p2 = new Player("0002", "Joakim", "von Anka", 1_000_000, "rikjoakim@gmail.com", "anka123");
		
		ps.transfer(p1, p2, -100);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPlayerDifferentPlayers() {
		Player p1 = new Player("0001", "Robin", "Hood", 100, "robin.hood@gmail.com", "monkey123");
		Player p2 = new Player("0002", "Joakim", "von Anka", 1_000_000, "rikjoakim@gmail.com", "anka123");

		ps.transfer(p1, p1, 100);
	}
}
