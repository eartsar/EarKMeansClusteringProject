package model.centroid;

import java.util.ArrayList;
import java.util.LinkedList;

import model.algorithm.Algorithm;
import model.objects.Cluster;
import model.objects.CoordinatePair;

/**
 * This class uses the elements of the Bradley-Fayyad technique as noted 
 * in the following paper: ftp://ftp.research.microsoft.com/pub/tr/tr-98-36.pdf
 * as well as our own methods. The algorithm and explanation can be found in
 * the paper that goes along with this program.
 * 
 * @author Eitan Romanoff
 */
public class PillarWebRefinementCentroidSelector implements CentroidSelector {
	
	private Algorithm algorithm;
	
	public PillarWebRefinementCentroidSelector(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public ArrayList<Cluster> findClusters(int numClusters, LinkedList<CoordinatePair> points) {
		
		algorithm.run(numClusters, 1, "Pillar Selection");
		ArrayList<Cluster> pillarClusters = algorithm.getClusters();
		
		ArrayList<ArrayList<Cluster>> randomClusterSet = new ArrayList<ArrayList<Cluster>>();
		
		for(int i = 0; i < 3; i++) {
			algorithm.run(numClusters, 1, "Random Selection");
			randomClusterSet.add(algorithm.getClusters());
		}
		
		ArrayList<Cluster> webClusters = new ArrayList<Cluster>();
		
		int clusterId = 0;
		for(Cluster base : pillarClusters) {
			base.calculateCentroid();
			base.clearPoints();
			
			double averageX = base.getCentroid().x;
			double averageY = base.getCentroid().y;
			
			for(ArrayList<Cluster> setR : randomClusterSet) {
				double tempX = 0;
				double tempY = 0;
				
				for(Cluster support : setR) {
					double minDistance = Double.MAX_VALUE;
					
					if(support.getCentroid().distanceFromPoint(base.getCentroid()) < minDistance) {
						tempX = support.getCentroid().x;
						tempY = support.getCentroid().y;
						minDistance = support.getCentroid().distanceFromPoint(base.getCentroid());
					}
				}
				
				averageX = averageX + tempX;
				averageY = averageY + tempY;
			}
			
			averageX = averageX / 4;
			averageY = averageY / 4;
			
			webClusters.add(new Cluster(new CoordinatePair(averageX, averageY), clusterId));
			clusterId++;
		}
		
		return webClusters;
	}

}
