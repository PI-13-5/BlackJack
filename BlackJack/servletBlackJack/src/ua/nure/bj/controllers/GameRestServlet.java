package ua.nure.bj.controllers;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import ua.nure.bj.game.Errors;
import ua.nure.bj.persistence.UserManager;
import ua.nure.bj.routing.GamesRouter;
import ua.nure.bj.users.User;
import ua.nure.bj.utils.JSONParser;
import ua.nure.bj.utils.Utils;

@WebServlet("/rest")
public class GameRestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserManager manager = UserManager.getManager();
	GamesRouter router = GamesRouter.getInstance();
	final static Logger logger = Logger.getLogger(GameRestServlet.class
			.getSimpleName());

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (Utils.isNullOrEmpty(action)) {
			respond(response, Errors.badParams());
		}
		if (!isRegistered(request)) {
			String userId = request.getSession().getId();
			HashMap<String, String> responseResult = router
					.handleUnregisteredUserRequest(userId, action);
			respond(response, responseResult);
		} else {
			User user = (User) request.getSession().getAttribute("user");
			String login = user.getLogin();
			HashMap<String, String> responseResult = router.handleUserRequest(
					login, action);
			respond(response, responseResult);
		}
	}

	private boolean isRegistered(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		return user != null;
	}

	private void respond(HttpServletResponse response,
			HashMap<String, String> responseMap)
			throws JsonGenerationException, JsonMappingException, IOException {
		String JSONResult = JSONParser.parseToJson(responseMap);
		ServletOutputStream out = response.getOutputStream();
		allowCrossDomain(response);
		out.print(JSONResult);
	}

	private void allowCrossDomain(HttpServletResponse resp) {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		resp.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");

	}

	@Override
	public void destroy() {
		router.killTimer();
	}

}
