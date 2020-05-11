package jsonGenerator.quickstart;

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

	/**
	 * Main method, entry point of application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		View UI = new View("Generate JSON Schema Documentation");
		Model model = new Model();
		Controller controller = new Controller(model, UI);
		
		controller.initController();
		
	}
}
