package model;
import java.util.ArrayList;
import java.util.LinkedList;

public interface CentroidSelector {
	
	public abstract ArrayList<Cluster> findClusters(int numClusters, LinkedList<CoordinatePair> points);
	
}
