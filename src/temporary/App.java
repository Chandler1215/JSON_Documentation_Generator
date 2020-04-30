package jsonGenerator.quickstart;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * <h1>JSON Scheme Documentation Generator!</h1> This program implements an
 * application that simply displays UI for a converter from JSON to other
 * output.
 *
 * @author Bozieac Artur
 * @version 1.1
 * @since 2020-04-23
 */

public class App {

	// windows size
	private static int width = 750, height = 350;
	private static JFilePicker filePickerOpen;
	private static JFilePicker filePickerSave;
	private static JDialog dialog = new JDialog();
	private static HashMap<String, String> items = new HashMap<String, String>();
	
	public static SaveTypeBox saveTypeBox;
	private static FormatDocument formatText;
	
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

		// set the jDialog title
		dialog = new JDialog();
		dialog.setTitle("Generate JSON Schema Documentation");

		// all the other jframe setup stuff

		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setPreferredSize(new Dimension(width, height));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.setVisible(true);

		// set icon

		dialog.setIconImage(new ImageIcon("images/icon.png").getImage());

		// all the frame components (used JPanel as a container for all the elements)

		JPanel panel = new JPanel(new GridBagLayout());

		JLabel title = new JLabel("Generate JSON Schema Documentation");
		JCheckBox requiredContent = new JCheckBox("Only required content", false);
		JCheckBox includeExamples = new JCheckBox("Include examples", false);
		JButton generateButton = new JButton("Generate");
		JButton cancelButton = new JButton("Cancel");
		


		// Adding panel to the frame
		panel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
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

		GridBagConstraints constraints = new GridBagConstraints();

		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridy = 0;
		constraints.weighty = 0.5;
		title.setOpaque(true);
		title.setFont(new Font("Tahoma", Font.BOLD, 18));
		title.setForeground(Color.BLACK);
		panel.add(title, constraints);

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

		generateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// creates 2 file instances for selected paths

				File fileOpen = new File(filePickerOpen.getSelectedFilePath());
				File fileSave = new File(filePickerSave.getSelectedFilePath());

				// verifies if file exists at selected path and if it is a json file
				if (fileOpen.exists() && fileOpen.getPath().contains(".json")) {
					
					String choosedFormat = saveTypeBox.getSelectedFormat();

					// Switch in dependence of selected format
					
					switch (choosedFormat) {
					case ".txt":
						formatText = new FormatAsText(choosedFormat);
						items = formatText.getFileData(fileOpen);
						formatText.createFile(fileSave);
						return;
					case ".json":
						formatText = new FormatAsJSON(choosedFormat);
						items = formatText.getFileData(fileOpen);
						formatText.createFile(fileSave);
						return;
					case ".html":
						formatText = new FormatAsHTML(choosedFormat);
						items = formatText.getFileData(fileOpen);
						formatText.createFile(fileSave);
						return;
					default :
						return;
					}
					
				} else {

					showMessageDialog(null, "Invalid JSON Schema URL");
				}

				if (fileSave.exists() && filePickerSave.getMode() == JFilePicker.MODE_SAVE) {
					int result = JOptionPane.showConfirmDialog(dialog, "The file exists, overwrite?", "Existing file",
							JOptionPane.YES_NO_CANCEL_OPTION);
					switch (result) {
					case JOptionPane.YES_OPTION:

						formatText.createFile(fileSave);
						
						return;
					case JOptionPane.NO_OPTION:
						return;
					case JOptionPane.CLOSED_OPTION:
						return;
					case JOptionPane.CANCEL_OPTION:
						return;
					}
				} else {

					// Creates file if there is no need to overwrite
					// Switch in dependence of selected format
					String choosedFormat = saveTypeBox.getSelectedFormat();
					
					switch (choosedFormat) {
					case ".txt":
						formatText = new FormatAsText(choosedFormat);
						items = formatText.getFileData(fileOpen);
						formatText.createFile(fileSave);
						return;
					case ".json":
						formatText = new FormatAsJSON(choosedFormat);
						items = formatText.getFileData(fileOpen);
						formatText.createFile(fileSave);
						return;
					case ".html":
						formatText = new FormatAsHTML(choosedFormat);
						items = formatText.getFileData(fileOpen);
						formatText.createFile(fileSave);
						return;
					default :
						return;
					}
					


				}
			}
		});

		panel.add(generateButton, constraints);

		// Cancel button style and action(closes window)

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.EAST;
		cancelButton.setPreferredSize(new Dimension(100, 30));
		cancelButton.setBackground(new Color(59, 89, 182));
		cancelButton.setForeground(Color.BLACK);
		cancelButton.setFocusPainted(false);
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));

		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				dialog.dispose();
			}
		});

		panel.add(cancelButton, constraints);

	}
}
