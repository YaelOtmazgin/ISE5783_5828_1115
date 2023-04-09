package geometries;

import primitives.Point;
import primitives.Vector;

/** Triangle class represents a two-dimensional polygon with 3 points 
 * in 3D Cartesian coordinate system */
public class Triangle extends Polygon {
	/** Triangle constructor based on 3 points. The points must be ordered by edge path.
	 * @param p1 Point
	 * @param p2 Point
	 * @param p3 Point */
	public Triangle(Point p1, Point p2, Point p3) {
		super(p1, p2, p3);
	}
	
	@Override
	public Vector getNormal(Point point) {
		return null;
	}
	
	@Override
	public String toString() {
		return "Triangle [" + plane.toString() +
			   "point0= " + vertices.get(0).toString() +
			   "point1= " + vertices.get(1).toString() +
			   "point2= " + vertices.get(2).toString() +"]";
	}
}

