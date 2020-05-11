package jsonGenerator.quickstart;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * <h1>DocGenerator</h1> Class which gets data from JSON Schema and writes it to
 * a file
 * 
 * @author Bozieac Artur
 * @version 1.2
 * @since 2020-05-03
 */
public class DocGenerator {

	protected HashMap<String, String> items = new HashMap<String, String>();
	protected String extension;

	/**
	 * Constructor for DocGenerator
	 * 
	 * @param extension Represents selected or the only extension which can be
	 *                  selected
	 */
	public DocGenerator(String extension) {
		this.extension = extension;
	}

	/**
	 * Gets data from JSON Schema and saves it to a HashMap
	 * 
	 */
	public HashMap<String, String> getFileData(File fileOpen) {

		String title = "title";
		String description = "description";
		InputStream inputStream;

		try {
			inputStream = new FileInputStream(fileOpen);
			JSONTokener tokener = new JSONTokener(inputStream);
			JSONObject object = new JSONObject(tokener);

			// get JSON objects from which we want to get info
			JSONObject properties = object.getJSONObject("properties");
			JSONObject definitions = object.getJSONObject("definitions");

			collectData(title, description, properties);
			collectData(title, description, definitions);

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		return items;
	}

	/**
	 * Collects data from file and saves to a HashMap "items"
	 * 
	 */
	protected void collectData(String title, String description, JSONObject wantedData) {
		String iteratedItem;
		JSONObject content;
		// Iterate through definitions and select title and description(also puts info
		// in a hash map)
		Iterator<?> Iterator = wantedData.keys();

		while (Iterator.hasNext()) {
			iteratedItem = Iterator.next().toString();
			content = wantedData.getJSONObject(iteratedItem);

			try {
				content.getString(description);
				content.getString(title);
			} catch (JSONException e) {
				// Throws exception if key doesn't exist
			}
			if (extension == ".json") {
				items.put(iteratedItem, " \"Title\": \"" + content.getString(title) + ",\" \n " + " \"Description\": \""
						+ content.getString(description) + "\"");
			} else if (extension == ".html") {
				items.put(iteratedItem, " Title: " + content.getString(title) + ",\n<br> " + " Description: "
						+ content.getString(description));
			} else {
				items.put(iteratedItem, " Title: " + content.getString(title) + ",\n " + " Description: "
						+ content.getString(description));
			}
		}
	}

	/**
	 * Creates file with selected extension, gets data from stored HashMap
	 * 
	 */
	public void createFile(File fileSave) {

		// Deletes existing file, and creates a new one
		if (fileSave.exists()) {
			fileSave.delete();
		}

		BufferedWriter bf = null;
		try {

			// create new BufferedWriter for the output file
			if (fileSave.getPath().contains(extension)) {
				bf = new BufferedWriter(new FileWriter(fileSave));
			} else {
				bf = new BufferedWriter(new FileWriter(fileSave + this.extension));
			}
			// iterate map entries
			if (extension == ".json") {
				writeAsJSON(bf);

			} else if (extension == ".html") {
				writeAsHTML(bf);

			} else {
				for (Map.Entry<String, String> entry : items.entrySet())
					bf.write(entry.getKey() + " --> \n " + entry.getValue() + "\n");

			}

			bf.newLine();
			bf.flush();

		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {

			try {
				// always close the writer
				bf.close();
			} catch (Exception e2) {
			}
		}
	}

// Writes in file as JSON
	private void writeAsJSON(BufferedWriter bf) throws IOException {
		bf.write("\"Generated data\": { \n");

		for (Map.Entry<String, String> entry : items.entrySet())
			bf.write("\"" + entry.getKey() + "\":{ \n " + entry.getValue() + "\n");

		bf.write("}");

	}

// Writes in file as HTML
	private void writeAsHTML(BufferedWriter bf) throws IOException {
		bf.write("<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "\r\n" + "<head>\r\n"
				+ "<title>Generate JSON Schema Documentation</title>\r\n" + "</head>\r\n" + "\r\n" + "<body>");

		for (Map.Entry<String, String> entry : items.entrySet())
			bf.write("<h3>" + entry.getKey() + " : </h3> \n " + " <h4>" + entry.getValue() + "</h4>\n");

		bf.write("</body>\r\n" + "</html>");

	}
}
