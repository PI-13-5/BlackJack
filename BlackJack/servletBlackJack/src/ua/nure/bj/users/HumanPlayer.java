package ua.nure.bj.users;

import ua.nure.bj.deckpack.Deck;

/**
 * This class implements human BlackJack game
 * 
 * @author max
 * 
 */
public class HumanPlayer extends AbstractPlayer {

	int pot;// a pot between dealer and player
	User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		if (pot <= 0 || pot > chipCount)
			throw new IllegalArgumentException();
		this.pot = pot;
	}

	/**
	 * Allows user to take money to a blackJack table
	 * 
	 * @param amount
	 *            stands for certain sum of money less than total amount of
	 *            money on player's account
	 */
	public void TakeMoneyToTable(int amount) {

	}

	@Override
	public void play(Deck d) {
		getCard(d.takeCard());
	}

	public String getLastCardVisualisation() {
		return playerCards.get(playerCards.size() - 1).getVisualization();
	}
}
