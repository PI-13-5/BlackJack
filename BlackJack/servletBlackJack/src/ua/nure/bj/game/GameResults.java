package ua.nure.bj.game;

import ua.nure.bj.users.*;

/**
 * This class implements methods for definition of status and result of the game
 * 
 * @author max
 *
 */
public class GameResults {
	/**
	 * This method returns the result of the game
	 */
	public static String gameResult(DealerPlayer d, HumanPlayer hp) {
		if (hp.isBusted()) {
			return "PLAYER_BUSTED";
		} else if (d.isBusted()) {
			return "DEALER_BUSTED";
		} else if (d.sumPoints() > hp.sumPoints())
			return "DEALER_WINS";
		else if (d.sumPoints() < hp.sumPoints())
			return "PLAYER_WINS";
		else
			return "TIE";
	}

	/**
	 * This method shows whether you can continue. If you can'n continue, you
	 * get the result
	 */
	public static String gameStatus(DealerPlayer d, HumanPlayer hp) {
		if (hp.sumPoints() < 22)
			return "OK";
		else
			return gameResult(d, hp);
	}

}
