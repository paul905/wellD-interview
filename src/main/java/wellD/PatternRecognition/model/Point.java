package wellD.PatternRecognition.model;

/**
 * The class Point represents a point in two-dimensional space
 * 
 * @author Paolo Abatemarco
 *
 */
public class Point {

	private Double x, y;

	public Point() {
		this(0.0, 0.0);
	}

	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point p) {
		this(p.x, p.y);
	}

	public Double getX() {
		return x;
	}

	public Double getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		if (obj == this)
			return true;
		Point p = (Point) obj;
		return (Double.compare(p.getX(), this.x) == 0) && (Double.compare(p.getY(), this.y) == 0);
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

}
