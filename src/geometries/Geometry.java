package geometries;

import primitives.Point;
import primitives.Vector;

/** Interface for representing any geometric shape */
public interface Geometry {
	/** Find the normal (vertical) vector to the geometric shape at point p.
	 * @param p is a one point-type parameter [across the geometric shape]
	 * @return the normal vector on the given point. */
	public Vector getNormal(Point point);
}