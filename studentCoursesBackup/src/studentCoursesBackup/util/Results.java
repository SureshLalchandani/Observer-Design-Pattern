package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.List;

import studentCoursesBackup.util.FileProcessor.Permission;

/**
 * Class to store all results in string format and process those results to write in
 * a file or print on console
 * @author suresh
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	List<String> testResults = new ArrayList<String>();;
	String outputFilePath;

	public Results(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}


	/**
	 * Store new result
	 * @param resultString
	 */
	public void storeNewResult(String resultString) {
		testResults.add(resultString);
	}

	@Override
	public void writeToFile() {
		FileProcessor fileProcessor = new FileProcessor(outputFilePath, Permission.WRITE);
		fileProcessor.writeLines(testResults);
		
		System.out.println("Result is generated at path = " + fileProcessor.filePath);
	}


	@Override
	public void writeToStdout() {
		for (String line : testResults) {
			System.out.println(line);
		}
	}


}
