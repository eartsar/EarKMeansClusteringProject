package model.objects;


public class CoordinatePair {
	public double x, y;
	
	public CoordinatePair(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public CoordinatePair(CoordinatePair other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public double distanceFromPoint(CoordinatePair point) {
		return Math.sqrt(
				(Math.pow((this.x - point.x), 2) + 
				 Math.pow((this.y - point.y), 2))
				);
	}
}