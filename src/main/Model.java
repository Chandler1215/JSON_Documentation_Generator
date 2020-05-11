package jsonGenerator.quickstart;

import java.io.File;

/**
 * <h1>Model</h1> Part of MVC pattern
 * 
 * @author Bozieac Artur
 * @version 1.0
 * @since 2020-05-03
 */

public class Model {
	private File fileOpen;
	private File fileSave;
	private DocGenerator formatDocument;


	public DocGenerator getFormatDocument() {
		return formatDocument;
	}

	public void setFormatDocument(DocGenerator formatDocument) {
		this.formatDocument = formatDocument;
	}


	public File getFileOpen() {
		return fileOpen;
	}

	public void setFileOpen(File fileOpen) {
		this.fileOpen = fileOpen;
	}

	public File getFileSave() {
		return fileSave;
	}

	public void setFileSave(File fileSave) {
		this.fileSave = fileSave;
	}

}
