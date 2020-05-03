package jsonGenerator.quickstart;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.collection.IsMapContaining;
import org.junit.Test;

import junit.framework.Assert;

public class DocGeneratorTest {

	@Test
	public void getFileDataTest() {
		File fileOpen = new File("C:\\Users\\BART\\Desktop\\personal-schema.json");
		
		// tests if each instance gets data correct
		DocGenerator testDocumentTxt = new DocGenerator(".txt");
		DocGenerator testDocumentJSON = new DocGenerator(".json");
		DocGenerator testDocumentHTML = new DocGenerator(".html");
		
		
		assertThat(testDocumentTxt.getFileData(fileOpen).size(), is(6));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("nameType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("idType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("nameValueType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("linkType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("personType"));
		assertThat(testDocumentTxt.getFileData(fileOpen), IsMapContaining.hasKey("personnel"));
		
		assertThat(testDocumentJSON.getFileData(fileOpen).size(), is(6));
		assertThat(testDocumentJSON.getFileData(fileOpen), IsMapContaining.hasKey("nameType"));
		assertThat(testDocumentJSON.getFileData(fileOpen), IsMapContaining.hasKey("idType"));
		assertThat(testDocumentJSON.getFileData(fileOpen), IsMapContaining.hasKey("nameValueType"));
		assertThat(testDocumentJSON.getFileData(fileOpen), IsMapContaining.hasKey("linkType"));
		assertThat(testDocumentJSON.getFileData(fileOpen), IsMapContaining.hasKey("personType"));
		assertThat(testDocumentJSON.getFileData(fileOpen), IsMapContaining.hasKey("personnel"));
		
		assertThat(testDocumentHTML.getFileData(fileOpen).size(), is(6));
		assertThat(testDocumentHTML.getFileData(fileOpen), IsMapContaining.hasKey("nameType"));
		assertThat(testDocumentHTML.getFileData(fileOpen), IsMapContaining.hasKey("idType"));
		assertThat(testDocumentHTML.getFileData(fileOpen), IsMapContaining.hasKey("nameValueType"));
		assertThat(testDocumentHTML.getFileData(fileOpen), IsMapContaining.hasKey("linkType"));
		assertThat(testDocumentHTML.getFileData(fileOpen), IsMapContaining.hasKey("personType"));
		assertThat(testDocumentHTML.getFileData(fileOpen), IsMapContaining.hasKey("personnel"));

	}

	@Test
	public void createFileTest() throws IOException {
		
		File outputText = new File("C:\\Users\\BART\\Desktop\\test.txt");
		File outputJSON = new File("C:\\Users\\BART\\Desktop\\test.json");
		File outputHTML = new File("C:\\Users\\BART\\Desktop\\test.html");
		
		File JUnitTestText = new File("C:\\Users\\BART\\Desktop\\JUnitTest.txt");
		File JUnitTestJSON = new File("C:\\Users\\BART\\Desktop\\JUnitTest.json");
		File JUnitTestHTML = new File("C:\\Users\\BART\\Desktop\\JUnitTest.html");
		
		
		// verifies if file was created
		assertTrue(outputText.exists());
		assertTrue(outputJSON.exists());
		assertTrue(outputHTML.exists());
		
		// compares create file(output) content with test file content
		Assert.assertEquals(FileUtils.readLines(JUnitTestText), FileUtils.readLines(outputText));
		Assert.assertEquals(FileUtils.readLines(JUnitTestJSON), FileUtils.readLines(outputJSON));
		Assert.assertEquals(FileUtils.readLines(JUnitTestHTML), FileUtils.readLines(outputHTML));

	}

}
