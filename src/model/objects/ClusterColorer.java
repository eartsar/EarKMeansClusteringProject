package model.objects;


import java.awt.Color;
import java.util.HashMap;

public class ClusterColorer {

	private HashMap<Integer, Color> colors;
	public static ClusterColorer INSTANCE = new ClusterColorer();
	
	private ClusterColorer() {
		colors = new HashMap<Integer, Color>();
		colors.put(0, Color.BLUE);
		colors.put(1, Color.RED);
		colors.put(2, Color.GREEN);
		colors.put(3, Color.PINK);
		colors.put(4, Color.CYAN);
		colors.put(5, Color.MAGENTA);
		colors.put(6, Color.ORANGE);
		colors.put(7, Color.YELLOW);
		colors.put(8, Color.BLACK);
		colors.put(9, Color.GRAY);
	}
	
	public Color getColor(int id) {
		return colors.get(id);
	}
}
