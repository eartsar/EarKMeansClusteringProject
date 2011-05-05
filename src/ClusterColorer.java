

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
	}
	
	public Color getColor(int id) {
		return colors.get(id);
	}
}
