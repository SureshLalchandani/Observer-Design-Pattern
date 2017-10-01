package studentCoursesBackup.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileProcessor {
	public enum Permission {
		READ, WRITE
	}

	String filePath;
	BufferedReader reader;
	BufferedWriter writer;
	Permission permission;

	public FileProcessor(String filePath, Permission permission) {
		this.filePath = filePath;
		this.permission = permission;
	} 



	/**
	 * Open file if not already opened
	 * @return
	 */
	private boolean openFile() {

		if(filePath == null || filePath.length() == 0) {
			return false;
		}

		
		File file = new File(filePath);
		boolean openSuccess = true;
		try {
			switch (permission) {
			case READ:

				// Incase file is empty
				if(file.length() == 0) {
					System.err.println("File is Empty!");
					System.exit(1);
					return false;
				}

				
				if (reader == null) {
					FileReader fileReader = new FileReader(file);
					reader = new BufferedReader(fileReader);
				}
				break;
			case WRITE:
				FileWriter fileWriter = new FileWriter(file);
				writer = new BufferedWriter(fileWriter);
				break;
			default:
				System.err.println("FileProcessor:openFile - Please provide appropriate File Permission");
				System.exit(0);
				break;
			}

		} catch (FileNotFoundException e) {
			openSuccess = false;
			System.err.println("FileProcessor:openFile - File Not Found Exception Occured :: "  + e.getLocalizedMessage());
		} catch(IOException e) {
			openSuccess = false;
			System.err.println("FileProcessor:openFile - IO Exception Occured :: "  + e.getLocalizedMessage());
		} finally {
			if (!openSuccess) {
				closeFile();
				System.exit(0);
			}
		}

		return true;
	}

	/**
	 * Writes list of strings into file
	 * @param lines to write into file
	 */
	public void writeLines(List<String> lines) {
		// In case the file path is not available or file is not able to open
		boolean isSuccess = true;
		if (!openFile()) {
			System.err.println("File path doesn't exist");
			System.exit(0);
			return;
		}

		try {
			for(String line : lines ) {
				writer.append(line+"\n");
			}
		} catch (IOException e) {
			isSuccess = false;
			System.err.println("FileProcessor:writeLine - IOException Occured :: "  + e.getLocalizedMessage());
		} finally {
			closeFile();
			if(!isSuccess) {
				System.exit(0);
			}
		}
	}

	/**
	 * Reads the file line by line
	 * @return next line in the file
	 */
	public String readLine() {

		boolean shouldClose = false;
		boolean isSuccess = true;
		// In case the file path is not available or file is not able to open
		if (!openFile()) {
			System.err.println("File path doesn't exist");
			System.exit(0);
			return null;
		}

		String line = null;

		try {
			line = this.reader.readLine();
			shouldClose = line == null;
		} catch (IOException e) {
			System.err.println("FileProcessor:readLine - IO Exception Occured :: "  + e.getLocalizedMessage());
			isSuccess = false;
		} finally {
			if (shouldClose) { 
				closeFile();
			}
			
			if(!isSuccess) {
				System.exit(0);
			}
		}

		return line;
	}

	/**
	 * Close file if already opened
	 */
	private void closeFile() {
		try {
			switch (permission) {
			case READ:
				if (reader != null) {
					reader.close();
					reader = null;
				}	
				break;
			case WRITE:
				if (writer != null) {
					writer.flush();
					writer.close();
					writer = null;
				}
			default:
				break;
			}

		} catch (IOException e) {
			System.err.println("FileProcessor:closeFile - IO Exception Occured :: "  + e.getLocalizedMessage());
			System.exit(0);
		}
	}


	//Setters
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}


}
