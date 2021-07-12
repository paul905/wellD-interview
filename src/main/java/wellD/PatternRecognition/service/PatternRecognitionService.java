package wellD.PatternRecognition.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wellD.PatternRecognition.model.Geometry;
import wellD.PatternRecognition.model.Line;
import wellD.PatternRecognition.model.Point;

/**
 * 
 * @author Paolo Abatemarco
 *
 */
@Service
public class PatternRecognitionService {

	private Geometry geo;
	
	private List<Set<Point>> lineSegments = new ArrayList<>();
	private LinkedList<Line> visitedLines = new LinkedList<>();
	private Set<Point> space;
	
	@Autowired
	public PatternRecognitionService(Geometry geo) {
		this.geo = geo;
		space = Collections.synchronizedSet(new HashSet<>());
	}

	public void setGeo(Geometry geo) {
		this.geo = geo;
	}

	public void addPoint(Point p) {
		if (space.stream().anyMatch(point -> point.equals(p)))
			throw new RuntimeException(p + " already exists");
		space.add(p);
	}

	public Set<Point> getAllPoints() {
		return space;
	}

	public void removeAllPoints() {
		if (!space.isEmpty())
			space.clear();
	}

	public List<Set<Point>> getLineSegments(int n) {
		
		// because this class is managed as a Singleton
		this.lineSegments.clear();
		this.visitedLines.clear();

		if (n < 2)
			throw new IllegalArgumentException("N must be >= 2");

		// for every point in the space
		for (Point p : space) {

			
			Set<Point> line = getLine(p, n);

			if (!line.isEmpty())
				lineSegments.add(line);
		}
		return lineSegments;

	}

	private Set<Point> getLine(Point p0, int n) {

		Iterator<Point> it = space.iterator();

		while (it.hasNext()) {

			Point p1;
			do { // if we are on the same point , move to the next point
				p1 = it.next();
			} while (it.hasNext() && p1.equals(p0));

			if (!it.hasNext())
				continue;

			// get the line between the two points
			Line equation = null;
			try {

				equation = geo.lineBetweenTwoPoints(p0, p1);

			} catch (IllegalArgumentException e) {
				System.out.println(e);
			}

			/*
			 * Now that we have the equation of the line between p0 and p1, we're going to
			 * find all the points on the line. If those points are more than N we're going
			 * to add the line as part of result.
			 */

			if (!lineAlreadyVisited(equation)) {

				visitedLines.add(equation);

				// temporary list of points contained in the line
				Set<Point> temp = new HashSet<>();

				temp.add(p0);
				temp.add(p1);

				// counts how many points are on the line
				int counter = 2;

				Iterator<Point> it_new = space.iterator();

				while (it_new.hasNext()) {

					Point p = it_new.next();

					if (p.equals(p0) || p.equals(p1))
						continue;

					// if line contains point p
					if (geo.lineContainsPoint(equation, p)) {
						temp.add(p);
						counter++;
					}
				} // while

				if (counter >= n) {
					return temp;
				}

			} // if (!visitedLines.contains(equation))

		}

		// if the line segment doesn't contain at least N points, return an empty set
		return new HashSet<>();

	}

	private boolean lineAlreadyVisited(Line line) {
		for (Line l : visitedLines) {
			if (l.equals(line))
				return true;
		}
		return false;
	}

}
