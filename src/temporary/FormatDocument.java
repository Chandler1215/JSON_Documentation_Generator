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

public class FormatDocument {
	
	private static HashMap<String, String> items = new HashMap<String, String>();
	private String extension;
	

	public FormatDocument(String extension) {
		this.extension = extension;
	}



	public HashMap<String, String> getFileData(File fileOpen) {
	
	String title = "title";
	String description = "description";
	String iteratedItem;
	InputStream is;

	try {
		is = new FileInputStream(fileOpen);
		JSONTokener tokener = new JSONTokener(is);
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

			items.put(iteratedItem, content.getString(title) + " : " + content.getString(description));

		}

		// Iterate through definitions and select title and description(also puts info
		// in a hash map)
		Iterator<?> definitionIterator = definitions.keys();

		while (definitionIterator.hasNext()) {
			iteratedItem = definitionIterator.next().toString();
			content = definitions.getJSONObject(iteratedItem);

			items.put(iteratedItem, "title: " + content.getString(title) + ",\n " + " description: "
					+ content.getString(description));

		}

	} catch (FileNotFoundException e1) {
		e1.printStackTrace();
	}
	
		return items;
	}
	
	
	
	public void createFile(File fileSave) {
		
		// Deletes existing file, and creates a new one
		if(fileSave.exists()) {
			fileSave.delete();
			}
		
		BufferedWriter bf = null;
		try {

			// create new BufferedWriter for the output file
			bf = new BufferedWriter(new FileWriter(fileSave + this.extension));

			// iterate map entries
			for (Map.Entry<String, String> entry : items.entrySet())
				bf.write(entry.getKey() + " --> " + entry.getValue() + "\n");

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
	
	
	
}
