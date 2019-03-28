package main.com.excilys.persistence;

import java.sql.*;

public final class DAOUtility {

	public static PreparedStatement preparedStatementInitialization(Connection connection, String sql, boolean returnGeneratedKeys, Object... objects) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
	    
	    for (int i = 0; i < objects.length; i++) {
	        preparedStatement.setObject(i + 1, objects[i]);
	    }
	    
	    return preparedStatement;
	}
}