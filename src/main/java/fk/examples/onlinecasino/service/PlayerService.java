package fk.examples.onlinecasino.service;

import fk.examples.onlinecasino.model.Player;

public class PlayerService {
	public boolean  transfer(Player p1, Player p2, int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Du får inte överföra skulder");
		}
		if(p1 == p2) {
			throw new IllegalArgumentException("Systemet tillåter inte transaktioner till dig själv");
		}
		int p1Wallet = p1.getCredit();
		if (p1Wallet >= amount) {
			p1.setCredit(p1Wallet - amount);
			p2.setCredit(p2.getCredit() +  p1Wallet);
			return true;
		}
		else return false;
	}
}
