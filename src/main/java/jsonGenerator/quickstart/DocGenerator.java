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

import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * <h1>DocGenerator</h1> Class which gets data from JSON Schema and writes it to a file
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
		String iteratedItem;
		InputStream inputStream;

		try {
			inputStream = new FileInputStream(fileOpen);
			JSONTokener tokener = new JSONTokener(inputStream);
			JSONObject object = new JSONObject(tokener);

			// get JSON objects from which we want to get info
			JSONObject properties = object.getJSONObject("properties");
			JSONObject definitions = object.getJSONObject("definitions");
			JSONObject content;

			// Iterate through properties and select title and description(also puts info in
			// a hash map)
			Iterator<?> propertiesIterator = properties.keys();

			while (propertiesIterator.hasNext()) {
				iteratedItem = propertiesIterator.next().toString();
				content = properties.getJSONObject(iteratedItem);

				items.put(iteratedItem, " title: " + content.getString(title) + ",\n " + " description: "
						+ content.getString(description));

			}

			// Iterate through definitions and select title and description(also puts info
			// in a hash map)
			Iterator<?> definitionIterator = definitions.keys();

			while (definitionIterator.hasNext()) {
				iteratedItem = definitionIterator.next().toString();
				content = definitions.getJSONObject(iteratedItem);

				items.put(iteratedItem, " title: " + content.getString(title) + ",\n " + " description: "
						+ content.getString(description));

			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		return items;
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
			for (Map.Entry<String, String> entry : items.entrySet())
				bf.write(entry.getKey() + " --> \n " + entry.getValue() + "\n");

			// new line
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

	public HashMap<String, String> getItems() {
		return items;
	}

	public void setItems(HashMap<String, String> items) {
		this.items = items;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
}
