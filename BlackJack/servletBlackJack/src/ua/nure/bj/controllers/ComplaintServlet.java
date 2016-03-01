package ua.nure.bj.controllers;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.bj.utils.MailSender;
import ua.nure.bj.utils.Utils;

@WebServlet("/complain")
public class ComplaintServlet extends HttpServlet {

	private static final long serialVersionUID = -3597424740864725582L;
	final static Logger logger = Logger.getLogger(ComplaintServlet.class
			.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/view/complaint.jsp").forward(req,
				resp);
	}

	private void redirectSuccess(HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		req.getRequestDispatcher("/WEB-INF/view/complaintsuccess.jsp").forward(
				req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String complaintText = req.getParameter("message");
		if (!Utils.isNullOrEmpty(complaintText)) {
			processSendComplaint(complaintText);
			redirectSuccess(req, resp);
			return;
		} else {
			req.setAttribute("errormessage",
					"Feedback should not be empty, please<br/>");
			req.getRequestDispatcher("WEB-INF/view/complaint.jsp").forward(req,
					resp);
		}
	}

	private void processSendComplaint(String complaintText) {
		try {
			MailSender.sendMail(complaintText);
		} catch (MessagingException e) {
			logger.error("error while sending complaint via mail, complaint text: "
					+ complaintText);
		}

	}
}
