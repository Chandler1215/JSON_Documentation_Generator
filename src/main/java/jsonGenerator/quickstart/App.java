package jsonGenerator.quickstart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <h1>JSON Scheme Documentation Generator!</h1> This program implements an
 * application that simply displays UI for a converter from JSON to other
 * output.
 *
 * @author Bozieac Artur
 * @version 1.0
 * @since 2020-04-23
 */

public class App {
	/**
	 * Main method, entry point of application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		

		try {
			// Set System L&F to my system L&F (a better style than default)
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		// schedule this for the event dispatch thread

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayJFrame();
			}
		});
	}
	/**
	 * This method is used to display JFrame Components.
	 */
	static void displayJFrame() {
		

		// set the jframe title in the constructor

		JFrame jframe = new JFrame("Generate JSON Schema Documentation");

		// all the other jframe setup stuff

		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setPreferredSize(new Dimension(600, 350));
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);

		// set icon

		jframe.setIconImage(new ImageIcon("images/icon.png").getImage());

		// all the frame components (used JPanel as a container for all the elements)

		JPanel panel = new JPanel(new GridBagLayout());

		JLabel title = new JLabel("Generate JSON Schema Documentation");
		JCheckBox requiredContent = new JCheckBox("Only required content", false);
		JCheckBox includeExamples = new JCheckBox("Include examples", false);
		JButton generateButton = new JButton("Generate");
		JButton cancelButton = new JButton("Cancel");

		// Adding panel to the frame

		jframe.add(panel, BorderLayout.WEST);

		// Creating first instance of filePicker, set to open mode with filter on JSON
		// files

		JFilePicker filePickerOpen = new JFilePicker("Json Schema Path:", "Browse...");
		filePickerOpen.setMode(JFilePicker.MODE_OPEN);
		filePickerOpen.addFileTypeFilter(".json", "JSON Files");

		// Creating second instance of filePicker, set to save mode with filter on JSON
		// files

		SaveTypeBox saveTypeBox = new SaveTypeBox("Output type");
		JFilePicker filePickerSave = new JFilePicker("Type Output Path  :", "Browse...");
		filePickerSave.setMode(JFilePicker.MODE_SAVE);
		filePickerSave.addFileTypeFilter(".html", "JSON, Text & HTML Files");

		// access JFileChooser class directly

		JFileChooser fileChooser = filePickerOpen.getFileChooser();
		fileChooser.setCurrentDirectory(new File("D:/"));

		// add the components to the frame

		GridBagConstraints constraints = new GridBagConstraints();

		// styles and aligning for title
		title.setOpaque(true);
		title.setFont(new Font("Tahoma", Font.BOLD, 18));
		title.setForeground(Color.BLACK);
		constraints.insets = new Insets(10, 10, 10, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(title, constraints);

		// aligning for filePicker (Open Dialog)
		constraints.insets = new Insets(0, 0, 0, 0);
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(filePickerOpen, constraints);

		// aligning for ComboBox
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(saveTypeBox, constraints);

		// aligning for filePicker (Save Dialog)
		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(filePickerSave, constraints);

		// aligning for CheckBoxes
		constraints.gridx = 0;
		constraints.gridy = 4;
		panel.add(requiredContent, constraints);
		constraints.gridx = 0;
		constraints.gridy = 5;
		panel.add(includeExamples, constraints);

		// styles and aligning for "Generate" button
		generateButton.setPreferredSize(new Dimension(100, 30));
		generateButton.setBackground(new Color(59, 89, 182));
		generateButton.setForeground(Color.BLACK);
		generateButton.setFocusPainted(false);
		generateButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		constraints.insets = new Insets(1, 1, 1, 1);
		constraints.gridx = 0;
		constraints.gridy = 6;
		panel.add(generateButton, constraints);

		// styles and aligning for "Cancel" button
		cancelButton.setPreferredSize(new Dimension(100, 30));
		cancelButton.setBackground(new Color(59, 89, 182));
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFocusPainted(false);
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		constraints.gridx = 0;
		constraints.gridy = 7;
		panel.add(cancelButton, constraints);

	}
}
