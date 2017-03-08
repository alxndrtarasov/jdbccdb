package worker;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PGBDWorker implements BDWorker {

	private Connection connection;
	public static final String defDbName = "postgres";
	public static final String login = "postgres";
	public static final String password = "1337";
	public static String defName = "obj";

	@Override
	public Connection connect(String dbName, String login, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String url = "jdbc:postgresql://localhost:5433/" + dbName.toLowerCase();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, login, password);
		} catch (SQLException e) {
			System.err.println("can not establish connection");
			e.printStackTrace();
		}
		connection = conn;
		return conn;
	}

	@Override
	public void createDatabase(String name) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call create_table(?)}");
				statement.setString(1, name);
				statement.execute();
				defName = name;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}

	}

	public void setDefName(String defName) {
		PGBDWorker.defName = defName;
	}

	@Override
	public void deleteDatabase(String name) throws SQLException {
		if (connection != null) {
			CallableStatement statement = connection.prepareCall("{call delete_table(?)}");
			statement.setString(1, name);
			statement.execute();
		} else {
			System.err.println("establish connection first");
		}

	}

	@Override
	public void cleanTable(String name) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call clean_table(?)}");
				statement.setString(1, name);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
	}

	@Override
	public void insert(String table, int id, String object) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call insert_into(?,?,?)}");
				statement.setString(1, table);
				statement.setInt(2, id);
				statement.setString(3, object);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
	}

	@Override
	public void update(String table, String object, String id) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call upd(?,?,?)}");
				statement.setString(1, table);
				statement.setString(2, object);
				statement.setString(3, id);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
	}

	@Override
	public ResultSet find(String table, String field, String value) {
		if (connection != null) {
			try {

				CallableStatement statement = connection.prepareCall("{call find(?,?,?)}",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				statement.setString(1, table);
				statement.setString(2, field);
				statement.setString(3, value);
				return statement.executeQuery();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
		return null;
	}

	@Override
	public void deleteByFieldValue(String table, String field, String value) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call del(?,?,?)}");
				statement.setString(1, table);
				statement.setString(2, field);
				statement.setString(3, value);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
	}

	@Override
	public void deleteById(String table, String id) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call del_by_id(?,?)}");
				statement.setString(1, table);
				statement.setString(2, id);
				statement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}

	}

	@Override
	public ResultSet getAllObjects() {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call all_objs(?)}",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				statement.setString(1, defName);
				return statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
		return null;
	}

	@Override
	public ResultSet find(String table, int id) {
		if (connection != null) {
			try {
				CallableStatement statement = connection.prepareCall("{call find_by_id(?,?)}",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				statement.setString(1, table);
				statement.setInt(2, id);
				return statement.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}
		return null;
	}

}
