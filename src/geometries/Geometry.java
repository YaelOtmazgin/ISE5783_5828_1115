package geometries;

import primitives.*;

/** Interface for representing any geometric shape 
 * @author MENUHA and Yael */
public interface Geometry extends Intersectable {
	/** Find the normal (vertical) vector to the geometric shape at point p.
	 * @param p is a one point-type parameter [across the geometric shape]
	 * @return the normal vector on the given point. */
	public Vector getNormal(Point point);
}