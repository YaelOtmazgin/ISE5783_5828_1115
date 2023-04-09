package geometries;

import primitives.Point;
import primitives.Vector;

/** The plane class represents a two-dimensional plane in 3D Cartesian coordinate system */
public class Plane implements Geometry {
	private Point q0;
	private Vector normal;
	
	/** Plane constructor which calculate the normal to the triangle
	 * @param p1 Point
	 * @param p2 Point
	 * @param p3 Point */
	public Plane(Point p1, Point p2, Point p3) {
		q0 = p1;
		normal = null;
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
