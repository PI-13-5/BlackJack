package ua.nure.bj.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class MailSender {
	final static Logger logger = Logger.getLogger(LogManager.class
			.getSimpleName());
	private final static String USERNAME = "blackjakk.jakk@gmail.com";
	private final static String PASSWORD = "blackjakk";
	private final static String RECEIVER="edzthps@hotmail.com";
	static Properties properties;

	static {
		properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.user", USERNAME);
		properties.setProperty("mail.smtp.password", PASSWORD);
	}

	public static void sendMail(String complaint) throws MessagingException {

		Session session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(USERNAME, PASSWORD);
					}
				});
		Message message = new MimeMessage(session);
		message.setContent("Blackjack complaint", "text/plain");
		message.setSubject("BlackJack Complaint");
		message.setText("New user Complaint: " + complaint);
		message.setHeader("=", "=");
		message.setFrom(new InternetAddress("blackjakk.jakk@gmail.com"));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				RECEIVER));
		Transport.send(message);
	}
}
