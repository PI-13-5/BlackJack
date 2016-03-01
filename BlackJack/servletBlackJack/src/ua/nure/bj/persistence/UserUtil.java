package ua.nure.bj.persistence;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;








import ua.nure.bj.users.User;


public class UserUtil {

	private static String ALGORITHM = "MD5";

	public static User getUser(String login, String password)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = Connector.getConnection();
		PreparedStatement ps = connection.prepareStatement("Select * from users where login = ? and password = ?;");
		ps.setString(1, login);
		ps.setString(2, Arrays.toString(getHash(password, ALGORITHM)));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String uLogin = rs.getString(2);
			String email = rs.getString(3);
			String uPassword = rs.getString(4);
			int balance = rs.getInt(5);
			int games_won = rs.getInt(6);
			int games_lost = rs.getInt(7);
			int games_draws = rs.getInt(8);
			int games_played = games_won + games_lost + games_draws;
			connection.close();
			return new User(uLogin, email, uPassword, balance, games_played, games_won, games_lost, games_draws);
		}
		connection.close();
		return null;

	}

	public static List<User> getAllUsers() throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<User>();
		Connection connection = Connector.getConnection();
		PreparedStatement ps = connection.prepareStatement("Select * from users;");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String uLogin = rs.getString(2);
			String email = rs.getString(3);
			String uPassword = rs.getString(4);
			int balance = rs.getInt(5);
			int games_won = rs.getInt(6);
			int games_lost = rs.getInt(7);
			int games_draws = rs.getInt(8);
			int games_played = games_won + games_lost + games_draws;
			users.add(new User(uLogin, email, uPassword, balance, games_played, games_won, games_lost, games_draws));
		}
		connection.close();
		return users;
	}

	public static boolean addUser(User user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection connection = Connector.getConnection();
		PreparedStatement pst = connection.prepareStatement(
				"INSERT INTO users (login, email, password, balance, games_won, games_lost, games_draws) VALUES(?, ?, ?, ?, ?, ?, ?);");
		pst.setString(1, user.getLogin());
		pst.setString(2, user.getEmail());
		pst.setString(3, Arrays.toString(getHash(user.getPassword(), ALGORITHM)));
		pst.setInt(4, user.getBalance());
		pst.setInt(5, user.getGames_won());
		pst.setInt(6, user.getGames_lost());
		pst.setInt(7, user.getGames_draws());
		pst.executeUpdate();
		connection.close();
		return true;
	}

	public static boolean updateUserInfo(String userLogin, String emailToUpdate, String passToUpdate)
			throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
		Connection connection = Connector.getConnection();
		PreparedStatement pst = connection.prepareStatement("UPDATE users SET email = ?, password = ? Where login = ?");
		pst.setString(1, emailToUpdate);
		pst.setString(2, Arrays.toString(getHash(passToUpdate, ALGORITHM)));
		pst.setString(3, userLogin);
		pst.executeUpdate();
		connection.close();
		return true;
	}

	public static boolean setBalance(String userLogin, int balanceToUpdate) throws ClassNotFoundException, SQLException{
		Connection connection = Connector.getConnection();
		PreparedStatement pst = connection
				.prepareStatement("UPDATE users SET balance = ? WHERE login = ?");
		pst.setInt(1, balanceToUpdate);
		pst.setString(2, userLogin);
		pst.executeUpdate();
		connection.close();
		return true;
	}
	
	public static boolean updateUserStats(String userLogin, int balanceToUpdate, int gamesWon, int gamesLost,
			int gamesDraws) throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
		Connection connection = Connector.getConnection();
		PreparedStatement pst = connection
				.prepareStatement("UPDATE users SET balance = ?, games_won = ?, games_lost = ?, games_draws = ? WHERE login = ?");
		pst.setInt(1, balanceToUpdate);
		pst.setInt(2, gamesWon);
		pst.setInt(3, gamesLost);
		pst.setInt(4, gamesDraws);
		pst.setString(5, userLogin);
		pst.executeUpdate();
		connection.close();
		return true;
	}

	public static boolean updateUserBalance(String userLogin, int balanceToUpdate)
			throws SQLException, NoSuchAlgorithmException, ClassNotFoundException {
		Connection connection = Connector.getConnection();
		PreparedStatement pst = connection.prepareStatement("UPDATE users SET balance = ?  Where login = ?");
		pst.setInt(1, balanceToUpdate);
		pst.setString(2, userLogin);
		pst.executeUpdate();
		connection.close();
		return true;
	}

	public static boolean deleteUser(String userLogin) throws ClassNotFoundException, SQLException {
		Connection connection = Connector.getConnection();
		PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE login = ?;");
		pst.setString(1, userLogin);
		pst.executeUpdate();
		connection.close();
		return true;
	}

	private static byte[] getHash(String pass, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.update(pass.getBytes());
		byte[] hash = md.digest();
		return hash;
	}

	public static User getUser(String login)
			throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		Connection connection = Connector.getConnection();
		PreparedStatement ps = connection.prepareStatement("Select * from users where login = ? ;");
		ps.setString(1, login);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String uLogin = rs.getString(2);
			String email = rs.getString(3);
			String uPassword = rs.getString(4);
			int balance = rs.getInt(5);
			int games_won = rs.getInt(6);
			int games_lost = rs.getInt(7);
			int games_draws = rs.getInt(8);
			int games_played = games_won + games_lost + games_draws;
			connection.close();
			return new User(uLogin, email, uPassword, balance, games_played, games_won, games_lost, games_draws);
		}
		connection.close();
		return null;
	}

	public static int getBalance(String userLogin) throws ClassNotFoundException, NoSuchAlgorithmException, SQLException {
		User user=getUser(userLogin);
		return user.getBalance();
	}
}