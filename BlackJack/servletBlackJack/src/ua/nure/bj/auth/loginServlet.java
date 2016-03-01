package ua.nure.bj.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.bj.persistence.PersistenceException;
import ua.nure.bj.persistence.UserManager;
import ua.nure.bj.users.User;

@WebServlet("/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger LOG = Logger.getLogger(loginServlet.class
			.getSimpleName());
	UserManager userManager = UserManager.getManager();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (isLogout(request)) {
			doLogout(request, response);
		} else {
			doLogin(request, response);
		}
	}

	private void doLogout(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("");
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		LOG.info("user " + name + " logging in");
		if (invalidParams(name, password)) {
			LOG.debug("bad params " + name + password);
			loginError(request, response, "Wrong username/password");
			return;
		}
		User user;
		try {
			user = userManager.getVerifyUser(name, password);
		} catch (PersistenceException e) {
			LOG.error(e);
			user = null;
		}
		if (user != null) {
			LOG.info("user verified");
			session.setMaxInactiveInterval(10 * 60);
			session.setAttribute("user", user);
			response.sendRedirect("user");
		} else {
			LOG.error("no user" + name + " found");
			String error = "Wrong username/password";
			loginError(request, response, error);
		}

	}

	private boolean invalidParams(String name, String password) {
		return name == null || password == null || name.isEmpty()
				|| password.isEmpty();
	}

	private void loginError(HttpServletRequest request,
			HttpServletResponse response, String error)
			throws ServletException, IOException {
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,
				response);

	}

	private boolean isLogout(HttpServletRequest request) {
		return !(request.getParameter("Logout") == null);
	}
}
