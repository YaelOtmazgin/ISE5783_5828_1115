package lighting;

import primitives.*;

/** The interface represents common operations on types of light sources
 * @author Menucha and Yael */
public interface LightSource {
	
	/** Get light intensity at a point (IL)
	 * @param p - point on geometry shape
	 * @return color of pixel in this point */
	public Color getIntensity(Point p);
	
	/** The method calculates a unit vector from the light source to a point in a geometric shape
	 * not support to zero vector!
	 * @param p - point on geometry shape
	 * @return the direction of the light */
	public Vector getL(Point p);
	
	/** Get distance between the light and a point
	 * @param p - calculate the distance from this point
	 * @return distance between the light and the point */
	public double getDistance(Point p);
}
