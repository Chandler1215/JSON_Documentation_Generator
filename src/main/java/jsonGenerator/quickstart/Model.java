package jsonGenerator.quickstart;

import java.io.File;
import java.util.HashMap;

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
	private HashMap<String, String> documentData = new HashMap<String, String>();

	public DocGenerator getFormatDocument() {
		return formatDocument;
	}

	public void setFormatDocument(DocGenerator formatDocument) {
		this.formatDocument = formatDocument;
	}

	public HashMap<String, String> getDocumentData() {
		return documentData;
	}

	public void setDocumentData(HashMap<String, String> documentData) {
		this.documentData = documentData;
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
