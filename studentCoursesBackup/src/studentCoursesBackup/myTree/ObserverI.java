package studentCoursesBackup.myTree;

public interface ObserverI {
	
	/**
	 * This method will be invoked by the object that conforms to SubjectI interface and 
	 * the current instance of this class is registered as observer.
	 * @param sender
	 */
	public void update(SubjectI sender);

}
