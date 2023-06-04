package geometries;

import primitives.Vector;

/** Implements the Geometry interface
 * @author Menuha and Yael */
public abstract class RadialGeometry extends Geometry {
	/** radius */
	protected final double radius;
	
	/** RadialGeometry constructor based on radius
	 * @param radius value */
	public RadialGeometry(double radius) {
		this.radius = radius;
	}
	
	/** @return the Geometry's radius */
	public double getRadius() { 
		return radius; 
	}
}