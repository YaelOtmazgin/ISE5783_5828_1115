package geometries;

import primitives.Point;
import primitives.Ray;
import static primitives.Util.*;
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
	public Vector getNormal(Point point) { 
		Vector dir = axisRay.getDir();
		Point p0 = axisRay.getP0();
		try {
			var t = dir.dotProduct(point.subtract(p0));
			if (isZero(t) || isZero(t - height))
				return dir;
			var o = p0.add(dir.scale(t));
			return point.subtract(o).normalize();
		} catch (Exception e) {
			return dir;
		}
	}

	@Override
	public String toString() {
		return "Cylinder [" + super.toString() + ", height=" + height + "]";
	}
}
