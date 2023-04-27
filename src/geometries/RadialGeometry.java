package geometries;

import primitives.Vector;

/** Implements the Geometry interface
 * @author MENUHA */
public abstract class RadialGeometry implements Geometry {
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
