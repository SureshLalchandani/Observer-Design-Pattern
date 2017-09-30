package studentCoursesBackup.myTree;

import java.util.List;

public class Node implements ObserverI, SubjectI {
	
	Node leftNode;
	Node rightNode;
	
	int bNumber;
	List<String> courses;
	
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void update(SubjectI sender) {
		// TODO Auto-generated method stub
		
	}

}
