import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import worker.BDWorker;
import worker.PGBDWorker;

/**
 * Created by alta0816 on 20.02.2017.
 */
public class BDWorkerTest {
	private static final String defDbName = "postgres";
	private static BDWorker worker;
	private static final String login = "postgres";
	private static final String password = "1337";

	@BeforeClass
	public static void setUpBeforeClass() {
		worker = new PGBDWorker();
	}

	@Test
	public void testConnect() throws SQLException {
		Connection result = worker.connect(defDbName, login, password);
		PreparedStatement statement = result.prepareStatement("select * from test");
		ResultSet set = statement.executeQuery();
		if (set.next()) {
			assertEquals(set.getString("id"), "1337");
		}
	}

	@Test
	public void testCreateAndDeleteDatabase() throws SQLException {
		String dbName = "new_DB";
		worker.createDatabase(dbName);
		Connection connetctionToNew = worker.connect(dbName, login, password);
		connetctionToNew.close();
		worker.connect(defDbName, login, password);
		worker.deleteDatabase(dbName);
	}

	//
	@Test
	public void testCleanTable() {
		worker.cleanTable("");
	}
	//
	// @Test
	// public void testConnect() {
	// worker.connect("localhost", "1337");
	// }
	//
	// @Test
	// public void testConnect() {
	// worker.connect("localhost", "1337");
	// }
	//
	// @Test
	// public void testConnect() {
	// worker.connect("localhost", "1337");
	// }
	//
	// @Test
	// public void testConnect() {
	// worker.connect("localhost", "1337");
	// }
	//
	// @Test
	// public void testConnect() {
	// worker.connect("localhost", "1337");
	// }
	//
	// @Test
	// public void testConnect() {
	// worker.connect("localhost", "1337");
	// }
}
