package studentCoursesBackup.util;

import java.util.ArrayList;
import java.util.List;

import studentCoursesBackup.util.FileProcessor.Permission;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {

	List<String> testResults;
	String outputFilePath;

	public Results(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}


	/**
	 * Store new result
	 * @param resultString
	 */
	public void storeNewResult(String resultString) {
		if(testResults == null) {
			testResults = new ArrayList<String>();
		}
		
		testResults.add(resultString);
	}

	//FileDisplayInterface
	@Override
	public void writeToFile() {
		if (testResults == null || testResults.isEmpty()) return;
		
		
		if(outputFilePath == null || outputFilePath.length() == 0) {
			return;
		}

		FileProcessor fileProcessor = new FileProcessor(outputFilePath, Permission.WRITE);
		fileProcessor.writeLines(testResults);
		
		System.out.println("Result is generated at path = " + fileProcessor.filePath);
	}


	//StdoutDisplayInterface
	@Override
	public void writeToStdout() {
		if (testResults == null || testResults.isEmpty()) return;
		
		for (String line : testResults) {
			System.out.println(line);
		}
	}


}
