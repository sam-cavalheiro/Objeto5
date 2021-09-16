import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;

public class Simulation {
	private SimulationPanel simulationPanel;
	
	//private Region region;
	private QuadTree quadTree;
	private Particle[] particles = new Particle[600];
	
	public Simulation() {
		int screenSize = 640;
		
		quadTree = new QuadTree(new Rectangle(0, 0, screenSize, screenSize));
		simulationPanel = new SimulationPanel(this);
		
		JFrame frame = new JFrame("Alisson, Laport e Sam - Objeto 5");
		
		frame.setSize(screenSize, screenSize);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(simulationPanel);
		frame.setVisible(true);
		
		//region = new Region(new Rectangle(0, 0, screenSize, screenSize));
		
		Random random = new Random();
		for (int i = 0; i < particles.length; i++) {
			//particles[i] = new Particle(new Point(random.nextInt(screenSize), random.nextInt(screenSize)));//(new Point((screenSize - particles.length) / 2 + i + random.nextInt(screenSize), (screenSize - particles.length) / 2 + i + random.nextInt(screenSize)));
			particles[i] = new Particle(new Rectangle(random.nextInt(screenSize), random.nextInt(screenSize), Node.SIZE, Node.SIZE));
		}
		
		quadTree.build(particles);
	}
	
	public void update() {
		for (Particle particle : particles) {
			particle.setColor(Color.white);
			particle.movement();
		}
		
		// Sem QuadTree
		/*for (Particle p1 : particles) {
			//Main.interactions++;
			for (Particle p2 : particles) {
				Main.interactions++;
				if (p1 != p2 && p1.rectangle.intersect(p2.rectangle))
					p2.setColor(Color.red);
			}
		}*/
		
		// Com QuadTree
		for (Particle particle : particles) {
			Main.interactions++;
			for (Node node : quadTree.getNodesInRectangle(particle.rectangle)) {
				if (node != particle)
					node.setColor(Color.red);
			}
		}
		
		System.out.println("Interações: " + Main.interactions);
		Main.interactions = 0;
		
		quadTree.build(particles);
		
		//simulationPanel.invalidate();
		//simulationPanel.validate();
		simulationPanel.repaint();
	}
	
	/*public Particle[] getParticles() {
		return particles;
	}*/
	
	public void paint(Graphics g) {
		quadTree.paint(g);
		
		for (Particle particle : particles)
			particle.paint(g);
	}
}
