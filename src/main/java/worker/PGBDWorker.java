package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PGBDWorker implements BDWorker {

	private Connection connection;
	private static final String defName = "films";

	public Connection connect(String dbName, String login, String password) {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
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

	public void createDatabase(String name) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("create database " + name + ";");
				statement.executeUpdate();
			} catch (SQLException e) {
				System.err.println("establish connection first");
				e.printStackTrace();
			}
		}
		else {
			System.err.println("establish connection first");
		}
	}

	public void deleteDatabase(String name) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("drop database " + name + ";");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.err.println("establish connection first");
		}
	}

	public void cleanTable(String name) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("delete table " + name + ";");
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
				System.err.println("establish connection first");
			}
		}
	

	public void insert(String table, String object) {
		// TODO Auto-generated method stub

	}

	public void update(String table, String object, String id) {
		// TODO Auto-generated method stub

	}

	public ResultSet find(String table, String field, String value) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("select * from ? where ? = ?");
				statement.setString(1, table);
				statement.setString(2, field);
				statement.setString(3, value);
				return statement.executeQuery();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			else {
				System.err.println("establish connection first");
			}
		return null;
	}
	
	public void deleteByFieldValue(String table, String field, String value) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("delete from ? where ? = ?");
				statement.setString(1, table);
				statement.setString(2, field);
				statement.setString(3, value);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			else {
				System.err.println("establish connection first");
			}
	}

	public void deleteById(String table, String id) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement("delete from ? where id = ?");
				statement.setString(1, table);
				statement.setString(2, id);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			else {
				System.err.println("establish connection first");
			}

	}

}
