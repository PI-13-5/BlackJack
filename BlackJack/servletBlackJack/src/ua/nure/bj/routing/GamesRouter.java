package ua.nure.bj.routing;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import ua.nure.bj.game.BlackJackGame;
import ua.nure.bj.game.UnregisteredBlackJackGame;

public class GamesRouter {
	private static final long CLEANER_COOLDOWN = 300_000;
	private static final long GAME_MAX_NON_ACTIVE = CLEANER_COOLDOWN;
	Timer cleaner = new Timer();
	private ConcurrentHashMap<String, BlackJackGame> games = new ConcurrentHashMap<String, BlackJackGame>();
	private ConcurrentHashMap<String, UnregisteredBlackJackGame> uregisteredUserGames = new ConcurrentHashMap<String, UnregisteredBlackJackGame>();

	public GamesRouter() {
		setTimer();
	}

	private void setTimer() {
		cleaner.schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				Date now = new Date();
				int gamesKilled = 0;
				Enumeration<String> keys = games.keys();
				while(keys.hasMoreElements()){
					String elem=keys.nextElement();
					Date launched = games.get(elem).getLastLaunched();
					long diff = now.getTime() - launched.getTime();// as given
					long seconds = TimeUnit.MILLISECONDS.toMillis(diff);
					if (seconds > GAME_MAX_NON_ACTIVE) {
						games.remove(elem);
						gamesKilled++;
					}
				}
				if (gamesKilled > 0) {
					System.out.println("router's cleaner: games killed: "
							+ gamesKilled);
				}
			}
		}, CLEANER_COOLDOWN, CLEANER_COOLDOWN);

	}

	public HashMap<String, String> handleUserRequest(String userId,
			String command) {
		HashMap<String, String> result = getUserGame(userId).respond(userId,
				command);
		return result;
	}

	public HashMap<String, String> handleUnregisteredUserRequest(String userId,
			String command) {
		HashMap<String, String> result = getUnregisteredUserGame(userId)
				.respond(userId, command);
		return result;
	}

	private UnregisteredBlackJackGame getUnregisteredUserGame(String userId) {
		try {
			UnregisteredBlackJackGame game = uregisteredUserGames.get(userId);
			if (game == null) {
				throw new NullPointerException();
			}
			return game;
		} catch (NullPointerException ex) {
			System.out.println("creating new game for unregistered "+userId);
			uregisteredUserGames.put(userId, new UnregisteredBlackJackGame());
			return uregisteredUserGames.get(userId);
		}
	}

	private BlackJackGame getUserGame(String userId) {
		try {
			BlackJackGame game = games.get(userId);
			if (game == null) {
				throw new NullPointerException();
			}
			return game;
		} catch (NullPointerException ex) {
			System.out.println("creating new game for "+userId);
			games.put(userId, new BlackJackGame(userId));
			return games.get(userId);
		}
	}

	public void killTimer() {
		cleaner.cancel();
	}

	public static GamesRouter getInstance() {
		return new GamesRouter();
	}
}
