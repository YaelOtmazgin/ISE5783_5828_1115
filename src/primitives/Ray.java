package primitives;

import java.util.List;

/** Semi-Straight - All the points on the line that are on one side of the given point on the line 
 * called the beginning / head of the ray
 * @author Menuha and Yael */
public class Ray {
	// Head of the ray
	private final Point p0;
	// Direction
	private final Vector dir;
	
	/** Constructor that creates a Ray from a normalized vector point.
	 * @param p0 - the head of the Ray
	 * @param dir - direction Vector which does not have to be normalized */
	public Ray(Point p0, Vector dir) {
		this.p0 = p0;
		this.dir = dir.normalize();
	}
	
	/** @return the head of the Ray */
	public Point getP0() {
		return p0;
	}

	/** @return the direction of the Ray */
	public Vector getDir() {
		return dir;
	}
	
	/** a function that calculate apoint on the ray
	 * @param t scaler
	 * @return vector */
	public Point getPoint(double t) {
		return p0.add(dir.scale(t));
	}
	
	/** search the closest point to the ray in list of points
	 * @param intersections - list of points we want to scan
	 * @return the closest point to the ray */
	public Point findClosestPoint(List<Point> intersections) {
		if(intersections == null || intersections.size() == 0)
			return null;
		var minPoint = intersections.get(0);
		for(var item: intersections) {
		     if(item.distance(p0) < minPoint.distance(p0))
		    	 minPoint = item;
		}
		return minPoint;
	}
	
	@Override
	public boolean equals(Object obj) {
	if (this == obj) return true;
	return (obj instanceof Ray other) 
			&& this.p0.equals(other.p0) 
			&& this.dir.equals(other.dir);
	}
	
	@Override
	public String toString() { return "Ray [" + p0.toString() + "," + dir.toString() + "]"; }
}


