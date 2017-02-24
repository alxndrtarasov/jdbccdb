package bdvisualisation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import worker.BDWorker;

public class SelectionListenerBDVisualizator implements BDVisualizator {

	BDWorker worker;

	@Override
	public BDWorker getWorker() {
		return worker;
	}

	public void setWorker(BDWorker worker) {
		this.worker = worker;
	}

	SelectionListenerBDVisualizator(BDWorker worker) {
		this.worker = worker;
	}

	@Override
	public JScrollPane getTable() {
		final JTable table;
		Object[] columnTitles = { "id", "name", "date", "description" };
		Object[][] rowData = null;
		try {
			ResultSet objs = worker.getAllObjects();
			System.out.println(objs);
			SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			int rsSize = 0;
			if (objs.last()) {
				rsSize = objs.getRow();
				objs.beforeFirst(); // not rs.first() because the rs.next()
									// below will move on, missing the first
									// element
			}
			rowData = new Object[rsSize][4];
			int i = 0;
			while (objs.next()) {
				rowData[i][0] = objs.getObject("id");
				rowData[i][1] = objs.getObject("name");
				rowData[i][2] = df.format(objs.getDate("data"));
				rowData[i][3] = objs.getObject("description");
				i++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new JTable(rowData, columnTitles);
		table.setCellSelectionEnabled(false);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		cellSelectionModel.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selectedData = null;

				int[] selectedRow = table.getSelectedRows();
				int[] selectedColumns = table.getSelectedColumns();

				for (int i = 0; i < selectedRow.length; i++) {
					for (int j = 0; j < selectedColumns.length; j++) {
						selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
					}
				}
				System.out.println("Selected: " + selectedData);

			}
		});

		JScrollPane result = new JScrollPane(table);
		result.setSize(300, 200);
		return result;
	}

}
