package ua.nure.bj.game;

import java.util.HashMap;

public class Errors {
	private static final HashMap<String, String> noCommandMessage = new HashMap<String, String>();
	private static final HashMap<String, String> errorRestart = new HashMap<String, String>();
	private static final HashMap<String, String> badParams = new HashMap<String, String>();
	public static final String REFILLNOTALLOWED = "Only registered users can refill";
	static {
		noCommandMessage.put("error", "no command");
		noCommandMessage.put("gameStatus", "OK");
	}
	static {
		errorRestart.put("error", "Game is restarted");
		badParams.put("error", "wrong parameters");
	}

	public static HashMap<String, String> getNocommand() {
		return noCommandMessage;
	}

	public static HashMap<String, String> getRestart() {
		return errorRestart;
	}

	public static HashMap<String, String> badParams() {
		return badParams;
	}
}
