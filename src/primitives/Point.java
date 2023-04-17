package primitives;

/** Point class represents a point in space - a fundamental object in geometry - a point with 3 coordinates
 * @author MENUHA */
public class Point {
	/** Three numbers */
	final Double3 xyz;
	
	/** Constructor to initialize Point based object with its three number values
	 * @param d1 first number value
	 * @param d2 second number value
	 * @param d3 third number value */
	public Point(double d1, double d2, double d3) {
	    xyz = new Double3(d1,d2,d3);
	}
	   
	/** Constructor to initialize Point based object with Double3 object
	 * @param xyz contains 3 numbers */
	Point(Double3 xyz) {
		this.xyz = xyz; // new Double3(xyz.d1, xyz.d2, xyz.d3);
	}
	
	@Override
	public boolean equals(Object obj) {
	if (this == obj) return true;
	return (obj instanceof Point other) 
		    && xyz.equals(other.xyz);
	}
	
	@Override
	public String toString() { return "" + xyz; }
	
	/** Vector subtraction 
	 * @param p a second point
	 * @return a vector from the second point to the point on which the operation is performed */
	public Vector subtract(Point p) {
		return new Vector(xyz.subtract(p.xyz));
	}
	
	/** Adding a vector to a point
	 * @param v a vector
	 * @return a new point */
	public Point add(Vector v) {
		return new Point(xyz.add(v.xyz));
	}
	
	/** Calculate the distance between 2 points squared
	 * @param p a second point
	 * @return the distance between 2 points squared */
	public double distanceSquared(Point p) {
		return (xyz.d1 - p.xyz.d1) * (xyz.d1 - p.xyz.d1) +
			   (xyz.d2 - p.xyz.d2) * (xyz.d2 - p.xyz.d2) +
			   (xyz.d3 - p.xyz.d3) * (xyz.d3 - p.xyz.d3);
	}
	
	/** Calculate the distance between 2 points
	 * @param p a second point
	 * @return distance between 2 points */
	public double distance(Point p) {
		return Math.sqrt(this.distanceSquared(p));
	}	
}
