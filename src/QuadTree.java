import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class QuadTree {
	private Region region;
	private Rectangle defaultRegionRectangle;
	
	public QuadTree(Rectangle regionRectangle) {
		defaultRegionRectangle = regionRectangle;
	}
	
	public void build(Node[] nodes) {
		region = new Region(defaultRegionRectangle, 0);
		region.subdivide();
		insertNodes(nodes, region.getChildren());
		
		/*List<Node> nodeList = new ArrayList<Node>();
		nodeList.addAll(Arrays.asList(nodes));
		debugRegion(region, nodeList);
		
		System.out.println("Órfãos: " + nodeList);*/
	}
	
	/*private void debugRegion(Region region, List<Node> nodeList) {
		Region[] children = region.getChildren();
		if (children != null)
			for (Region _region : children)
				debugRegion(_region, nodeList);
		
		nodeList.removeAll(Arrays.asList(region.getNodes()));
	}*/
	
	private void insertNodes(Node[] nodes, Region[] regions) {
		//List<Node> ignoreNodes = new ArrayList<Node>();
		
		for (Node node : nodes) {
			/*if (ignoreNodes.contains(node))
				break;*/
			
			for (Region region : regions) {
				if (!region.reachedMaxNodesCount()) {
					if (region.insertNode(node)) {
						if (region.reachedMaxNodesCount()) {
							//printDebug(name ,"Aconteceu");
							
							Node[] regionNodes = region.getNodes();
							//List<Node> _regionNodes = Arrays.asList(regionNodes);
							
							//ignoreNodes.addAll(_regionNodes);
							
							//Random random = new Random();
							if (region.subdivide())
								insertNodes(regionNodes, region.getChildren());	
						}
						
						break;
					}
				}
				else {
					Region[] c = region.getChildren();
					if (c != null)
						insertNodes(new Node[] { node }, region.getChildren());
				}
			}

			/*String nodeArrayOutput = "\n" + name + "{";
			for (int j = 0; j < nodes.size(); j++) {
				if (i == j) {
					nodeArrayOutput += "\n===>";
				}
				else {
					nodeArrayOutput += "\n";
				}
				nodeArrayOutput += "[" + j + "]: " + nodes.get(j);
			}
			nodeArrayOutput += "\n}\n";
			printDebug(name, nodeArrayOutput);*/
		}
	}
	
	/*private void printDebug(String name, String message) {
		//if (name == "FIRST")
			System.out.println("[" + name + "] " + message);
	}*/
	
	/*private void insertNodes(String name, List<Node> nodes, Region[] regions) {
		//name = "[" + name + "] ";
		int i = 0;
		printDebug(name, "Nodes: " + nodes);
		printDebug(name, "Regions: " + regions);
		
		boolean nextIndex = true;
		while (i < nodes.size() && !nodes.isEmpty()) {
			printDebug(name, "" + i);
			for (Region region : regions) {
				nextIndex = true;
				
				if (!region.reachedMaxNodesCount()) {
					if (region.insertNode(nodes.get(i))) {
						if (region.reachedMaxNodesCount()) {
							printDebug(name ,"Aconteceu");
							
							List<Node> regionNodes = new ArrayList<Node>();
							regionNodes.addAll(Arrays.asList(region.getNodes()));

							Node nextNode = null;
							for (int j = i + 1; j < nodes.size(); j++) {
								for (Node rn : regionNodes) {
									Node n = nodes.get(j);
									if (n != rn) {
										nextNode = n;
										break;
									}
								}
							}
							
							nodes.removeAll(regionNodes);
							printDebug(name, "RegionNodes_Size: " + regionNodes.size());
							//i--;
							//i -= regionNodes.size() - 1;
							//i -= regionNodes.size() / 2;
							i = nodes.indexOf(nextNode);
							printDebug(name, "PRÓXIMO = " + i);
							
							nextIndex = false;
							
							Random random = new Random();
							insertNodes("" + random.nextInt(), regionNodes, region.subdivide());
						}
						
						break;
					}
				}
			}

			String nodeArrayOutput = "\n" + name + "{";
			for (int j = 0; j < nodes.size(); j++) {
				if (i == j) {
					nodeArrayOutput += "\n===>";
				}
				else {
					nodeArrayOutput += "\n";
				}
				nodeArrayOutput += "[" + j + "]: " + nodes.get(j);
			}
			nodeArrayOutput += "\n}\n";
			printDebug(name, nodeArrayOutput);
			
			if (nextIndex) {
				printDebug(name, "Somoou??");
				i++; // <-
			}
		}
	}*/
	
	/*public void build(Node[] nodes) {
		region = new Region(defaultRegionRectangle);
		
		Queue<Region> regionQueue = new LinkedList<Region>();
		Queue<Node> nodeQueue = new LinkedList<Node>();
		
		regionQueue.addAll(Arrays.asList(region.subdivide()));
		nodeQueue.addAll(Arrays.asList(nodes));

		while (!nodeQueue.isEmpty() && !regionQueue.isEmpty()) {
			System.out.println("porra");
			Region r = regionQueue.peek();

			if (!r.reachedMaxNodesCount()) {
				if (r.insertNode(nodeQueue.peek())) {
					nodeQueue.poll();
					
					if (r.reachedMaxNodesCount()) {
						nodeQueue.addAll(Arrays.asList(r.getNodes()));
						
						regionQueue.addAll(Arrays.asList(r.subdivide()));
						regionQueue.poll();
					}	
				}
				else {
					regionQueue.add(regionQueue.poll());
				}
			}
			else {
				regionQueue.poll();
			}
		}
	}*/
	
	/*private int insertNodeInAvailableRegions(Node node, List<Region> availableRegions, int index) {
		System.out.println(index);
		Region r = availableRegions.get(index);

		if (!r.reachedMaxNodesCount()) {
			r.insertNode(node);
			if (r.reachedMaxNodesCount()) {
				availableRegions.addAll(Arrays.asList(r.subdivide()));
				availableRegions.remove(index);

				insertNodeInAvailableRegions(node, availableRegions, index);
				//unavailableRegions.add(r);
				
				return index;
			}
			
			return index + 1;
		}
		else {
			availableRegions.remove(index);
			//unavailableRegions.add(r);
		}
		
		return index;
	}*/
	
	public List<Node> getNodesInRectangle(Rectangle area) {
		return region.getNodesInRectangle(area);
	}
	
	public void paint(Graphics g) {
		if (region != null)
			region.paint(g);
	}
}
