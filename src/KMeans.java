
import java.io.File;
import java.io.FileNotFoundException;

import model.algorithm.Algorithm;
import view.Gui;

public class KMeans {
	
	public static void main(String args[]) {
		if(args.length < 1) {
			Usage();
			return;
		}
		try {
			
			String filename = args[0];
			boolean debug = false;
			if(args.length > 1 && args[2].equals("--debug")) {
				debug = true;
			}
		
			File file = new File(filename);
		
		
			Algorithm algorithm = new Algorithm(file, debug);
			Gui gui = new Gui(algorithm);
			gui.validate();
			
		} catch(FileNotFoundException e) {
			System.out.println("Error: Invalid file supplied");
			Usage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Usage() {
		System.out.println("Usage (normal): java KMeans <file_name> [--debug]");
		System.out.println("Usage (jar): java -jar KMeans.jar <file_name> [--debug]");
		System.out.println("\tfile_name - name of the datafile. Each entry must have the format\n" +
				"\t\t Instance_Name,coordx,coordy");
		System.out.println("\t--debug - prints out the state of the clusters on every iteration");
	}

}
