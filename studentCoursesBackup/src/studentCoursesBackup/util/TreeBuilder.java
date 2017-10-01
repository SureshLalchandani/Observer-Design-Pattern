package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder {
	
	Node rootNode; 
	
	public TreeBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Node add(Node root, int bNumber, String course) {
		if(root == null) {
			root = new Node(bNumber);
		}
		
		return root;
	}
	
	public void delete(int bNumber, String course) {
		
	}
	
	public void printNodes(Results results) {
		
	}

}
