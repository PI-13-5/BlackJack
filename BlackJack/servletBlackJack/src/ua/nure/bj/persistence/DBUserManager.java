package ua.nure.bj.persistence;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.naming.OperationNotSupportedException;

import ua.nure.bj.users.User;

public class DBUserManager extends UserManager {

	protected DBUserManager() {
	}

	@Override
	public synchronized void addUser(User userToAdd)
			throws PersistenceException {
		try {
			UserUtil.addUser(userToAdd);
		} catch (SQLException | ClassNotFoundException
				| NoSuchAlgorithmException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

	@Override
	public synchronized int getBalance(String userLogin)
			throws PersistenceException {
		try {
			return UserUtil.getBalance(userLogin);
		} catch (SQLException | ClassNotFoundException
				| NoSuchAlgorithmException e) {
			throw new PersistenceException("failed to retrieve balance for "
					+ userLogin);
		}
	}

	@Override
	public synchronized User getVerifyUser(String name, String password)
			throws PersistenceException {
		User user = null;
		try {
			user = UserUtil.getUser(name, password);
		} catch (SQLException | NoSuchAlgorithmException
				| ClassNotFoundException e) {
			throw new PersistenceException(" no such user");
		}
		return user;
	}

	@Override
	public synchronized Collection<User> getAllUsers()
			throws PersistenceException {
		try {
			return UserUtil.getAllUsers();
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceException("cant get user list");
		}
	}

	@Override
	public synchronized User deleteUser(String userNameToDelete)
			throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}

	@Override
	public void setBalance(String username, int newChipCount)
			throws PersistenceException {
		try {
			UserUtil.setBalance(username, newChipCount);
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		}

	}

	@Override
	public void updateUserStats(String username, int win, int lost, int draws,
			int balance) throws PersistenceException {
		try {
			UserUtil.updateUserStats(username, balance, win, lost, draws);
		} catch (SQLException | ClassNotFoundException
				| NoSuchAlgorithmException e) {
			throw new PersistenceException("failed to update stats: "
					+ e.getMessage());
		}
		System.out.println(username + " update balance to " + balance);
	}

	@Override
	public User getUser(String login) throws PersistenceException {
		Connection connection;
		try {
			connection = Connector.getConnection();
			PreparedStatement ps = connection
					.prepareStatement("Select * from users where login = ?;");
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
				return new User(uLogin, email, uPassword, balance,
						games_played, games_won, games_lost, games_draws);
			}
			connection.close();
			return null;
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		}
	}

}
