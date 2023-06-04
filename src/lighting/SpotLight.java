/**
 * 
 */
package lighting;

import primitives.*;
/**The class represents a directional 
 *point light source such as an adjustable lamp
 * @author Dell
 *
 */
public class SpotLight extends PointLight{
	private Vector direction;
	
	/**A Ctor who gets the color, power of light,position and direction vector.
	 * the light source cannot be on a body surface
	 * @param iA - Fill the light intensity according to RGB
	 * @param point - position of source light
	 * @param direction - direction of light
	 * @param kC - constant coefficient
	 * @param kL - Linear coefficient
	 * @param kQ - Quadratic coefficient 
	 * @param narrow - The width of the light extending from the light */
	public SpotLight(Color iA, Point position, double kC, double kL, double kQ, Vector direction) {
		super(iA, position, kC, kL, kQ);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		var result = direction.dotProduct(getL(p));
		if(result <= 0)
			return Color.BLACK;
		return super.getIntensity(p).scale(result);
	}
	@Override
	public Vector getL(Point p) {
		return super.getL(p);
	}
}
