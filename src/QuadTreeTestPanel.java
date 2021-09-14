import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class QuadTreeTestPanel extends JPanel {
	private Region regions[] = new Region[0];
	private Rectangle testRect = new Rectangle(0, 0, 120, 120);
	
	public QuadTreeTestPanel() {
		this.setBackground(Color.black);
	}
	
	/*public void refreshPaint() {
		repaint();
	}*/
	
	public void setRegions(Region regions[]) {
		this.regions = regions;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.green);
		g.drawRect(testRect.x, testRect.y, testRect.w, testRect.h);
		
		for (Region region : regions) {
			region.paint(g);
		}
	}
	
	public void setTestRectPosition(int x, int y) {
		testRect.x = x - testRect.w / 2;
		testRect.y = y - testRect.h / 2;
		repaint();
	}
	
	public Rectangle getTestRectangle() {
		return testRect;
	}
}
