package geometries;

import java.util.List;

import primitives.*;

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
		Vector v = this.axisRay.getDir();
		Point p0 = this.axisRay.getP0();
		double t = v.dotProduct(point.subtract(p0));
		Point o = p0.add(v.scale(t)); 
		if(Util.isZero(t))
			throw new IllegalArgumentException("point is in front of the head Ray");
		Vector normal = point.subtract(o);
		return normal.normalize();
	}
	
	@Override
	public String toString() {
		return "Tube [axisRay=" + axisRay.toString() + ", radius=" + radius + "]";
	}

	@Override
	public List<Point> findIntsersections(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
