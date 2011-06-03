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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public Point sub(Point p) {
		return new Point(this.x - p.x, this.y - p.y);
	}

	public Point add(int i, int j) {
		return add(new Point(i, j));
	}

	public Point sub(int i, int j) {
		return sub(new Point(i, j));
	}
}
