package jsonGenerator.quickstart;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

public class FormatDocumentTest {

	@Test
	public void getFileDataTest() {
		File fileOpen = new File("C:\\Users\\BART\\Desktop\\personal-schema.json");
		FormatDocument testDocumentTxt = new FormatDocument(".txt");
		FormatDocument testDocumentJSON = new FormatDocument(".json");
		FormatDocument testDocumentHTML = new FormatDocument(".html");
		
		HashMap<String, String> expected = new HashMap<String, String>();
		expected.put("nameType", "title: The 'name' property,\r\n" + 
				"  description: Specifies the family and given name for the person.");
		expected.put("idType", "The 'id' propertySpecifies a required ID for this person.");
		expected.put("nameValueType", "TThe 'family' or 'given' propertyThe first name or last name of the person.");
		expected.put("personnel", "The 'personnel' propertyDefines the personnel as a collection of person entries.");
		expected.put("linkType", "The 'link' propertySpecifies who is the manager and who are the subordinates for this person.");
		expected.put("personType", "The 'person' propertySpecifies information about a person.");
		
		assertThat(testDocumentTxt.getFileData(fileOpen).size(), is(6));
		
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("nameType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("idType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("nameValueType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("linkType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("personType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("personnel"));
	}
	
	public void createFileTest() {
		File file = new File("C:\\Users\\BART\\Desktop\\test.txt");
		assertTrue(file.exists());
	}



}
