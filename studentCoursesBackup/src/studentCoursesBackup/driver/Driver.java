package studentCoursesBackup.driver;

import studentCoursesBackup.myTree.Node;
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

		if(args[0].contains("${arg0}") || 
				args[1].contains("${arg1}") || 
				args[2].contains("${arg2}") ||
				args[3].contains("${arg3}") ||
				args[4].contains("${arg4}")) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Delete File \n3.Output1 File \n4.Output2 File \n5.Output3 File");
			System.exit(0);
			return;
		}


		String inputFile = args[0];
		String deleteFile = args[1];
		
		String outputFile1 = args[2];
		String outputFile2 = args[3];
		String outputFile3 = args[4];


		/*File Read and purge into ArrayList*/
		FileProcessor readFileProcessor = new FileProcessor(inputFile, Permission.READ);

		/*Create tree instances and will fill the tree below*/
		TreeBuilder originalTree = new TreeBuilder();
		TreeBuilder backupTree1 = new TreeBuilder();
		TreeBuilder backupTree2 = new TreeBuilder();

		
		Driver driver = new Driver();
		driver.processInput(readFileProcessor, originalTree, backupTree1, backupTree2);
		
		readFileProcessor = new FileProcessor(deleteFile, Permission.READ);
		driver.processDelete(readFileProcessor, originalTree);
		
		/*Create Result instances and bind it with file paths to write results in the files*/
		Results results1 = new Results(outputFile1);
		Results results2 = new Results(outputFile2);
		Results results3 = new Results(outputFile3);
		
		/*Fill the results with values of Nodes present in corresponding trees.*/
		originalTree.printNodes(results1);
		backupTree1.printNodes(results2);
		backupTree2.printNodes(results3);
	}

	/**
	 * Read input file line by line and create Tree from it.
	 * @param readFileProcessor
	 * @param originalTree
	 * @param backupTree1
	 * @param backupTree2
	 */
	private void processInput(FileProcessor readFileProcessor, 
			TreeBuilder originalTree, 
			TreeBuilder backupTree1, 
			TreeBuilder backupTree2) {

		int count = -1;

		String line = null;

		while((line = readFileProcessor.readLine()) != null) {
			try {

				if(line == null || line.trim().length() == 0) {
					continue;
				}

				String[] components = line.split(":");

				int bNumber = Integer.parseInt(components[0]);
				String course = components[1];

				Node nodeOriginal  = new Node(bNumber);
				nodeOriginal.addCourse(course);

				Node nodeBackup1 = (Node) nodeOriginal.clone();
				Node nodeBackup2 = (Node) nodeOriginal.clone();
				
				// Register both backup trees as observers
				nodeOriginal.register(nodeBackup1);
				nodeOriginal.register(nodeBackup2);

				count++;
				if (count == 0) {
					originalTree.setRootNode(nodeOriginal);;
					backupTree1.setRootNode(nodeBackup1);
					backupTree2.setRootNode(nodeBackup2);

					continue;
				}

				originalTree.addNode(nodeOriginal);
				backupTree1.addNode(nodeBackup1);
				backupTree2.addNode(nodeBackup2);


			} catch(NumberFormatException | CloneNotSupportedException ex) {

				if (ex instanceof NumberFormatException) {
					System.err.println("Driver:main - Number Format Exception Occured :: "  + ex.getLocalizedMessage());
				} else if (ex instanceof CloneNotSupportedException) {
					System.err.println("Driver:main - CloneNotSupportedException Occured :: "  + ex.getLocalizedMessage());
				}

				System.exit(0);
			}
		}
	}
	
	
	/**
	 * Read delete file line by line and update courses in tree.
	 * @param readFileProcessor
	 * @param originalTree
	 * @param backupTree1
	 * @param backupTree2
	 */
	private void processDelete(FileProcessor readFileProcessor, TreeBuilder originalTree) {

		String line = null;

		while((line = readFileProcessor.readLine()) != null) {
			try {

				if(line == null || line.trim().length() == 0) {
					continue;
				}

				String[] components = line.split(":");

				int bNumber = Integer.parseInt(components[0]);
				String course = components[1];

				originalTree.delete(bNumber, course);

			} catch(NumberFormatException ex) {
				System.err.println("Driver:main - Number Format Exception Occured :: "  + ex.getLocalizedMessage());
				System.exit(0);
			}
		}
	}


}
