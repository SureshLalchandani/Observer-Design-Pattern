package studentCoursesBackup.driver;

public class Driver {

	public static void main(String[] args) {
		
		if(args == null || args.length != 5) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Delete File \n3.Output1 File \n4.Output2 File \n5.Output3 File");
			System.exit(0);
			return;
		}
		
		if(args[0].contains("${arg0}") || 
				args[1].contains("${arg1}") || 
				args[2].contains("${arg2}") ||
				args[3].contains("${arg3}") ||
				args[4].contains("${arg4}")) {
			System.err.println("Please provide valid number of arguments. 5 Arguments are expected: \n1.Input File \n2.Delete File \n3.Output1 File \n4.Output2 File \n5.Output3 File");
			System.exit(0);
			return;
		}
		
	}

}
