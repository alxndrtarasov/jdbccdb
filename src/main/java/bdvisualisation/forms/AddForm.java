package bdvisualisation.forms;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import worker.BDWorker;
import worker.PGBDWorker;

public class AddForm extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddForm(BDWorker worker) {
		setLayout(new GridLayout(5, 2));

		JLabel idLabel = new JLabel("id:");
		JLabel nameLabel = new JLabel("name:");
		JLabel dateLabel = new JLabel("date:");
		JLabel descriptionLabel = new JLabel("description:");

		JTextField idField = new JTextField();
		JTextField name = new JTextField();
		JDateChooser date = new JDateChooser();
		JComboBox<String> description = new JComboBox<>();

		description.addItem("Fruit");
		description.addItem("Vegetable");

		JButton ok = new JButton("Ok");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pattern.matches("[A-Za-z]{0,99}", name.getText())
						&& Pattern.matches("[0-9]{0,99}", name.getText())) {
					SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
					try {
						int id = Integer.decode(idField.getText());
						worker.insert(PGBDWorker.defName, id,
								name.getText() + "," + "'" + df.parse(df.format(date.getDate())) + "'" + ","
										+ description.getSelectedItem().toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					name.setText("");
				} else {
					name.setText("Use only English letters for name and numbers for id");
				}
			}
		});

		add(idLabel);
		add(idField);
		add(nameLabel);
		add(name);
		add(dateLabel);
		add(date);
		add(descriptionLabel);
		add(description);
		add(ok);

		setSize(300, 200);

		setVisible(true);
	}
}
