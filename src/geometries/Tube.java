package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry {
	/** The tube's ray */
	protected final Ray axisRay;
	
	/** Tube constructor based on axis ray and radius
	 * @param axisRay Ray
	 * @param radius value */
	public Tube(Ray axisRay, double radius) {
		super(radius);
		this.axisRay = axisRay;
	}
	
	/** @return the tube's ray */
	public Ray getAxisRay() {
		return axisRay;
	}
	
	@Override
	public Vector getNormal(Point point) {
		return null;
	}
	
	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay.toString() + ", radius=" + radius + "]";
	}
}
