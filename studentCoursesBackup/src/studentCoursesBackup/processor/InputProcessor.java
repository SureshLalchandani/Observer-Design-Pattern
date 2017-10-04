package studentCoursesBackup.processor;

import studentCoursesBackup.myTree.Node;
import studentCoursesBackup.util.FileProcessor;
import studentCoursesBackup.util.TreeBuilder;
import studentCoursesBackup.util.FileProcessor.Permission;

/**
 * Wrapper class to process data from Input File and Form a Tree out of it.
 * @author suresh
 */
public class InputProcessor {
	String filePath; 
	TreeBuilder originalTree;
	TreeBuilder backupTree1;
	TreeBuilder backupTree2;
	
	/**
	 * Parameterized constructor to create instance of this class and initializes member
	 * variables with references given
	 * @param readFileProcessor
	 * @param originalTree
	 * @param backupTree1
	 * @param backupTree2
	 */
	public InputProcessor(String filePath, 
			TreeBuilder originalTree, 
			TreeBuilder backupTree1, 
			TreeBuilder backupTree2) {
		this.filePath = filePath;
		this.originalTree = originalTree;
		this.backupTree1 = backupTree1;
		this.backupTree2 = backupTree2;
	}
	
	/**
	 * Process the Input file and read line by line to create a Node 
	 * for every BNumber and add to Tree. Also creates 2 copies of same 
	 * node and add into backup trees
	 */
	public void processInput() {
		int count = -1;
		String line = null;

		FileProcessor readFileProcessor = new FileProcessor(filePath, Permission.READ);
		readFileProcessor.setPermitEmptyFile(true);
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

}
