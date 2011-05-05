/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataminingkmeans;
import javax.swing.*;

/**
 *
 * @author Kyle
 */
public class Main {
    
	public static void main(String[] args) {
	
	    int[] dataX = {
	        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 15, 60
	    };
	
	    int[] dataY = {
	        0, 1, 2, 3, 4, 5, 6, 7, 20, 15, 30, 60
	    };
	
	    String[] pointColor = {
	        "red", "red", "blue","blue","green","green","yellow","yellow",
	        "black", "black", "red", "blue"
	    };
	
	    JFrame f = new JFrame();
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.add(new GraphingData(dataX, dataY, pointColor));
	    f.setSize(500,500);
	    f.setLocation(100,100);
	    f.setVisible(true);
	}

}
