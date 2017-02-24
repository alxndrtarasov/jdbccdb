package bdvisualisation;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import bd.BDWorker;
import bd.SimpleBDWorker;
import objtype.Obj;

public class JButtonTableExample extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BDWorker worker = new SimpleBDWorker("db.txt");

	public JButtonTableExample() {
		super("JButtonTable Example");
		DefaultTableModel dm = new DefaultTableModel();
		Object[] columnTitles = { "id", "name", "date", "description" };
		List<Obj> allObjects = worker.getAllObjects();
		System.out.println(worker.getAllObjects());
		Object[][] rowData = new Object[allObjects.size()][4];
		for (int i = 0; i < allObjects.size(); i++) {
			Obj currentObj = allObjects.get(i);
			rowData[i][0] = currentObj.getId();
			rowData[i][0] = currentObj.getName();
			rowData[i][0] = currentObj.getDate();
			rowData[i][0] = currentObj.getDescription();
		}
		dm.setDataVector(rowData, columnTitles);

		JTable table = new JTable(dm);
		table.getColumn("Button").setCellRenderer(new ButtonRenderer());
		table.getColumn("Button").setCellEditor(new ButtonEditor(new JCheckBox()));
		table.getComponentAt(1, 4);
		JScrollPane scroll = new JScrollPane(table);
		getContentPane().add(scroll);
		setSize(400, 100);
		setVisible(true);
		table.setCellSelectionEnabled(false);
	}

	public static void main(String[] args) {

		JButtonTableExample frame = new JButtonTableExample();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}

/**
 * @version 1.0 11/09/98
 */

class ButtonRenderer extends JButton implements TableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ButtonRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

/**
 * @version 1.0 11/09/98
 */

class ButtonEditor extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JButton button;

	private String label;

	private boolean isPushed;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		if (isPushed) {
			//
			//
			JOptionPane.showMessageDialog(button, label + ": Ouch!");
			// System.out.println(label + ": Ouch!");
		}
		isPushed = false;
		return new String(label);
	}

	@Override
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}

	@Override
	protected void fireEditingStopped() {
		super.fireEditingStopped();
	}
}