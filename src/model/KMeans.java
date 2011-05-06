package model;
import java.io.File;

import view.Gui;


public class KMeans {
	
	public static void main(String args[]) {
		String filename = args[0];
		boolean debug = false;
		if(args.length > 1 && args[2].equals("--debug")) {
			debug = true;
		}
		
		File file = new File(filename);
		
		try {
			Algorithm algorithm = new Algorithm(file, debug);
			Gui gui = new Gui(algorithm);
			gui.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void Usage() {
		System.out.println("Usage: KMeans <file_name> [--debug]");
		System.out.println("\t\tfile_name - name of the datafile. Each entry must have the format\n" +
				"\t\t\t Instance_Name,coordx,coordy");
		System.out.println("\t\t--debug - prints out the state of the clusters on every iteration");
	}

}
