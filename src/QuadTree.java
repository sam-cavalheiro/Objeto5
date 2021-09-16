import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	private Region region;
	private Rectangle defaultRegionRectangle;
	
	public QuadTree(Rectangle regionRectangle) {
		defaultRegionRectangle = regionRectangle;
	}
	
	public void build(Node[] nodes) {
		region = new Region(defaultRegionRectangle);
		region.subdivide();
		
		for (Node node : nodes)
			region.tryAddNode(node);
	}
	
	public List<Node> getNodesInRectangle(Rectangle area) {
		/*List<Node> _nodes = new ArrayList<Node>();
		
		for (Region region : regions)
			_nodes.addAll(region.getNodesInRectangle(area));
		
		return _nodes;*/
		return region.getNodesInRectangle(area);
	}
	
	public void printRegions() {
		region.recursivePrint();
	}
	
	/*public Region getRegion() {
		return region;
	}*/
	
	public void paint(Graphics g) {
		region.paint(g);
	}
}
