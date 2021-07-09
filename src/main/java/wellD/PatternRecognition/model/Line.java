package wellD.PatternRecognition.model;

/**
 * The class Line represents a straight line in two-dimensional space, is formed
 * by slope and intercept.
 * 
 * @author Paolo Abatemarco
 *
 */
public class Line {

	private double slope;
	private double intercept;

	public Line(double slope, double intercept) {
		this.slope = slope;
		this.intercept = intercept;
	}

	public double getSlope() {
		return slope;
	}

	public double getIntercept() {
		return intercept;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Line))
			return false;
		if (obj == this)
			return true;
		Line line = (Line) obj;
		return (Double.compare(this.getSlope(), line.getSlope()) == 0)
				&& (Double.compare(this.getIntercept(), line.getIntercept()) == 0);
	}

	@Override
	public String toString() {
		return "Line [slope=" + slope + ", intercept=" + intercept + "]";
	}
	
	

}
