import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;

public class GuiInitialize implements ActionListener{
	
	private JButton clusterOne;
    private JButton clusterTwo;
    private JButton clusterThree;
    private JButton clear;
    
	public GuiInitialize(Cluster cluster) {
		LinkedList<CoordinatePair> dataPoints = cluster.getPoints();
		double[] x = new double[dataPoints.size()];
		double[] y = new double[dataPoints.size()];
		for(int i=0; i<dataPoints.size(); i++)
		{
			x[i] = dataPoints.get(i).x;
			y[i] = dataPoints.get(i).y;
		}
	
	    String[] pointColor = {
	        "red", "red", "blue","blue","green","green","yellow","yellow",
	        "black", "black", "red", "blue"
	    };
	    
	    JButton clusterOne = new JButton("ClusterOne");
	    clusterOne.addActionListener(this);
	    JButton clusterTwo = new JButton("ClusterTwo");
	    clusterTwo.addActionListener(this);
	    JButton clusterThree = new JButton("ClusterThree");
	    clusterThree.addActionListener(this);
	    JButton clear = new JButton("Clear");
	    clear.addActionListener(this);
	    
	    
	    JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLayout( new FlowLayout());
	    f.add(new GraphingData(x, y, pointColor));
	    f.add(clusterOne);
	    f.add(clusterTwo);
	    f.add(clusterThree);
	    f.add(clear);
	    f.setSize(500,500);
	    f.setLocation(100,100);
	    f.setVisible(true);
	   
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == clusterOne) {
			//cluster fuck this way
		} else if(source == clusterTwo) {
			//cluster fuck that way
		} else if( source == clusterThree) {
			//errrbody clusterfuck
		} else if(source == clear){
			//knock it off with the cluster fucking
		}
		
	}
}



