package studentCoursesBackup.driver;

import studentCoursesBackup.processor.DeleteProcessor;
import studentCoursesBackup.processor.InputProcessor;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.FileProcessor.Permission;
import studentCoursesBackup.util.Results;
import studentCoursesBackup.util.TreeBuilder;

/**
 * This is a driver/entry of the program i.e main class.
 * @author suresh
 *
 */
public class Driver {

	/**
	 * Entry of the program : Performs command line argument check and validation. The process the inputs as per requirement. 
	 * @param args
	 */
	public static void main(String[] args) {

		if(args == null || args.length != 5) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Delete File \n3.Output1 File \n4.Output2 File \n5.Output3 File");
			System.exit(0);
			return;
		}

		if((args[0].trim().length() == 0 || args[0].contains("${arg0}")) || 
				(args[1].trim().length() == 0 || args[1].contains("${arg1}")) || 
				(args[2].trim().length() == 0 || args[2].contains("${arg2}")) ||
				(args[3].trim().length() == 0 || args[3].contains("${arg3}")) ||
				(args[4].trim().length() == 0 || args[4].contains("${arg4}"))) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Delete File \n3.Output1 File \n4.Output2 File \n5.Output3 File");
			System.exit(0);
			return;
		}

		/*Input File & Delete File path*/
		String inputFile = args[0];
		String deleteFile = args[1];
		
		/*Three Output Files*/
		String outputFile1 = args[2];
		String outputFile2 = args[3];
		String outputFile3 = args[4];

		/*Create tree instances and will fill the tree below*/
		TreeBuilder originalTree = new TreeBuilder();
		TreeBuilder backupTree1 = new TreeBuilder();
		TreeBuilder backupTree2 = new TreeBuilder();

		/*File Read and purge into ArrayList*/
		FileProcessor inputFileProcessor = new FileProcessor(inputFile, Permission.READ);
		InputProcessor inputProcessor = new InputProcessor(inputFileProcessor, originalTree, backupTree1, backupTree2);
		inputProcessor.processInput();
		
		/*Read Delete file and update courses*/
		FileProcessor deleteFileProcessor = new FileProcessor(deleteFile, Permission.READ);
		DeleteProcessor deleteProcessor = new DeleteProcessor(deleteFileProcessor, originalTree);
		deleteProcessor.processDelete();
		
		/*Create Result instances and bind it with file paths to write results in the files*/
		Results results1 = new Results(outputFile1);
		Results results2 = new Results(outputFile2);
		Results results3 = new Results(outputFile3);
		
		/*Fill the results with values of Nodes present in corresponding trees.*/
		originalTree.printNodes(results1);
		backupTree1.printNodes(results2);
		backupTree2.printNodes(results3);
	}

}
