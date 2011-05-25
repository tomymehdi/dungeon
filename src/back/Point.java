package back;

public class Point {
	public int x;
	public int y;

	public Point(Point p) {
		this(p.x, p.y);
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point add(Point p) {
		return new Point(this.x + p.x, this.y + p.y);
	}

	@Override
	public String toString() {
		return "[ " + x + ", " + y + " ]";
	}

	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point pt = (Point) obj;
			return (x == pt.x) && (y == pt.y);
		}
		return super.equals(obj);
	}
}
