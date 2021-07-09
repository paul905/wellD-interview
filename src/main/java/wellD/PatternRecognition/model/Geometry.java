package wellD.PatternRecognition.model;

import org.springframework.stereotype.Component;

/**
 * The class Geometry contains the algorithms for performing operations
 * 
 * @author Paolo Abatemarco
 *
 */
@Component
public class Geometry {

	/**
	 * Given two points , this method returns the {@link Line} between the two
	 * points: {@code p0} and {@code p1}.<br>
	 * <br>
	 * 
	 * @param p1 Point
	 * @param p2 Point
	 * 
	 * @return A {@link Line} in terms of slope and intercept. <br>
	 * 
	 *         If the slope is {@code NaN} , the line is parallel to Y axis. <br>
	 *         If the slope is {@code 0} , the line is parallel to X axis.
	 * 
	 */
	public Line lineBetweenTwoPoints(Point p0, Point p1) {

		/*
		 * Line equation between two points : (y-y0)/(y1-y0)=(x-x0)/(x1-x0) Slope m =
		 * (y1-y0)/(x1-x0) Intercept b = -m*x0+y0
		 * 
		 * N.B : x1!=x0
		 * 
		 */

		if (p0.equals(p1))
			throw new IllegalArgumentException("Error: give two distinct points!");

		double m; // slope
		double b; // Intercept

		/*
		 * if line parallel to Y axis , equation will be : x=x0
		 */
		if (Double.compare(p0.getX(), p1.getX()) == 0) {
			m = Double.NaN;
			return new Line(m, p0.getX());
		}

		/*
		 * if line parallel to X axis , equation will be : y=y0
		 */
		if (Double.compare(p0.getY(), p1.getY()) == 0) {

			m = 0;
			b = p0.getY();
			return new Line(m, b);

		}

		m = (p1.getY() - p0.getY()) / (p1.getX() - p0.getX());
		b = -m * p0.getX() + p0.getY();

		return new Line(m, b);
	}

	/**
	 * Return {@code true} if the given point {@code p} is on the {@code line},
	 * {@code false} otherwise.
	 * 
	 * @param line line
	 * @param p    point
	 * @return {@code true} if the given point {@code p} is on the line,
	 *         {@code false} if the point is not on the line.
	 */
	public boolean lineContainsPoint(Line line, Point p) {
		// vaidate parameters

		double slope = line.getSlope();
		double intercept = line.getIntercept();

		if (Double.compare(slope, Double.NaN) == 0) { // line parallel to Y axis
			return Double.compare(intercept, p.getX()) == 0;
		}

		if (Double.compare(slope, 0) == 0) { // line parallel to X axis
			return Double.compare(intercept, p.getY()) == 0;
		}

		// line equation y=slope*x+intercept
		return Double.compare(p.getY(), slope * p.getX() + intercept) == 0;
	}

}
