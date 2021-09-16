import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Region {
	private Rectangle rectangle;
	private int index;
	
	private Region[] children;
	//private List<Node> nodes = new ArrayList<Node>();
	private Node[] nodes = new Node[MAX_NODES];
	private int nodesCount = 0;
	
	private static final int MAX_NODES = 2;
	
	public Region(Rectangle rectangle) {
		this.rectangle = rectangle;
		index = Main.lastRegionIndex++;
	}
	
	/*public void insertNode(Node node) {
		//if (rectangle.containsPoint(node.point)) {
		if (rectangle.intersect(node.rectangle)) {
			nodes[nodesCount++] = node;
			
			if (children != null) {
				for (Region region : children) {
					region.insertNode(node);
				}	
			}
		}
	}*/
	
	public boolean tryAddNode(Node node) {
		//if (rectangle.containsPoint(node.point)) {
		if (rectangle.intersect(node.rectangle)) {
			
			if (children == null) {
				nodes[nodesCount++] = node;
				if (nodesCount < MAX_NODES)
					return true;
				
				subdivide();
			}
			
			for (Region region : children) {
				region.tryAddNode(node);
				/*if (region.tryAddNode(node))
					return true;*/
			}
			
			return true;
		}
		
		return false;
	}
	
	public void subdivide() {
		int _x = rectangle.x;
		int _y = rectangle.y;
		int _w = rectangle.w / 2;
		int _h = rectangle.h / 2;
		
		if (index == 2)
			System.out.println("Original: " + rectangle.w + "\nRestante: " + _x + ", " + _y + " | " + _w + "x" + _h);
		
		children = new Region[] {
				new Region(new Rectangle(_x, _y, _w, _h)),
				new Region(new Rectangle(_x + _w, _y, _w, _h)),
				new Region(new Rectangle(_x, _y + _h, _w, _h)),
				new Region(new Rectangle(_x + _w, _y + _h, _w, _h))
		};
		
		/*for (int i = 0; i < MAX_NODES; i++) {
			Node n = nodes[i];
			for (Region region : children)
				if (region.tryAddNode(n))
					break;
		}*/
	}
	
	public List<Node> getNodesInRectangle(Rectangle area) {
		List<Node> _nodes = new ArrayList<Node>();
		Main.interactions++;
		if (rectangle.intersect(area)) {
			//for (Node node : nodes) {
			for (int i = 0; i < nodesCount; i++) {
				Main.interactions++;
				Node node = nodes[i];
				if (area.intersect(node.rectangle))//(area.containsPoint(node.point))
					_nodes.add(node);
			}
			
			if (children != null) {
				for (Region region : children) {
					_nodes.addAll(region.getNodesInRectangle(area));
				}
			}
		}
		
		return _nodes;
	}
	
	public void recursivePrint() {
		String message = "---------\nRegion [" + index + "]:\n(" + rectangle.w + "x" + rectangle.h + ")";
		for (int i = 0; i < nodesCount; i++)
			message += "\n" + nodes[i].getIndex();
		
		System.out.println(message);
		
		if (children != null) {
			for (Region region : children)
				region.recursivePrint();	
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
		
		// Agora são desenhados na classe principal
		/*for (Node node : nodes)
			node.paint(g);*/
		
		if (children != null) {
			for (Region region : children)
				region.paint(g);	
		}
	}
}
