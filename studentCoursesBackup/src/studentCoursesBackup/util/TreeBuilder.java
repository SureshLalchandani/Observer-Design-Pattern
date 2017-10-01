package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

public class TreeBuilder {
	
	Node rootNode; 
	
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
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
		Node node = lookup(rootNode, bNumber);
		
		if(node != null)
			node.removeCourse(course);
	}
	
	public Node lookup(Node rootNode, int bNumber) {
		Node toReturn = null;
		
		if (rootNode == null) return null;
		
		if(rootNode.getbNumber() == bNumber) {
			toReturn = rootNode;
		} else if(rootNode.getbNumber() > bNumber) {
			toReturn = lookup(rootNode.getLeftNode(), bNumber);
		} else {
			toReturn = lookup(rootNode.getRightNode(), bNumber);
		}
		
		return toReturn;
	}
	
	public void printNodes(Results results) {
		printInAscendingOrder(rootNode, results);
		
		results.writeToFile();
	}
	
	private void printInAscendingOrder(Node node, Results result) {
		if(node == null) return;
		
		printInAscendingOrder(node.getLeftNode(), result);
		
		result.storeNewResult(node.toString());
		
		printInAscendingOrder(node.getRightNode(), result);
	}

}
