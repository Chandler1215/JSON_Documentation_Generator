package jsonGenerator.quickstart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <h1>View</h1> Part of MVC Pattern, UI of application
 * 
 * @author Bozieac Artur
 * @version 1.2
 * @since 2020-05-03
 */
public class View extends JDialog {
	private static final long serialVersionUID = 1L;
	private static final int WINDOW_WIDTH = 750;
	private static final int WINDOW_HEIGHT = 350;

	private String windowTitle;
	private JFilePicker filePickerOpen;
	private JFilePicker filePickerSave;
	private SaveTypeBox saveTypeBox;
	private JPanel panel;
	private JLabel titleLabel;
	private JCheckBox requiredContent;
	private JCheckBox includeExamples;
	private JButton generateButton;
	private JButton cancelButton;
	private GridBagConstraints constraints;

	/**
	 * Constructor for View, sets windows size and title
	 * 
	 * @param width  Windows width
	 * @param height Window height
	 * @param title  Windows title
	 */
	public View(String title) {
		this.windowTitle = title;

	}

	/**
	 * This method is used to display JFrame Components.
	 */
	void displayJFrame() {

		// Frame setup stuff
		this.setTitle(windowTitle);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setMinimumSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setVisible(true);
		this.setIconImage(new ImageIcon("images/icon.png").getImage());

		// all the frame components (used JPanel as a container for all the elements)
		panel = new JPanel(new GridBagLayout());
		setUpPanel();
		// Adding panel to the frame
		this.getContentPane().add(panel, BorderLayout.CENTER);

	}

	private void setUpPanel() {
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		titleLabel = new JLabel(windowTitle);
		requiredContent = new JCheckBox("Only required content", false);
		includeExamples = new JCheckBox("Include examples", false);

		// Creating first instance of filePicker, set to open mode with filter on JSON
		// files

		filePickerOpen = new JFilePicker("Json Schema Path:", "Browse...");
		filePickerOpen.setMode(JFilePicker.MODE_OPEN);
		filePickerOpen.addFileTypeFilter(".json", "JSON Files");

		// Creating second instance of filePicker, set to save mode with filter on JSON
		// files

		saveTypeBox = new SaveTypeBox("Output type");
		filePickerSave = new JFilePicker("Type Output Path:", "Browse...");
		filePickerSave.setMode(JFilePicker.MODE_SAVE);

		// add the components to the frame

		
		titleLabel.setOpaque(true);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setForeground(Color.BLACK);
		panel.add(titleLabel, constraints);
		
		constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridy = 0;
		constraints.weighty = 1;
		constraints.weightx = 1;


		constraints.gridy++;

		panel.add(filePickerOpen, constraints);

		constraints.gridy++;
		panel.add(saveTypeBox, constraints);

		constraints.gridy++;
		panel.add(filePickerSave, constraints);

		constraints.gridy++;
		panel.add(requiredContent, constraints);

		constraints.gridy++;
		panel.add(includeExamples, constraints);

		// generate button style and action

		constraints.insets = new Insets(0, 0, 15, 0);
		constraints.fill = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridy++;
		generateButton = new JButton("Generate");
		generateButton.setPreferredSize(new Dimension(100, 30));
		generateButton.setBackground(new Color(59, 89, 182));
		generateButton.setForeground(Color.BLACK);
		generateButton.setFocusPainted(false);
		generateButton.setFont(new Font("Tahoma", Font.BOLD, 12));

		// Handling file path constraints, verifies if given path is valid and verifies
		// input file extension

		panel.add(generateButton, constraints);

		// Cancel button style and action(closes window)

		constraints.fill = GridBagConstraints.RELATIVE;
		constraints.anchor = GridBagConstraints.EAST;

		cancelButton = new JButton("Cancel");
		cancelButton.setPreferredSize(new Dimension(100, 30));
		cancelButton.setBackground(new Color(59, 89, 182));
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFocusPainted(false);
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));

		panel.add(cancelButton, constraints);
	}

	public JFilePicker getFilePickerOpen() {
		return filePickerOpen;
	}

	public JFilePicker getFilePickerSave() {
		return filePickerSave;
	}

	public SaveTypeBox getSaveTypeBox() {
		return saveTypeBox;
	}

	public JButton getGenerateButton() {
		return generateButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

}
