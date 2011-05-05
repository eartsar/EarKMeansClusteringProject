/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

// Import Packages
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.lang.reflect.Field;
import javax.swing.*;

// Create Graph
public class GraphingData extends JPanel {
   
	private static final long serialVersionUID = 1L;
	
	final int PAD = 20;
    double[] dataX, dataY;
    String[] pointColor;

    public GraphingData(double[] x, double[] y, String[] color) {
        dataX = x;
        dataY = y;
        pointColor = color;
    }
    
    // Override paintComponent
    protected void paintComponent(Graphics g) {

        // Set up Graphics
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();

        // Draw Y-Axis
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
        
        // Draw X Axis
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));

        // Set Label
        Font font = g2.getFont();
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();

        // Y Axis label
        String s = "Y-Axis";
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        // X Axis label
        s = "X-Axis";
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);

        // Draw lines.
        double xInc = (double)(w - 2*PAD)/getMaxX();
        double scale = (double)(h - 2*PAD)/getMaxY();
        
        // Mark data points.
        for(int i = 0; i < dataX.length; i++) {

            // Try to find color string
            try {
                Field temp = Color.class.getField(pointColor[i]);
                g2.setPaint((Color) temp.get(null));
            } catch (Exception e) { // If not found, set black
                g2.setPaint(Color.black);
            }

            // Set Points with scale
            double x = PAD + dataX[i]*xInc;
            double y = h - PAD - scale*dataY[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }

    }

    // Find Max X
    private double getMaxX() {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < dataX.length; i++) {
            if(dataX[i] > max)
                max = dataX[i];
        }
        return max;
    }

    // Find Max Y
    private double getMaxY() {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < dataY.length; i++) {
            if(dataY[i] > max)
                max = dataY[i];
        }
        return max;
    }
}
