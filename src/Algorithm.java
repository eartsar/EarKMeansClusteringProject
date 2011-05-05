import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Algorithm {

	private LinkedList<CoordinatePair> points;
	private ArrayList<Cluster> clusters;
	private double maxX;
	private double maxY;
	
	public Algorithm(File file) throws Exception {
		this.points = new LinkedList<CoordinatePair>();
		this.clusters = null;
		maxX = 0;
		maxY = 0;
		loadFile(file);
	}
	
	public void run(int numClusters, int numRounds) {
		clusters = getInitialClusters(numClusters);
		
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
			
			System.out.println("### PASS " + round + " ###");
			for(Cluster cluster : clusters) {
				System.out.println("Cluster: " + cluster.getClusterId() + 
						" -- Points: " + cluster.getPoints().size() + " -- Color: " +
						cluster.getColor().toString());
			}
		}
		
		// update the gui
	}
	
	private ArrayList<Cluster> getInitialClusters(int numClusters) {
		
		ArrayList<Cluster> initialClusters = new ArrayList<Cluster>();	
		Random rng = new Random();
		
		for(int i = 0; i < numClusters; i++) {
			
			boolean done = false;
			
			CoordinatePair centroid = points.get(rng.nextInt(points.size()));
			Cluster newCluster = new Cluster(centroid, i);
			
			while(!done) {
				
				done = true;
				
				for(Cluster cluster : initialClusters) {
					if(cluster.overlaps(newCluster)) {
						done = false;
					}
				}
				
				if(!done) {
					centroid = points.get(rng.nextInt(points.size()));
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
			if(tokens.length < 3) {
				System.out.println("");
			}
			double x = Double.parseDouble(tokens[1]);
			double y = Double.parseDouble(tokens[2]);
			CoordinatePair point = new CoordinatePair(x, y);
			points.add(point);
		}
		
		findMaxX();
		findMaxY();
	}
	
	
	public double[] getAllXPoints() {
		if(clusters == null) {
			return null;
		}
		
		double[] xPoints = new double[points.size()];
		int count = 0;
		
		for(Cluster cluster : clusters) {
			for(CoordinatePair point : cluster.getPoints()) {
				xPoints[count] = point.x;
				count++;
			}
		}
		
		return xPoints;
	}
	
	public double[] getAllYPoints() {
		if(clusters == null) {
			return null;
		}
		
		double[] yPoints = new double[points.size()];
		int count = 0;
		
		for(Cluster cluster : clusters) {
			for(CoordinatePair point : cluster.getPoints()) {
				yPoints[count] = point.y;
				count++;
			}
		}
		
		return yPoints;
	}
	
	public Color[] getAllPointColors() {
		if(clusters == null) {
			return null;
		}
		
		Color[] pointColors = new Color[points.size()];
		int count = 0;
		
		for(Cluster cluster : clusters) {
			for(CoordinatePair point : cluster.getPoints()) {
				pointColors[count] = cluster.getColor();
				count++;
			}
		}
		
		return pointColors;
	}
	
	private void findMaxX() {
        double max = -Double.MAX_VALUE;
        for(CoordinatePair point : points) {
            if(point.x > max)
                max = point.x;
        }
        
        maxX = max;
    }

	private void findMaxY() {
        double max = -Double.MAX_VALUE;
        for(CoordinatePair point : points) {
            if(point.y > max)
                max = point.y;
        }
        
        maxY = max;
    }
	
	public double getMaxX() {
		return maxX;
	}
	
	public double getMaxY() {
		return maxY;
	}
	
}
