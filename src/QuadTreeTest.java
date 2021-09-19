import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;

public class QuadTreeTest {
	private QuadTreeTestPanel simulationPanel = new QuadTreeTestPanel(this);
	
	private QuadTree quadTree;
	private boolean isQuadTreeBuilded;
	//private Region[] regions;
	
	private List<Node> nodes = new ArrayList<Node>(); 
	
	private List<Node> selectedNodes = new ArrayList<Node>();
	
	public QuadTreeTest() {
		JFrame frame = new JFrame("Botão esq: Inserir nó | Botão dir: Buildar quadtree");
		int screenSize = 640;
		
		frame.setSize(screenSize, screenSize);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(simulationPanel);
		frame.setVisible(true);
		
		quadTree = new QuadTree(new Rectangle(0, 0, screenSize, screenSize));
		
		//quadTreeRegion = new Region(new Rectangle(0, 0, screenSize, screenSize));
		
		/*int dividedSS = screenSize / 2;
		regions = new Region[] {
			new Region(new Rectangle(0, 0, dividedSS, dividedSS)),
			new Region(new Rectangle(dividedSS, 0, dividedSS, dividedSS)),
			new Region(new Rectangle(0, dividedSS, dividedSS, dividedSS)),
			new Region(new Rectangle(dividedSS, dividedSS, dividedSS, dividedSS))
		};*/
		
		simulationPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				commandClicked(e);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		simulationPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				commandMouseMotion(e);
			}
			
		});
		
		//simulationPanel.setRegions(regions);
	}
	
	private void commandClicked(MouseEvent e) {
		if (isQuadTreeBuilded) {
			nodes.clear();
			isQuadTreeBuilded = false;
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			Node[] _nodes = new Node[nodes.size()];
			for (int i = 0; i < _nodes.length; i++)
				_nodes[i] = nodes.get(i);
			quadTree.build(_nodes);
			isQuadTreeBuilded = true;
			return;
		}
		
		//Node node = new Node(new Point(e.getX() - Node.SIZE / 2, e.getY() - Node.SIZE / 2));
		Node node = new Node(new Rectangle(e.getX() - Node.SIZE / 2, e.getY() - Node.SIZE / 2,
										   Node.SIZE, Node.SIZE));
		/*for (Region region : regions) {
			region.insertNode(node);
		}*/
		nodes.add(node);
		simulationPanel.repaint();
	}
	
	private void commandMouseMotion(MouseEvent e) {
		if (!isQuadTreeBuilded)
			return;
		
		simulationPanel.setTestRectPosition(e.getX(), e.getY());
		
		List<Node> nodeList = new ArrayList<Node>();
		
		for (Node node : selectedNodes) {
			node.setColor(Color.white);
		}
		
		/*Region region = quadTree.getRegion();
		nodeList.addAll(region.getNodesInRectangle(simulationPanel.getTestRectangle()));*/
		nodeList.addAll(quadTree.getNodesInRectangle(simulationPanel.getTestRectangle()));
		/*for (Region region : regions) {
			nodeList.addAll(region.getNodesInRectangle(simulationPanel.getTestRectangle()));
		}*/
		
		for (Node node : nodeList) {
			node.setColor(Color.green);
		}
		
		selectedNodes = nodeList;
		
		simulationPanel.repaint();
	}
	
	public void paint(Graphics g) {
		if (isQuadTreeBuilded)
			quadTree.paint(g);
		
		for (Node node : nodes)
			node.paint(g);
	}
}
