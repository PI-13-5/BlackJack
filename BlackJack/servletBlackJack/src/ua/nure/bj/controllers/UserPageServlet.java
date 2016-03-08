package ua.nure.bj.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.bj.persistence.PersistenceException;
import ua.nure.bj.persistence.UserManager;
import ua.nure.bj.users.User;

@WebServlet("/user")
public class UserPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserManager userManager = UserManager.getManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		try {
			User newUser = userManager.getUser(user.getLogin());
			request.getSession().setAttribute("user", newUser);
			request.getRequestDispatcher("/WEB-INF/userpage.jsp").forward(request, response);
		} catch (PersistenceException | NullPointerException e) {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
