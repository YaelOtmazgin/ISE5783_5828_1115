package geometries;

import primitives.Point;
import primitives.Vector;

/** The plane class represents a two-dimensional plane in 3D Cartesian coordinate system */
public class Plane implements Geometry {
	private final Point q0;
	private final Vector normal;
	
	/** 
	 * @param p1 Point
	 * @param p2 Point
	 * @param p3 Point 
	 * @throws IllegalArgumentException 
	 * <li>When one of the points is similar to the other <li>When they are on the same straight line
	 */
	public Plane(Point p1, Point p2, Point p3) {
		if(p1.equals(p3)||p1.equals(p2)||p2.equals(p3)){
			throw new IllegalArgumentException("There are at least two merging points, Check them again!");
		}
		var v1=p2.subtract(p1);
		var v2=p3.subtract(p1);
		normal=v1.crossProduct(v2).normalize();
		this.q0 = p1;
		
	}
	
	
	
	/** Plane constructor receiving a Point and a Vector
	 * @param p Point
	 * @param normal Vector which does not have to be normalized */
	public Plane(Point q0, Vector normal) {
		this.q0 = q0;
		this.normal = normal.normalize();
	}
	
	/** @return the plane's point */
	public Point getQ0() { return q0; }
	
	/** @return the plane's normal */
	public Vector getNormal() { return normal; }

	@Override
	public Vector getNormal(Point point) { return normal; }
	
	@Override
	public String toString() {
		return "Plane [q0=" + q0.toString() + ", normal=" + normal.toString() + "]";
	}
}
