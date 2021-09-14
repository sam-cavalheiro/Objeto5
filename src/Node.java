import java.awt.Color;
import java.awt.Graphics;

public class Node {
	public Point point;
	private boolean isSelected;
	
	public static final byte SIZE = 5;
	
	public Node(Point point) {
		this.point = point;
	}
	
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	public void paint(Graphics g) {
		if (isSelected) {
			g.setColor(Color.green);
		}
		else {
			g.setColor(Color.white);	
		}
		g.fillRect(point.x, point.y, SIZE, SIZE);
	}
}
