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
 * @author Bozieac Artur
 * @version 1.2
 * @since 2020-05-03
 */
public class View {
	private String windowTitle;
	private int width;
	private int height;
	private JFilePicker filePickerOpen;
	private JFilePicker filePickerSave;
	private JDialog dialog;
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
	 * @param width Windows width
	 * @param height Window height
	 * @param title Windows title
	 */
	public View(int width, int height, String title) {
		this.windowTitle = title;
		this.width = width;
		this.height = height;
	}

	/**
	 * This method is used to display JFrame Components.
	 */
	void displayJFrame() {

		// Frame setup stuff
		dialog = new JDialog();
		dialog.setTitle(windowTitle);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setPreferredSize(new Dimension(width, height));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);
		dialog.setIconImage(new ImageIcon("images/icon.png").getImage());

		// all the frame components (used JPanel as a container for all the elements)

		panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));

		titleLabel = new JLabel(windowTitle);
		requiredContent = new JCheckBox("Only required content", false);
		includeExamples = new JCheckBox("Include examples", false);

		// Adding panel to the frame

		dialog.add(panel, BorderLayout.CENTER);

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

		constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridy = 0;
		constraints.weighty = 0.5;
		titleLabel.setOpaque(true);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		titleLabel.setForeground(Color.BLACK);
		panel.add(titleLabel, constraints);

		constraints.anchor = GridBagConstraints.NORTHWEST;
		constraints.weighty = 0.1;
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
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridy++;
		generateButton.setPreferredSize(new Dimension(100, 30));
		generateButton.setBackground(new Color(59, 89, 182));
		generateButton.setForeground(Color.BLACK);
		generateButton.setFocusPainted(false);
		generateButton.setFont(new Font("Tahoma", Font.BOLD, 12));

		// Handling file path constraints, verifies if given path is valid and verifies
		// input file extension

		panel.add(generateButton, constraints);

		// Cancel button style and action(closes window)

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		cancelButton.setPreferredSize(new Dimension(100, 30));
		cancelButton.setBackground(new Color(59, 89, 182));
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFocusPainted(false);
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));

		panel.add(cancelButton, constraints);

	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public JFilePicker getFilePickerOpen() {
		return filePickerOpen;
	}

	public void setFilePickerOpen(JFilePicker filePickerOpen) {
		this.filePickerOpen = filePickerOpen;
	}

	public JFilePicker getFilePickerSave() {
		return filePickerSave;
	}

	public void setFilePickerSave(JFilePicker filePickerSave) {
		this.filePickerSave = filePickerSave;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}

	public SaveTypeBox getSaveTypeBox() {
		return saveTypeBox;
	}

	public void setSaveTypeBox(SaveTypeBox saveTypeBox) {
		this.saveTypeBox = saveTypeBox;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitle(JLabel title) {
		this.titleLabel = title;
	}

	public JCheckBox getRequiredContent() {
		return requiredContent;
	}

	public void setRequiredContent(JCheckBox requiredContent) {
		this.requiredContent = requiredContent;
	}

	public JCheckBox getIncludeExamples() {
		return includeExamples;
	}

	public void setIncludeExamples(JCheckBox includeExamples) {
		this.includeExamples = includeExamples;
	}

	public JButton getGenerateButton() {
		return generateButton;
	}

	public void setGenerateButton(JButton generateButton) {
		this.generateButton = generateButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public GridBagConstraints getConstraints() {
		return constraints;
	}

	public void setConstraints(GridBagConstraints constraints) {
		this.constraints = constraints;
	}

	public String getWindowTitle() {
		return windowTitle;
	}

	public void setWindowTitle(String windowTitle) {
		this.windowTitle = windowTitle;
	}
}
