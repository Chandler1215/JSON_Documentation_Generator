package jsonGenerator.quickstart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextPane;

/**
 * <h1>JFilePicker</h1> Class created to have a combination of label + textFiled
 * + button + fileChooser
 * 
 * @author Bozieac Artur
 * @version 1.1
 * @since 2020-04-23
 */

public class JFilePicker extends JPanel {

	// Name of the field and button content
	private String textFieldLabel;
	private String buttonLabel;
	private int fieldWidth = 350;
	

	// FilePicker components
	private JLabel label;
	private JTextPane textField;
	private JButton button;
	private JFileChooser fileChooser;

	// Unchangeable vars, to switch between Open dialog and Save dialog
	private int mode;
	public static final int MODE_OPEN = 1;
	public static final int MODE_SAVE = 2;

	/**
	 * File Picker constructor (takes 2 strings, one for name of the field, another
	 * for button name)
	 * 
	 * @param textFieldLabel represents name of the field
	 * @param buttonLabel    represents button name
	 */
	public JFilePicker(String textFieldLabel, String buttonLabel) {

		this.textFieldLabel = textFieldLabel;
		this.buttonLabel = buttonLabel;

		// file chooser instantiation
		fileChooser = new JFileChooser();
		
		
		// setting flowlauout to have elements in one row
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// creates the GUI ( components styles )
		
		label = new JLabel(textFieldLabel);
		
		label.setFont(new Font("Tahoma", Font.BOLD, 12));

		
		textField = new JTextPane();
		textField.setPreferredSize(new Dimension(fieldWidth, 20));
		
		
		button = new JButton(buttonLabel);
		button.setBackground(new Color(59, 89, 182));
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
		button.setFont(new Font("Tahoma", Font.BOLD, 12));

		button.addActionListener(new ActionListener() {
			/**
			 * Anonymous method which handles action when "browse" button is pushed Calls
			 * buttonActionPerformed()
			 * 
			 * @param evt event which was performed
			 */
			public void actionPerformed(ActionEvent evt) {
				buttonActionPerformed(evt);
			}
		});

		// adding elements to layout
		add(label, BorderLayout.WEST);
		add(textField, BorderLayout.CENTER);
		add(button, BorderLayout.EAST);
		
	}

	/**
	 * Method to verify Open Mode and copy the absolute path to the text field when
	 * Open/Save buttons are selected
	 * 
	 * @param evt Performed action
	 */
	private void buttonActionPerformed(ActionEvent evt) {

		if (mode == MODE_OPEN) {
			if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		} else if (mode == MODE_SAVE) {
			if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}

	/**
	 * Method to add file filter to the fileChooser, updates description of the
	 * files and sets a filter on extension
	 * 
	 * @param extension   Represents selected or the only extension which can be
	 *                    chosen
	 * @param description Represents description of file extensions constraints
	 */
	public void addFileTypeFilter(String extension, String description) {
		FileTypeFilter filter = new FileTypeFilter(extension, description);
		fileChooser.addChoosableFileFilter(filter);
	}

	/**
	 * Setter for mode var
	 * 
	 * @param mode variable used to choose in which way the dialog will be opened
	 */
	public void setMode(int mode) {

		this.mode = mode;
	}
	
	public int getMode() {
		return this.mode;
	}

	/**
	 * Getter for text from textField
	 */
	public String getSelectedFilePath() {

		return textField.getText();
	}

	/**
	 * Getter for File Chooser
	 */
	public JFileChooser getFileChooser() {

		return this.fileChooser;
	}
	
	public void setFieldWidth(int width) {
		this.fieldWidth = width;
	}
	

}