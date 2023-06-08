package geometries;

/*import primitives.Vector;*/

/** A parent class for geometry shapes with a radius
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