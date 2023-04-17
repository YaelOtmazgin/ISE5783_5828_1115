package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/** Cylinder class represents a finite cylinder in 3D Cartesian coordinate system */
public class Cylinder extends Tube {
	// The cylinder's height
	private final double height;
	
	/** Cylinder constructor based on axis ray, radius and height
	 * @param axisRay Ray
	 * @param radius value
	 * @param height value */
	public Cylinder(Ray axisRay, double radius, double height) {
		super(axisRay, radius);
		this.height = height;
	}

	/** @return the cylinder's height */
	public double getHeight() {
		return height;
	}
	
	@Override
	public Vector getNormal(Point point) { return null; }

	@Override
	public String toString() {
		return "Cylinder [" + super.toString() + ", height=" + height + "]";
	}
}
