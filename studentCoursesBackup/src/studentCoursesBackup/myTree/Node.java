package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class that represents Node of a tree.
 * @author suresh
 *
 */
public class Node implements ObserverI, SubjectI, Cloneable {

	List<ObserverI> observers = new ArrayList<ObserverI>();

	Node leftNode;
	Node rightNode;

	int bNumber;
	Set<String> courses = null;
	
	@SuppressWarnings("unused")
	private Node() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Parameterized constructor to restrict 1 Node to 1 BNumber relation. Also, initializes
	 * all member variables
	 * @param bNumber
	 */
	public Node(int bNumber) {
		this.bNumber = bNumber;
		this.leftNode = null;
		this.rightNode = null;
		this.courses = new HashSet<String>();
	}
	
	/**
	 * Get bNumber
	 * @return bNumber
	 */
	public int getbNumber() {
		return bNumber;
	}
	
	/**
	 * Getter Method for Left Node 
	 * @return Node
	 */
	public Node getLeftNode() {
		return leftNode;
	}
	
	/**
	 * Setter method for LeftNode
	 * @param leftNode
	 */
	public void setLeftNode(Node leftNode) {
		this.leftNode = leftNode;
	}
	
	/**
	 * Getter Method for Right Node
	 * @return Node
	 */
	public Node getRightNode() {
		return rightNode;
	}
	
	/**
	 * Setter method for Right Node 
	 * @param rightNode
	 */
	public void setRightNode(Node rightNode) {
		this.rightNode = rightNode;
	}
	
	
	/**
	 * Add course against this Node's BNumber.
	 * @param course
	 */
	public void addCourse(String course) {
		courses.add(course);
	}
	
	/**
	 * Getter method for courses
	 * @return list of courses
	 */
	public Set<String> getCourses() {
		return courses;
	}
	
	/**
	 * Unregister this BNumber from given course and remove course from list
	 * @param course
	 * @return <b>true</b> if this BNumber was registered for the given course, otherwise <b>false</b>
	 */
	public boolean removeCourse(String course) {
		boolean result = courses.remove(course);
		
		if(result) {
			broadcastChanges();
		}
		
		return result;
	}
	
	/**
	 * This merges the list of courses from source node <b>(Parameter)</b> to current node 
	 * if both nodes have same bNumber. Result of this merging would be union of two lists.
	 * @param node
	 */
	public void merge(Node node) {
		if(node.bNumber != this.bNumber) return;
		
		this.courses.addAll(node.courses);
	}
	

	@Override
	public void register(ObserverI observer) {
		observers.add(observer);
	}

	@Override
	public void unregister(ObserverI observer) {
		if(observers.contains(observer)) {
			observers.remove(observer);
		}
	}

	@Override
	public void broadcastChanges() {
		for(ObserverI observer : observers) {
			observer.update(this);
		}
	}

	@Override
	public void update(SubjectI sender) {
		
		if(!(sender instanceof Node)) {
			System.err.println("Class type mismatch");
			return;
		}
		
		Node node = (Node) sender;
		
		if(node.bNumber == this.bNumber) {
			this.courses.clear();
			this.courses.addAll(node.courses);
		}
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Node node = new Node(this.bNumber);
		node.courses.addAll(this.courses);
		return node;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();	
		
		builder.append(bNumber);
		builder.append(":");
		
		for(String course : courses) {
			builder.append(course+" ");	
		}
		
		return builder.toString();
	}
	
	
}
