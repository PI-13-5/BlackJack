package ua.nure.bj.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bj.persistence.PersistenceException;
import ua.nure.bj.persistence.UserManager;
import ua.nure.bj.users.User;
import ua.nure.bj.utils.Utils;

@WebServlet("/register")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BALANCE = 3000;
	UserManager manager = UserManager.getManager();
	final static Logger logger = Logger.getLogger(registerServlet.class
			.getSimpleName());

	public registerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("registration.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userLogin = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmpw = request.getParameter("confirmpassword");
		if (Utils.isNullOrEmpty(userLogin, email, password, confirmpw)) {
			response.sendRedirect("registration.jsp");
			return;
		}
		if (!password.equals(confirmpw)) {
			request.setAttribute("errorMessage",
					"Confirmed password doesn't match with the entered one!");
			request.getRequestDispatcher("registration.jsp").forward(request,
					response);
			return;
		}
		try {
			manager.addUser(new User(userLogin, email, password,
					DEFAULT_BALANCE, 0, 0, 0, 0));
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			request.setAttribute("errorMessage", "This login or email is already owned");
			request.getRequestDispatcher("registration.jsp").forward(request,
					response);
			return;
		}
		redirectSuccess(response);
	}

	private void redirectSuccess(HttpServletResponse response)
			throws IOException {
		response.sendRedirect("login");
	}

}
