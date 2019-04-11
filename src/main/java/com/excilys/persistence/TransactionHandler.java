package main.java.com.excilys.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import main.java.com.excilys.exception.DAOException;

public class TransactionHandler<U,R> {

	// ******** INTERFACE *******
	public interface MyConsumer<U,R> {
		public R accept(Connection t, U u) throws SQLException, DAOException;
	};

	// ******** VARIABLES *******
	DAOFactory daoFactory;
	MyConsumer<U,R> myConsumer;
	R result;

	// ******** CONSTRUCTOR *******
	private TransactionHandler(MyConsumer<U,R> myConsumer) {
		this.myConsumer = myConsumer;
		this.daoFactory = DAOFactory.getInstance();
	}

	// ******** METHODS *******
	public static <U,R> TransactionHandler<U,R> create(MyConsumer<U,R> myConsumer) {
		return new TransactionHandler<U,R>(myConsumer);
	}

	public TransactionHandler<U,R> run(U u) throws DAOException {
		try (Connection connection = this.daoFactory.getConnection()) {
			connection.setAutoCommit(false);
			try {
				this.result = this.myConsumer.accept(connection, u);
			} catch (SQLException e) {
				connection.rollback();
				connection.setAutoCommit(true);
				throw new DAOException(e);
			} catch (DAOException e) {
				connection.rollback();
				connection.setAutoCommit(true);
				throw new DAOException(e);
			}
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return this;
	}

	public R getResult() {
		return this.result;
	}
}