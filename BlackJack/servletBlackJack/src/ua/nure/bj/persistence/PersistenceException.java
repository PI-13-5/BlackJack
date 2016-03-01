package ua.nure.bj.persistence;

public class PersistenceException extends Exception {

	private static final long serialVersionUID = 8614988058671510260L;

	public PersistenceException(String reason) {
		super(reason);
	}
}
