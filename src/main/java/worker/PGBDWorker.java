package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PGBDWorker implements BDWorker {

	private Connection connection;
	private static final String defName = "obj";

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
				Statement statement = connection.createStatement();
				statement.execute("create database " + name);
				statement.execute("create table " + defName
						+ "(id integer primary key check(id>0), obj_name text, data date, description text check(description = 'Fruit' or description = 'Vegetable'));");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
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
		} else {
			System.err.println("establish connection first");
		}

	}

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

	public void insert(String table, String object) {
		// TODO Auto-generated method stub

	}

	public void update(String table, String object, String id) {
		// TODO Auto-generated method stub

	}

	public ResultSet find(String table, String field, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteByFieldValue(String table, String field, String value) {
		// TODO Auto-generated method stub

	}

	public void deleteById(String table, String id) {
		// TODO Auto-generated method stub

	}

}
