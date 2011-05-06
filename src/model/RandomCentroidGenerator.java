package model;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class RandomCentroidGenerator implements CentroidSelector{

	public ArrayList<Cluster> findClusters(int numClusters, LinkedList<CoordinatePair> points) {
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

}