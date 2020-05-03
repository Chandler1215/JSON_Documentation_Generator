package jsonGenerator.quickstart;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * <h1>Controller</h1> Part of MVC Pattern, logic part of application
 * 
 * @author Bozieac Artur
 * @version 1.2
 * @since 2020-05-03
 */
public class Controller {

	private View view;
	private Model model;

	/**
	 * Constructor for Controller
	 * 
	 * @param model Data part
	 * @param view  User Interface
	 */
	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		initView();
	}

	/**
	 * Initializes UI and sets System Look and Feel
	 * 
	 */
	private void initView() {
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
				view.displayJFrame();

			}
		});
	}

	/**
	 * Initializes Controller and handles Actions
	 * 
	 */
	public void initController() {
		view.setGenerateButton(new JButton("Generate"));
		view.getGenerateButton().addActionListener(e -> generateButtonAction());

		view.setCancelButton(new JButton("Cancel"));
		view.getCancelButton().addActionListener(e -> view.getDialog().dispose());

	}

	/**
	 * Generate Button action
	 * 
	 * 
	 */
	public void generateButtonAction() {
		// creates 2 file instances for selected paths
		model.setFileOpen(new File(view.getFilePickerOpen().getSelectedFilePath()));
		model.setFileSave(new File(view.getFilePickerSave().getSelectedFilePath()));

		// verifies if file exists at selected path and if it is a json file
		if (!model.getFileOpen().exists() && !model.getFileOpen().getPath().contains(".json")) {

			showMessageDialog(null, "Invalid JSON Schema URL");
		}

		if (model.getFileSave().exists() && view.getFilePickerSave().getMode() == JFilePicker.MODE_SAVE) {
			int result = JOptionPane.showConfirmDialog(view.getDialog(), "The file exists, overwrite?", "Existing file",
					JOptionPane.YES_NO_CANCEL_OPTION);
			switch (result) {
			case JOptionPane.YES_OPTION:
				// Call createSelectedFormat method to create file of selected format

				createSelectedFormat(view.getSaveTypeBox().getSelectedFormat(), model.getFileOpen(),
						model.getFileSave());

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
			// Call useSelectedFormat method to create file of selected format
			createSelectedFormat(view.getSaveTypeBox().getSelectedFormat(), model.getFileOpen(), model.getFileSave());
		}
	}

	/**
	 * Selects format and creates file depending on it
	 * 
	 * @param selectedFormat Format from combobox
	 * @param fileOpen Selected source
	 * @param fileSave Selected output 
	 * 
	 */
	public void createSelectedFormat(String selectedFormat, File fileOpen, File fileSave) {
		// Switch depending on the format you select

		if (selectedFormat == ".txt") {
			model.setFormatDocument(new FormatAsText(selectedFormat));

		} else if (selectedFormat == ".json") {
			model.setFormatDocument(new FormatAsJSON(selectedFormat));

		} else {
			model.setFormatDocument(new FormatAsHTML(selectedFormat));
		}

		model.getFormatDocument().getFileData(fileOpen);
		model.getFormatDocument().createFile(fileSave);

	}

}
