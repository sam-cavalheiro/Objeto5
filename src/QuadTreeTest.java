import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class QuadTreeTest {
	private QuadTreeTestPanel simulationPanel = new QuadTreeTestPanel();
	
	private Region[] regions;
	private List<Node> selectedNodes = new ArrayList<Node>();
	
	public QuadTreeTest() {
		JFrame frame = new JFrame("Teste!!!!");
		int screenSize = 640;
		
		frame.setSize(screenSize, screenSize);
		//frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(simulationPanel);
		frame.setVisible(true);
		
		int dividedSS = screenSize / 2;
		regions = new Region[] {
			new Region(new Rectangle(0, 0, dividedSS, dividedSS)),
			new Region(new Rectangle(dividedSS, 0, dividedSS, dividedSS)),
			new Region(new Rectangle(0, dividedSS, dividedSS, dividedSS)),
			new Region(new Rectangle(dividedSS, dividedSS, dividedSS, dividedSS))
		};
		
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
		
		//regions[0].addNode(new Node(60, 30));
		simulationPanel.setRegions(regions);
	}
	
	private void commandClicked(MouseEvent e) {
		Node node = new Node(new Point(e.getX() - Node.SIZE / 2, e.getY() - Node.SIZE / 2));
		for (Region region : regions) {
			if (region.tryAddNode(node))
				break;
		}
		simulationPanel.repaint();
	}
	
	private void commandMouseMotion(MouseEvent e) {
		simulationPanel.setTestRectPosition(e.getX(), e.getY());
		
		List<Node> nodeList = new ArrayList<Node>();
		
		for (Node node : selectedNodes) {
			node.setSelected(false);
		}
		
		for (Region region : regions) {
			nodeList.addAll(region.getNodesInRectangle(simulationPanel.getTestRectangle()));
		}
		
		for (Node node : nodeList) {
			node.setSelected(true);
		}
		
		selectedNodes = nodeList;
	}
}
