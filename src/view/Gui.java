package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import model.algorithm.Algorithm;

@SuppressWarnings("serial")
public class Gui extends JFrame implements ActionListener {

	public Algorithm model;
	private JButton graphButton;
	private GraphPanel graphPanel;
	private JPanel controlPanel;
	private JTextField iterationField;
	private JTextField clusterField;
	private JComboBox centroidAlgorithmBox;
	
	public Gui(Algorithm model) {
		this.model = model;
		
		this.setLayout(new BorderLayout());
		this.setSize(800, 600);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		
		String[] centroidAlgorithms = {"Random Point Generation", "Random Point Selection", "Pillar Selection", "Refinement Selection"};
		centroidAlgorithmBox = new JComboBox(centroidAlgorithms);
		centroidAlgorithmBox.setSelectedIndex(0);
		
		
		graphButton = new JButton();
		graphButton.setText("Graph");
		graphButton.addActionListener(this);
		
		iterationField = new JTextField();
		iterationField.setText("Number of Iterations");
		
		clusterField = new JTextField();
		clusterField.setText("Number of Clusters");

		controlPanel.add(centroidAlgorithmBox);
		controlPanel.add(clusterField);
		controlPanel.add(iterationField);
		controlPanel.add(graphButton);
		
		graphPanel = new GraphPanel(this);
		
		this.add(controlPanel, BorderLayout.SOUTH);
		this.add(graphPanel, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent source) {
	
		if(source.getSource() == graphButton) {
			
			//check args
			int clusters = 1;
			int iterations = 1;
			
			try {
				clusters = Integer.parseInt(clusterField.getText());
				iterations = Integer.parseInt(iterationField.getText());
			} catch(Exception e) {
				// bad input
				JOptionPane.showMessageDialog(this, "Clusters and iterations should be a number greater than 1.");
				return;
			}
			
			if(clusters < 1 || iterations < 1) {
				JOptionPane.showMessageDialog(this, "Clusters and iterations should be a number greater than 1.");
				return;
			}
			if(clusters > 10) {
				JOptionPane.showMessageDialog(this, "Clusters should not be greater than 10.");
				return;
			}

			String centroidMethod = (String)centroidAlgorithmBox.getSelectedItem();
			model.run(clusters, iterations, centroidMethod);
			
			double[] xPoints = model.getAllXPoints();
			double[] yPoints = model.getAllYPoints();
			Color[] pointColors = model.getAllPointColors();
			
			graphPanel.setPoints(xPoints, yPoints, pointColors);
		}
	}
}
