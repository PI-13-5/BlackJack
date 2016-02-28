package ua.nure.bj.users;

import ua.nure.bj.deckpack.Deck;

/**
 * This class implements the dealer play
 * 
 * @author max
 *
 */
public class DealerPlayer extends AbstractPlayer {

	@Override
	public void play(Deck d) {
		while (sumPoints() < 17)
			getCard(d.takeCard());

	}

}
