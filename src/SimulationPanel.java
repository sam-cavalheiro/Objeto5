import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class SimulationPanel extends JPanel {
	private Simulation simulation;
	//private Particle[] particles;
	
	public SimulationPanel(Simulation simulation) {
		this.simulation = simulation;
		
		this.setBackground(Color.black);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		simulation.paint(g);
	}
}
