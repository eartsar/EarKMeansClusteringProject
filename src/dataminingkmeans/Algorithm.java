

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Algorithm {

	private GraphingData gui;
	private LinkedList<CoordinatePair> points;
	
	public Algorithm(GraphingData gui, File file) throws Exception {
		this.points = new LinkedList<CoordinatePair>();
		this.gui = gui;
		loadFile(file);
	}
	
	public void run(int numClusters, int numRounds) {
		ArrayList<Cluster> clusters = getInitialClusters(numClusters);
		
		for(int round = 0; round < numRounds; round++) {
			// if repeat round, recalculate centroid, clear points  
			if(round > 0) {
				for(Cluster cluster : clusters) {
					cluster.calculateCentroid();
					cluster.clearPoints();
				}
			}
			
			// go through all points, add to appropriate cluster
			for(CoordinatePair point : points) {
				
				double minDistance = Double.MAX_VALUE;
				Cluster closest = null;
				
				// find the closest cluster
				for(Cluster cluster : clusters) {
					if(cluster.distanceFromPoint(point) < minDistance) {
						closest = cluster;
						minDistance = cluster.distanceFromPoint(point);
					}
				}
				
				closest.addCoordinate(point);
			}
		}
		
		// update the gui
	}
	
	private ArrayList<Cluster> getInitialClusters(int numClusters) {
		
		ArrayList<Cluster> initialClusters = new ArrayList<Cluster>();	
		Random rng = new Random(points.size());
		
		for(int i = 0; i < numClusters; i++) {
			
			boolean done = false;
			
			CoordinatePair centroid = points.get(rng.nextInt());
			Cluster newCluster = new Cluster(centroid, i);
			
			while(!done) {
				
				done = true;
				
				for(Cluster cluster : initialClusters) {
					if(cluster.overlaps(newCluster)) {
						done = false;
					}
				}
				
				if(!done) {
					centroid = points.get(rng.nextInt());
					newCluster.setCentroid(centroid);
				}
			}
			
			initialClusters.add(i, newCluster);
		}
		
		return initialClusters;
	}
	
	private void loadFile(File file) throws Exception {
		Scanner scanner = new Scanner(file);
		
		while(scanner.hasNextLine()) {
			String[] tokens = scanner.nextLine().split(",");
			
			double x = Double.parseDouble(tokens[1]);
			double y = Double.parseDouble(tokens[2]);
			CoordinatePair point = new CoordinatePair(x, y);
			points.add(point);
		}
	}
	
}
