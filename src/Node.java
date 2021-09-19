import java.awt.Color;
import java.awt.Graphics;

public class Node {
	public Rectangle rectangle;
	private Color color = Color.white;
	//public Point point;
	
	public static final byte SIZE = 5;
	
	public Node(Rectangle rectangle) {
		this.rectangle = rectangle;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(rectangle.x, rectangle.y, rectangle.w, rectangle.h);
		//g.fillRect(point.x, point.y, SIZE, SIZE);
	}
}
