
public class Rectangle {
	public int x,y, w, h;
	
	public Rectangle(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public boolean containsPoint(Point point) {
		return (point.x >= x && point.x <= x + w && point.y >= y && point.y <= y + w);
	}
	
	public boolean intersect(Rectangle other) {
		return x <= other.x + other.w && x + w >= other.x &&
			   y <= other.y + other.h && y + h >= other.y;
		
		/*return ( (other.x >= x && other.x <= x + w) ||
				 (other.x + other.w >= x && other.x + other.w <= x + w)) &&
				((other.y >= y && other.y <= y + h) ||
				 (other.y + other.h >= y && other.y + other.h <= y + h));*/
		
		/*return ((x >= x && x <= x + w) ||
				 (x + w >= x && x + w <= x + w)) &&
				((y >= y && y <= y + h) ||
				 (y + h >= y && y + h <= y + h));*/
	}
}
