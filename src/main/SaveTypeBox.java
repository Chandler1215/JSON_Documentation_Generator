package jsonGenerator.quickstart;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <h1>JSaveTypeBox</h1> Class which helps to have 2 objects as one, for better
 * aligning in window
 * 
 * @author Bozieac Artur
 * @version 1.0
 * @since 2020-04-23
 */

public class SaveTypeBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Variables to store label and combobox
	private JLabel saveTypeLabel;
	private JComboBox<String> saveTypeBox;
	/**
	 * Constructor for box (label + combobox).
	 * 
	 * @param labelContent var used to set label content
	 * 
	 */

	public SaveTypeBox(String labelContent) {

		// creates the GUI
		// setting a FlowLayot to have Label and ComboBox close

		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		saveTypeLabel = new JLabel(labelContent);

		saveTypeBox = new JComboBox<String>();
		saveTypeBox.addItem(".txt");
		saveTypeBox.addItem(".json");
		saveTypeBox.addItem(".html");

		// TypeLabel style
		saveTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 12));

		// ComboBox style
		saveTypeBox.setOpaque(true);
		saveTypeBox.setFont(new Font("Tahoma", Font.BOLD, 12));
		saveTypeBox.setBackground(new Color(59, 89, 182));
		saveTypeBox.setForeground(Color.BLACK);

		// adding to layout
		add(saveTypeLabel);
		add(saveTypeBox);

	}

	public String getSelectedFormat() {
		return (String) saveTypeBox.getSelectedItem();
	}

}
