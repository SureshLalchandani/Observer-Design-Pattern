package studentCoursesBackup.util;

import studentCoursesBackup.myTree.Node;

/**
 * Helper class that holds and create a Binary Search Tree for given BNumber and Courses
 * @author suresh
 *
 */
public class TreeBuilder {
	
	Node rootNode; 
	
	public void setRootNode(Node rootNode) {
		this.rootNode = rootNode;
	}
	
	/**
	 * Add new node to tree
	 * @param node
	 */
	public void addNode(Node node) {
		traverseAndAdd(rootNode, node);
	}
	
	/**
	 * Traverse through the tree and find the appropriate place for new node to store.
	 * @param root
	 * @param nodeToAdd
	 * @return node
	 */
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
	
	/**
	 * Remove course from list or unregister student from given course
	 * @param bNumber
	 * @param course
	 */
	public void delete(int bNumber, String course) {
		Node node = lookup(rootNode, bNumber);
		
		if(node != null)
			node.removeCourse(course);
	}
	
	/**
	 * Lookup node for given bNumber and return
	 * @param rootNode
	 * @param bNumber
	 * @return <b>node</b> if bnumber found in tree, <b>null</b> otherwise
	 */
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
	
	/**
	 * Store and write results to file in format 1234:A B C
	 * @param results
	 */
	public void printNodes(Results results) {
		printInAscendingOrder(rootNode, results);
		
		results.writeToFile();
	}
	
	/**
	 * Traverse tree in In Order form and store result in Results object
	 * @param node
	 * @param result
	 */
	private void printInAscendingOrder(Node node, Results result) {
		if(node == null) return;
		
		printInAscendingOrder(node.getLeftNode(), result);
		
		result.storeNewResult(node.toString());
		
		printInAscendingOrder(node.getRightNode(), result);
	}

}
