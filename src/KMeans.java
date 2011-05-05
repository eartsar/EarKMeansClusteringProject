import java.io.File;

public class KMeans {
	
	public static void main(String args[]) {
		String filename = args[0];
		File file = new File(filename);
		
		try {
			Algorithm algorithm = new Algorithm(file);
			Gui gui = new Gui(algorithm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
