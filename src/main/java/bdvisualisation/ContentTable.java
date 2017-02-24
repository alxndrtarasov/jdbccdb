package bdvisualisation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JPanel;
import javax.swing.JTable;

public class ContentTable extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public ContentTable(ResultSet objs) {
		try {
			Object[] columnTitles = { "id", "name", "date", "description" };
			System.out.println(objs);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			int rsSize = 0;
			if (objs.last()) {
				rsSize = objs.getRow();
				objs.beforeFirst(); // not rs.first() because the rs.next()
									// below will move on, missing the first
									// element
			}
			Object[][] rowData = new Object[rsSize][4];
			int i = 0;
			while (objs.next()) {
				rowData[i][0] = objs.getObject("id");
				rowData[i][1] = objs.getObject("name");
				rowData[i][2] = df.format(objs.getDate("data"));
				rowData[i][3] = objs.getObject("description");
				i++;
			}
			table = new JTable(rowData, columnTitles);
			this.add(table);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}
}
