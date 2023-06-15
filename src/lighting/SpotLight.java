package lighting;

import primitives.*;

/** The class represents a directional point light source such as an adjustable lamp
 * @author Menuha and Yael */
public class SpotLight extends PointLight {
	
	private Vector direction;
	
	/** A Ctor who gets the light source intensity, light source position and direction vector.
	 * the light source cannot be on a body surface
	 * @param iL - source light intensity
	 * @param point - position of source light
	 * @param direction - direction of light */
	public SpotLight(Color iL, Point position, Vector direction) {
		super(iL, position);
		this.direction = direction.normalize();
	}

	@Override
	public Color getIntensity(Point p) {
		var result = direction.dotProduct(getL(p));
		if(result <= 0)
			return Color.BLACK;
		return super.getIntensity(p).scale(result);
	}
	
}
