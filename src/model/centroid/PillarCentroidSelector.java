package model.centroid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import model.objects.Cluster;
import model.objects.CoordinatePair;

public class PillarCentroidSelector implements CentroidSelector {

	@Override
	public ArrayList<Cluster> findClusters(int numClusters, LinkedList<CoordinatePair> points) {
		
		HashMap<CoordinatePair, Double> distanceSums = new HashMap<CoordinatePair, Double>();
		
		ArrayList<Cluster> clusters = new ArrayList<Cluster>();
		int clusterId = 0;
		
		CoordinatePair mean = new CoordinatePair(0, 0);
		
		for(CoordinatePair point : points) {
			distanceSums.put(point, 0.0);
			mean.x = mean.x + point.x;
			mean.y = mean.y + point.y;
		}
		
		mean.x = mean.x / points.size();
		mean.y = mean.y / points.size();
		
		CoordinatePair firstCentroid = null;
		double maxDistanceFromMean = 0;
		
		for(CoordinatePair point : points) {
			double distanceFromMean = point.distanceFromPoint(mean);
			if(distanceFromMean > maxDistanceFromMean) {
				maxDistanceFromMean = distanceFromMean;
				firstCentroid = point;
			}
		}
		
		clusters.add(new Cluster(firstCentroid, clusterId));
		clusterId++;
		
		CoordinatePair previousCentroid = firstCentroid;
		for(int i = 1; i < numClusters; i++) {
			
			double distance;
			double maxDistanceFromLastCentroid;
			
			for(CoordinatePair point : points) {
				distance = distanceSums.get(point) + point.distanceFromPoint(previousCentroid);
				distanceSums.put(point, distance);
			}
			
			maxDistanceFromLastCentroid = 0;
			
			for(CoordinatePair point : points) {
				distance = distanceSums.get(point);
				
				if(distance > maxDistanceFromLastCentroid) {
					maxDistanceFromLastCentroid = distance;
					previousCentroid = point;
				}
			}
			
			clusters.add(new Cluster(previousCentroid, clusterId));
			clusterId++;
		}
		
		return clusters;
	}

}
