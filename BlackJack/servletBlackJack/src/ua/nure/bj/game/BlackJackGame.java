package ua.nure.bj.game;

import java.util.HashMap;

import ua.nure.bj.persistence.PersistenceException;
import ua.nure.bj.persistence.UserManager;
import ua.nure.bj.users.User;

public class BlackJackGame extends AbstractBJGame {
	public BlackJackGame(String registeredPlayerLogin) {
		try {
			User user = manager.getUser(registeredPlayerLogin);
			logger.debug(user + "setting user");
			player.setUser(user);
		} catch (Exception e) {
			logger.error("error while setting user, user is now null");
		}
	}

	UserManager manager = UserManager.getManager();

	protected void setBalance(String userId, int chipCount) {
		try {
			manager.setBalance(userId, player.getChipCount());
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	protected void refill(HashMap<String, String> result, String username) {
		player.refill();
		try {
			manager.setBalance(username, player.getChipCount());
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
		}
		logger.debug("refilling player" + username + " his balance is now "
				+ player.getChipCount());
		result.put("refill", Integer.toString(player.getChipCount()));
	}

	@Override
	protected void processGameOver(String userId, HashMap<String, String> result) {
		result.put("balance", Integer.toString(player.getChipCount()));
		String gameResult = result.get("gameStatus");
		logger.debug(player.getUser() + " is user stats");
		int win = player.getUser().getGames_won();
		int lost = player.getUser().getGames_lost();
		int draws = player.getUser().getGames_draws();
		int balance = player.getChipCount();
		logger.debug("BEFORE UPDATING STATS" + "win" + win + "lost" + lost
				+ "draws" + draws);
		if (gameResult == "PLAYER_WINS" || gameResult == "DEALER_BUSTED") {
			win++;
		} else if (gameResult == "DEALER_WINS" || gameResult == "PLAYER_BUSTED") {
			lost++;
		} else if (gameResult == "TIE") {
			draws++;
		}
		logger.debug("After UPDATING STATS" + "win" + win + "lost" + lost
				+ "draws" + draws);
		try {
			manager.updateUserStats(userId, win, lost, draws, balance);
			updateInternUser(player.getUser(), win, lost, draws, balance);
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
		}
		Restart();
	}

	private void updateInternUser(User user, int win, int lost, int draws, int balance) {
		user.setGames_won(win);
		user.setGames_lost(lost);
		user.setGames_draws(draws);
		user.setBalance(balance);

	}

	@Override
	protected void hardRestart(HashMap<String, String> result) {
		Restart();
		result.put("DEBUG", " Game has been restarted! Balance is default");
		state = GameState.Finished;
		logger.debug("hard restart becasue of leaving page!");
	}

	@Override
	protected void processGetBalance(String userId,
			HashMap<String, String> result) {
		int playersMoney;
		try {
			playersMoney = manager.getBalance(userId);
		} catch (PersistenceException e) {
			logger.error("blackjack game setbalance exception "
					+ e.getMessage());
			playersMoney = 0;
		}
		player.setChipCount(playersMoney);
		result.put("balance", Integer.toString(player.getChipCount()));
	}
}
