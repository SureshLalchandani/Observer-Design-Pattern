package studentCoursesBackup.processor;

import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.FileProcessor.Permission;

/**
 * Wrapper class that handles Data from Delete file and Updates the Trees accordingly
 * @author suresh
 *
 */
public class DeleteProcessor {
	String filePath;
	TreeBuilder originalTree;
	
	/**
	 * Parameterized constructor to create instance of this class and initializes member
	 * variables with references given
	 * @param filePath
	 * @param originalTree
	 */
	public DeleteProcessor(String filePath, TreeBuilder originalTree) {
		this.filePath = filePath;
		this.originalTree = originalTree;
	}
	
	
	/**
	 * Read delete file line by line and update courses in tree.
	 */
	public void processDelete() {

		String line = null;

		FileProcessor deleteFileProcessor = new FileProcessor(filePath, Permission.READ);
		deleteFileProcessor.setPermitEmptyFile(true);
		while((line = deleteFileProcessor.readLine()) != null) {
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
