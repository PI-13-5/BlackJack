package ua.nure.bj.users;

import java.util.*;

import ua.nure.bj.deckpack.*;

/**
 * This class implements basic functional of a player at the BlackJack table
 * (for human and robot)
 * 
 * @author max
 * 
 */

public abstract class AbstractPlayer extends RegisteredUser {

	private static final int DEFAULT_STARTSUM = 1000;
	List<Card> playerCards = new ArrayList<Card>();

	public List<Card> getPlayerCards() {
		return playerCards;
	}

	int chipCount = DEFAULT_STARTSUM;// the amount of player's chips at the
										// table. Equals 1/5 until we don't have
										// table limitations

	/**
	 * Take the card
	 */
	public void getCard(Card c) {
		playerCards.add(c);
	}

	public int getChipCount() {
		return chipCount;
	}

	public void setChipCount(int chipCount) {
		this.chipCount = chipCount;

	}

	public void refill() {
		this.chipCount += 1000;
	}

	/**
	 * Display cards that player has taken from the deck
	 */
	public String displayCards() {
		String str = "";
		for (Card c : playerCards)
			str += c.getVisualization() + ";";
		return str;
	}

	/**
	 * Get the sum of the points that player has right now
	 */
	public int sumPoints() {
		int sum = 0;
		for (Card c : playerCards)
			sum += c.getcardPoints();
		return sum;
	}

	/**
	 * if player took more than 21 points
	 */
	public Boolean isBusted() {
		if (sumPoints() > 21) {
			return true;
		}
		return false;
	}

	public void update() {
		playerCards.clear();
	}

	/**
	 * The logic of the dealer and players
	 */
	abstract public void play(Deck d);
}
