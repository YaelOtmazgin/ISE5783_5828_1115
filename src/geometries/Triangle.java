package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

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
	public String toString() {
		return "Triangle [" + plane.toString() +
			   "point0= " + vertices.get(0).toString() +
			   "point1= " + vertices.get(1).toString() +
			   "point2= " + vertices.get(2).toString() +"]";
	}
	
	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> rayPoints = plane.findIntersections(ray);
		if (rayPoints == null)
			return null;
		//check if the point in, out or on the triangle:
		Vector v1 = vertices.get(0).subtract(ray.getP0());
		Vector v2 = vertices.get(1).subtract(ray.getP0());
		Vector v3 = vertices.get(2).subtract(ray.getP0());
		
		Vector n1 = v1.crossProduct(v2).normalize();
		Vector n2 = v2.crossProduct(v3).normalize();
		Vector n3 = v3.crossProduct(v1).normalize();
	
		//The point is inside if all  have the same sign (+/-)		
		if (alignZero(n1.dotProduct(ray.getDir())) > 0 
				&& alignZero(n2.dotProduct(ray.getDir())) > 0 
				&& alignZero(n3.dotProduct(ray.getDir())) > 0)
			return rayPoints;
		else if (alignZero(n1.dotProduct(ray.getDir())) < 0 
				&& alignZero(n2.dotProduct(ray.getDir())) < 0 
				&& alignZero(n3.dotProduct(ray.getDir())) < 0)
			return rayPoints;
		if (isZero(n1.dotProduct(ray.getDir())) 
				|| isZero(n2.dotProduct(ray.getDir())) 
				|| isZero(n3.dotProduct(ray.getDir())))
			return null; //there is no instruction points
		return null;
	}	   
}

