package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import bdvisualisation.ContentTable;
import worker.BDWorker;
import worker.PGBDWorker;

public class FindForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FindForm(BDWorker worker) {
		setLayout(new GridLayout(3, 2));
		JFrame findResult = new JFrame("Results of search");
		JLabel inputLabel = new JLabel("Input:");
		JLabel fieldLabel = new JLabel("Field:");

		findResult.setSize(400, 400);

		JComboBox<String> fieldChooser = new JComboBox<>();

		fieldChooser.addItem("id");
		fieldChooser.addItem("name");
		fieldChooser.addItem("date");
		fieldChooser.addItem("description");

		JTextField input = new JTextField();

		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (fieldChooser.getSelectedItem().equals("id")) {
					ResultSet objs = null;
					try {
						ResultSet foundObj = worker.find(PGBDWorker.defName, Integer.parseInt(input.getText()));
						if (foundObj.next()) {
							findResult.add(new ContentTable(objs));
							findResult.setVisible(true);
						} else {
							input.setText("Not found");
						}
					} catch (NumberFormatException | SQLException e1) {
						input.setText("Wrong id format");
					}
				} else {
					try {
						findResult.add(new ContentTable(worker.find(PGBDWorker.defName,
								(String) fieldChooser.getSelectedItem(), input.getText())));
						findResult.setVisible(true);
					} catch (Exception e2) {
						input.setText("Not found");
					}
				}
			}
		});

		add(fieldLabel);
		add(fieldChooser);
		add(inputLabel);
		add(input);
		add(ok);

		setSize(300, 200);

		setVisible(true);
	}
}
