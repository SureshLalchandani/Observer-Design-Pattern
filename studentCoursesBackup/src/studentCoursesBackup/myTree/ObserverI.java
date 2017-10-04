package studentCoursesBackup.myTree;

/**
 * An Interface that needs to be implemented by Node class in order to be registered as
 * observer and get notified whenever there is update
 * @author suresh
 */
public interface ObserverI {
	
	/**
	 * This method will be invoked by the object that conforms to SubjectI interface and 
	 * the current instance of this class is registered as observer.
	 * @param sender
	 */
	public void update(SubjectI sender);

}
