

import java.io.File;

public class KMeans {
	
	public static void main(String args[]) {
		String filename = args[0];
		File file = new File(filename);
		
		int numClusters = Integer.parseInt(args[1]);
		int numRounds = Integer.parseInt(args[2]);
		
		try {
			GraphingData gui = new GraphingData(null, null, null);
			Algorithm algorithm = new Algorithm(gui, file);
			algorithm.run(numClusters, numRounds);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
