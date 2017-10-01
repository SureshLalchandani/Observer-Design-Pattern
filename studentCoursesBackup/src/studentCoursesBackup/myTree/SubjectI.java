package studentCoursesBackup.myTree;

public interface SubjectI {
	
	
	/**
	 * Register an observer to listen and get notified if any change occurs in current
	 * instance of Subject object.
	 * @param observer
	 */
	public void register(ObserverI observer);
	
	/**
	 * Unregister an observer and stop listening any changes in current
	 * instance of Subject object.
	 * @param observer
	 */
	public void unregister(ObserverI observer);
	
	
	/**
	 * If any change occurs this method will notify all registered observers about updates.
	 * @void
	 */
	public void broadcastChanges();

}
