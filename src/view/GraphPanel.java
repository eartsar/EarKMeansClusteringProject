package view;


// Import Packages
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;



// Create Graph
public class GraphPanel extends JPanel {
   
	private static final long serialVersionUID = 1L;
	
	final int PADDING = 20;
    double[] xPoints;
    double[] yPoints;
    Color[] pointColors;
    
    double[] xCentroidPoints;
    double[] yCentroidPoints;
    
    private Gui parentFrame;

    public GraphPanel(Gui parentFrame) {
    	this.parentFrame = parentFrame;
    	this.xPoints = null;
    	this.yPoints = null;
    	this.pointColors = null;
    	
    	this.xCentroidPoints = null;
    	this.yCentroidPoints = null;
    }
    
    public void setPoints(double[] xPoints, double[] yPoints, Color[] pointColors) {
    	this.xPoints = xPoints;
    	this.yPoints = yPoints;
    	this.pointColors = pointColors;
    	
    	this.repaint();
    }
    
    public void setCentroidPoints(double[] xCentroidPoints, double[] yCentroidPoints) {
    	this.xCentroidPoints = xCentroidPoints;
    	this.yCentroidPoints = yCentroidPoints;
    }
    
    // Override paintComponent
    protected void paintComponent(Graphics g) {

        // Set up Graphics (taken from http://www.codeguru.com/forum/showthread.php?threadid=510665)
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();

        // Draw Y-Axis
        g2.draw(new Line2D.Double(PADDING, PADDING, PADDING, h-PADDING));
        
        // Draw X Axis
        g2.draw(new Line2D.Double(PADDING, h-PADDING, w-PADDING, h-PADDING));

        // Set Label
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();

        // Y Axis label
        String s = "Y-Axis";
        float sy = PADDING + ((h - 2*PADDING) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PADDING - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // X Axis label
        s = "X-Axis";
        sy = h - PADDING + (PADDING - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);

        // Draw lines.
        double xInc = (double)(w - 2*PADDING)/parentFrame.model.getMaxX();
        double scale = (double)(h - 2*PADDING)/parentFrame.model.getMaxY();
        

        // if we don't have points, don't plot them
        if(xPoints == null || yPoints == null || pointColors == null) {
        	return;
        }
        
        // otherwise, if we do, plot them out
        for(int i = 0; i < xPoints.length; i++) {

        	g2.setPaint(pointColors[i]);
        	
            // Set Points with scale
            double x = PADDING + xPoints[i]*xInc;
            double y = h - PADDING - scale*yPoints[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
        
        // check if centroids were defined
        if(xCentroidPoints == null || yCentroidPoints == null) {
        	return;
        }
        
     // otherwise, if we do, plot them out
        for(int i = 0; i < xCentroidPoints.length; i++) {

        	g2.setPaint(Color.RED);
        	
            // Set Points with scale
            double x = PADDING + xCentroidPoints[i]*xInc;
            double y = h - PADDING - scale*yCentroidPoints[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }

}
