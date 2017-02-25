package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PGBDWorker implements BDWorker {

	private Connection connection;
	public static final String defDbName = "postgres";
	public static final String login = "postgres";
	public static final String password = "1337";
	public static final String defName = "obj";

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
				Statement statement = connection.createStatement();
				statement.execute("create database " + name);
				Connection newDbConn = connect(name, login, password);
				statement = newDbConn.createStatement();
				statement.execute("create table " + defName
						+ "(id integer primary key check(id>0), obj_name text, data date, description text check(description = 'Fruit' or description = 'Vegetable'));");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}

	}

	@Override
	public void deleteDatabase(String name) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("drop database " + name + ";");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("establish connection first");
		}

	}

	@Override
	public void cleanTable(String name) {
		if (connection != null) {
			try {
				Statement statement = connection.createStatement();
				statement.executeUpdate("delete from " + name + ";");
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
				Statement statement = connection.createStatement();
				statement.executeUpdate("insert into " + table + " values(" + id + "," + object + ");");
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
				Statement statement = connection.createStatement();
				statement.execute("delete from " + table + " where id = " + id + ";");
				statement.executeUpdate("insert into " + table + " values(" + id + "," + object + ");");
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
				PreparedStatement statement = connection.prepareStatement(
						"select * from " + table + " where " + field + " = ?", ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				statement.setString(1, value);
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
				PreparedStatement statement = connection
						.prepareStatement("delete from " + table + " where " + field + " = ?");
				statement.setString(1, value);
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
				PreparedStatement statement = connection.prepareStatement("delete from " + table + " where id = ?");
				statement.setString(1, id);

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
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				return statement.executeQuery("select * from " + defName + ";");
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
				PreparedStatement statement = connection.prepareStatement("select * from " + defName + " where id = ?;",
						ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				statement.setLong(1, id);
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
