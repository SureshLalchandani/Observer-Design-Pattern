package studentCoursesBackup.myTree;

import java.util.ArrayList;
import java.util.List;

public class Node implements ObserverI, SubjectI, Cloneable {

	List<ObserverI> observers = new ArrayList<ObserverI>();

	Node leftNode;
	Node rightNode;

	int bNumber;
	List<String> courses = null;
	
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
		this.courses = new ArrayList<String>();
	}
	
	/**
	 * Add course against this Node's BNumber.
	 * @param course
	 */
	public void addCourse(String course) {
		courses.add(course);
	}
	
	/**
	 * Unregister this BNumber from given course and remove course from list
	 * @param course
	 * @return <b>true</b> if this BNumber was registered for the given course, otherwise <b>false</b>
	 */
	public boolean removeCourse(String course) {
		return courses.remove(course);
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
		
		if(this.leftNode != null) 
			node.leftNode = (Node) this.leftNode.clone();
		
		if(this.rightNode != null)
			node.rightNode = (Node) this.rightNode.clone();
		
		
		
		return node;
	}
	
}
