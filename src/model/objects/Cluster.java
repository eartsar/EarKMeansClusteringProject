package model.objects;


import java.util.LinkedList;
import java.awt.Color;


public class Cluster {

	private CoordinatePair centroid;
	private LinkedList<CoordinatePair> values;
	private Color color;
	private int clusterId;
	
	public Cluster(CoordinatePair centroid, int clusterId) {
		this.clusterId = clusterId;
		this.centroid = new CoordinatePair(centroid);
		this.color = ClusterColorer.INSTANCE.getColor(clusterId);
		values = new LinkedList<CoordinatePair>();
	}
	
	public void addCoordinate(double x, double y) {
		values.add(new CoordinatePair(x, y));
	}
	
	public void addCoordinate(CoordinatePair point) {
		values.add(point);
	}
	
	public LinkedList<CoordinatePair> getPoints(){
		return values;
	}
	
	public void clearPoints() {
		values = new LinkedList<CoordinatePair>();;
	}
	
	public void calculateCentroid() {
		if(values.size() == 0 || values.size() == 1) {
			return;
		}
		
		double totalX = 0;
		double totalY = 0;
		
		for(CoordinatePair coord : values) {
			totalX += coord.x;
			totalY += coord.y;
		}
		
		centroid.x = totalX / values.size();
		centroid.y = totalY / values.size();
	}
	
	public boolean overlaps(Cluster other) {
		return centroid.x == other.getCentroid().x 
			&& centroid.y == other.getCentroid().y;
	}
	
	public double distanceFromPoint(CoordinatePair point) {
		return Math.sqrt(
				(Math.pow((centroid.x - point.x), 2) + 
				 Math.pow((centroid.y - point.y), 2))
				);
	}
	
	public double getObjectiveFunction() {
		double totalDistance = 0;
		
		for(CoordinatePair point : values) {
			totalDistance = totalDistance + point.distanceFromPoint(centroid);
		}
		
		if(totalDistance == 0) {
			return 0;
		}
		
		return totalDistance / values.size();
	}
	
	public void setCentroid(CoordinatePair centroid) {
		this.centroid = new CoordinatePair(centroid);
	}

	public CoordinatePair getCentroid() {
		return centroid;
	}

	public Color getColor() {
		return this.color;
	}
	
	public int getClusterId() {
		return this.clusterId;
	}
}
