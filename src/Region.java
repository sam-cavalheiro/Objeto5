import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Region {
	private Rectangle rectangle;
	
	private Region[] children;
	private Node[] nodes = new Node[MAX_NODES];
	private int nodesCount = 0;
	
	private static final int MAX_NODES = 2;
	
	public Region(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public boolean tryAddNode(Node node) {
		if (rectangle.containsPoint(node.point)) {
			
			if (children == null) {
				nodes[nodesCount++] = node;
				if (nodesCount < MAX_NODES)
					return true;
				
				subdivide();
			}
			
			for (Region region : children) {
				if (region.tryAddNode(node))
					return true;
			}
		}
		
		return false;
	}
	
	private void subdivide() {
		int _x = rectangle.x;
		int _y = rectangle.y;
		int _size = rectangle.w / 2;
		
		children = new Region[] {
				new Region(new Rectangle(_x, _y, _size, _size)),
				new Region(new Rectangle(_x + _size, _y, _size, _size)),
				new Region(new Rectangle(_x, _y + _size, _size, _size)),
				new Region(new Rectangle(_x + _size, _y + _size, _size, _size))
		};
		
		/*for (int i = 0; i < MAX_NODES; i++) {
			Node n = nodes[i];
			for (Region region : children)
				if (region.tryAddNode(n))
					break;
		}*/
	}
	
	public List<Node> getNodesInRectangle(Rectangle area) {
		List<Node> nodeList = new ArrayList<Node>();
		
		if (rectangle.intersect(area)) {
			for (int i = 0; i < nodesCount; i++) {
				Node node = nodes[i];	
				if (area.containsPoint(node.point))
					nodeList.add(node);
			}
			
			if (children != null) {
				for (Region region : children) {
					nodeList.addAll(region.getNodesInRectangle(area));
				}
			}
		}
		
		return nodeList;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		g.drawRect(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
		
		for (int i = 0; i < nodesCount; i++)
			nodes[i].paint(g);
		
		if (children != null) {
			for (Region region : children)
				region.paint(g);	
		}
	}
}
