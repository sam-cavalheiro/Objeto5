import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Region {
	private Rectangle rectangle;
	
	private Region[] children;
	//private List<Node> nodes = new ArrayList<Node>();
	private Node[] nodes = new Node[MAX_NODES];
	private int nodesCount = 0;
	private int subdivisionStack;
	
	private static final int MAX_NODES = 2;
	private static final int MAX_SUBDIVISION = 100;
	
	public Region(Rectangle rectangle, int subdivisionStack) {
		this.rectangle = rectangle;
		this.subdivisionStack = subdivisionStack;
	}
	
	public boolean insertNode(Node node) {
		//if (rectangle.containsPoint(node.point)) {
		if (rectangle.intersect(node.rectangle)) {
			nodes[nodesCount++] = node;
			return true;
		}
		
		return false;
	}
	
	/*public boolean insertNode(Node node) {
		//if (rectangle.containsPoint(node.point)) {
		if (rectangle.intersect(node.rectangle)) {
			
			if (children == null) {
				nodes[nodesCount++] = node;
				if (nodesCount < MAX_NODES)
					return true;
				
				subdivide();
			}
			
			for (Region region : children) {
				region.insertNode(node);
			}
			
			return true;
		}
		
		return false;
	}*/
	
	public boolean subdivide() {
		if (subdivisionStack >= MAX_SUBDIVISION)
			return false;
		
		int _subdivisionStack = subdivisionStack + 1;
		
		int _x = rectangle.x;
		int _y = rectangle.y;
		int _w = rectangle.w / 2;
		int _h = rectangle.h / 2;
		
		children = new Region[] {
				new Region(new Rectangle(_x, _y, _w, _h), _subdivisionStack),
				new Region(new Rectangle(_x + _w, _y, _w, _h), _subdivisionStack),
				new Region(new Rectangle(_x, _y + _h, _w, _h), _subdivisionStack),
				new Region(new Rectangle(_x + _w, _y + _h, _w, _h), _subdivisionStack)
		};
		
		return true;
	}
	
	public List<Node> getNodesInRectangle(Rectangle area) {
		List<Node> _nodes = new ArrayList<Node>();

		if (rectangle.intersect(area)) {
			//for (Node node : nodes) {
			for (int i = 0; i < nodesCount; i++) {
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
	
	public Node[] getNodes() {
		return nodes;
	}
	
	public boolean reachedMaxNodesCount() {
		return nodesCount == MAX_NODES;
	}
	
	/*public void recursivePrint() {
		String message = "---------\nRegion [" + index + "]:\n(" + rectangle.w + "x" + rectangle.h + ")";
		for (int i = 0; i < nodesCount; i++)
			message += "\n" + nodes[i].getIndex();
		
		System.out.println(message);
		
		if (children != null) {
			for (Region region : children)
				region.recursivePrint();	
		}
	}*/
	
	public Region[] getChildren() { // só pra testes
		return children;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.white);
		//g.fillRect(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
		g.drawRect(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
		
		if (children != null) {
			for (Region region : children)
				region.paint(g);	
		}
	}
}
