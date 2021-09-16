import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class QuadTreeTestPanel extends JPanel {
	//private Region regions[] = new Region[0];
	private Rectangle testRect = new Rectangle(0, 0, 160, 160);///120, 120);
	
	private QuadTreeTest quadTreeTest;
	
	public QuadTreeTestPanel(QuadTreeTest quadTreeTest) {
		this.quadTreeTest = quadTreeTest;
		this.setBackground(Color.black);
	}
	
	/*public void refreshPaint() {
		repaint();
	}*/
	
	/*public void setRegions(Region regions[]) {
		this.regions = regions;
	}*/
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.green);
		g.drawRect(testRect.x, testRect.y, testRect.w, testRect.h);
		
		quadTreeTest.paint(g);
		/*for (Region region : regions) {
			region.paint(g);
		}*/
	}
	
	public void setTestRectPosition(int x, int y) {
		testRect.x = x - testRect.w / 2;
		testRect.y = y - testRect.h / 2;
	}
	
	public Rectangle getTestRectangle() {
		return testRect;
	}
}
