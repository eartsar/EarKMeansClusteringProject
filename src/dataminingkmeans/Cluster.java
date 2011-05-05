

import java.util.LinkedList;
import java.awt.Color;

public class Cluster {

	private CoordinatePair centroid;
	private LinkedList<CoordinatePair> values;
	private Color color;
	
	public Cluster(CoordinatePair centroid, int clusterId) {
		this.centroid = centroid;
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
		values.clear();
	}
	
	public void calculateCentroid() {
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
		return 0;
	}
	
	public void setCentroid(CoordinatePair centroid) {
		this.centroid = centroid;
	}

	public CoordinatePair getCentroid() {
		return centroid;
	}

	public Color getColor() {
		return this.color;
	}
}
