package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder {
	
	Node rootNode; 
	
	public TreeBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public void addNode(Node node) {
		traverseAndAdd(rootNode, node);
	}
	
	
	private Node traverseAndAdd(Node root, Node nodeToAdd) {
		
		if(root == null) {
			root = nodeToAdd;
			return root;
		}
		
		if(nodeToAdd.getbNumber() < root.getbNumber()) {
			root.setLeftNode(traverseAndAdd(root.getLeftNode(), nodeToAdd));
		} else if(nodeToAdd.getbNumber() > root.getbNumber()) {
			root.setRightNode(traverseAndAdd(root.getRightNode(), nodeToAdd));
		} else {
			root.merge(nodeToAdd);
		}
		
		return root;
	}
	
	public void delete(int bNumber, String course) {
		
	}
	
	public void printNodes(Results results) {
		
	}

}
