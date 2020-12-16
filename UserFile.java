
import java.io.*;
import java.util.List;

public class UserFile {
	
	/**
	 * Serialize file
	 * @param info
	 */
	public void saveFile(List<User> info) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		String filePath = System.getProperty("user.dir");
		filePath+="\\src\\com\\dysy\\demo\\UserInfo.txt";
		
		File userFile = new File(filePath);
		
		try {
			userFile.createNewFile();
			fos = new FileOutputStream(userFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(info);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object printFile() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		String filePath = System.getProperty("user.dir");
		filePath+="\\src\\com\\dysy\\demo\\UserInfo.txt";
		
		Object info = null;
		
		try {
			fis = new FileInputStream(new File(filePath));
			ois = new ObjectInputStream(fis);
			info = ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return info;
	}
}