import java.awt.Color;
import java.awt.Graphics;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JFrame;

public class Simulation {
	private SimulationPanel simulationPanel;
	
	//private Region region;
	private QuadTree quadTree;
	private Particle[] particles = new Particle[600];
	
	private FileWriter file;
	
	private static final boolean USE_QUADTREE = true;
	
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
		
		try {
			file = new FileWriter("speedup.txt");
			file.write("Quantia de particulas:;" + particles.length + "\nQuadtree:;" + String.valueOf(USE_QUADTREE) + "\nTempo:");
			file.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		long startTime = System.currentTimeMillis();
		
		if (USE_QUADTREE) {
			for (Particle particle : particles) {
				for (Node node : quadTree.getNodesInRectangle(particle.rectangle)) {
					if (node != particle)
						node.setColor(Color.red);
				}
			}
		}
		else {
			for (Particle p1 : particles) {
				for (Particle p2 : particles) {
					if (p1 != p2 && p1.rectangle.intersect(p2.rectangle))
						p2.setColor(Color.red);
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		long executionTime = endTime - startTime;
		
		try {
			file.write("\n" + String.valueOf(executionTime));
			file.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (USE_QUADTREE)
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
