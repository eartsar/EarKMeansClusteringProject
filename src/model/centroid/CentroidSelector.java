package model.centroid;
import java.util.ArrayList;
import java.util.LinkedList;

import model.objects.Cluster;
import model.objects.CoordinatePair;

public interface CentroidSelector {
	
	public abstract ArrayList<Cluster> findClusters(int numClusters, LinkedList<CoordinatePair> points);
	
}
