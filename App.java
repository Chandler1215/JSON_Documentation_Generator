package jsonGenerator.quickstart;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;

import static javax.swing.JOptionPane.showMessageDialog;

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
	private static int width = 700, height = 350;
	private static JFilePicker filePickerOpen;
	private static JFilePicker filePickerSave;

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
		final JDialog dialog = new JDialog();
		dialog.setTitle("Generate JSON Schema Documentation");

		// all the other jframe setup stuff

		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setPreferredSize(new Dimension(width, height));
		dialog.pack();
		dialog.setLocationRelativeTo(null);
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

		SaveTypeBox saveTypeBox = new SaveTypeBox("Output type");
		filePickerSave = new JFilePicker("Type Output Path:", "Browse...");
		filePickerSave.setMode(JFilePicker.MODE_SAVE);
		filePickerSave.addFileTypeFilter(".json", "JSON, Text & HTML Files");


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
		
		
		//Handling file path constraints, verifies if given path is valid and verifies input file extension

		generateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				File fileOpen = new File(filePickerOpen.getSelectedFilePath());
				File fileSave = new File(filePickerSave.getSelectedFilePath());

				if (fileOpen.exists() && fileOpen.getPath().contains(".json")) {

					showMessageDialog(null, "JSON Schema URL is OK");

				} else {

					showMessageDialog(null, "Invalid JSON Schema URL");
				}

				if (fileSave.exists() && filePickerSave.getMode() == filePickerSave.MODE_SAVE) {
					int result = JOptionPane.showConfirmDialog(dialog, "The file exists, overwrite?", "Existing file",
							JOptionPane.YES_NO_CANCEL_OPTION);
					switch (result) {
					case JOptionPane.YES_OPTION:
						return;
					case JOptionPane.NO_OPTION:
						return;
					case JOptionPane.CLOSED_OPTION:
						return;
					case JOptionPane.CANCEL_OPTION:
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
		
		
		
		//
		// !!! should change text fields when resizing window
		//
		panel.addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent componentEvent) {
		    	filePickerOpen.setFieldWidth(width);
		    	filePickerSave.setFieldWidth(width);
		    }
		});

		// styles and aligning for title

		// styles and aligning for "Generate" button

		// constraints.insets = new Insets(1, 1, 1, 1);
		// constraints.gridx = 0;
		// constraints.gridy = 6;
		// panel.add(generateButton, constraints);

		// styles and aligning for "Cancel" button

		// constraints.gridx = 0;
		// constraints.gridy = 7;
		// panel.add(cancelButton, constraints);

	}

	/*
	 * private static boolean generateButtonActionPerformed(ActionEvent e) { try {
	 * Paths.get(filePickerOpen.getSelectedFilePath()); } catch
	 * (InvalidPathException ex) { return false; } catch (NullPointerException ex) {
	 * return false; } return true;
	 * 
	 * }
	 */

}
