package geometries;

import primitives.Point;
import primitives.Vector;

/** Sphere class represents sphere in 3D Cartesian coordinate system */
public class Sphere extends RadialGeometry{
	// the sphere's center point
	private Point center;
	
	/** Sphere constructor based on center point and radius
	 * @param center Point
	 * @param radius value */
	public Sphere(Point center, double radius) {
		super(radius);
		this.center = center;
	}
	
	/** @return the sphere's center point */
	public Point getCenter() { return center; }
	
	@Override
	public Vector getNormal(Point point) { return null; }

	@Override
	public String toString() { return "Sphere [center=" + center.toString() + ", radius=" + radius + "]"; }

	}
