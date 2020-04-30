package jsonGenerator.quickstart;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * <h1>FileTypeFilter</h1> Class to add file filter on open/save windows
 * 
 * @author Bozieac Artur
 * @version 1.0
 * @since 2020-04-23
 */

public class FileTypeFilter extends FileFilter {
	// Variables to store extension and description
	private String extension;
	private String description;

	/**
	 * Constructor for file filter
	 * 
	 * @param extension   Represents selected or the only extension which can be
	 *                    chosen
	 * @param description Represents description of file extensions constraints
	 */
	public FileTypeFilter(String extension, String description) {

		this.extension = extension;
		this.description = description;
	}
	
	/**
	 * Whenever a file is selected gets its name
	 * 
	 * @param file represents chosen file
	 */
	public boolean accept(File file) {

		if (file.isDirectory()) {
			return true;
		}
		return file.getName().toLowerCase().endsWith(extension);
	}

	/**
	 * Formatted getter for description
	 */
	public String getDescription() {
		return description + String.format(" (*%s)", extension);
	}
}
