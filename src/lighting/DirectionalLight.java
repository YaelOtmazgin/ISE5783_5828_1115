package lighting;

import primitives.*;

/** The class represents a directional light source such as sun or moon
 * @author Menuha and Yael */
public class DirectionalLight extends Light implements LightSource {
	
	private Vector direction;
	
	/** A Ctor who gets the light source intensity and direction vector.
	 * @param iL - light source intensity
	 * @param direction - direction of light */
	public DirectionalLight(Color iL, Vector direction) {
		super(iL);
		this.direction = direction.normalize();
	}
	
	@Override
	public Color getIntensity(Point p) {
		return super.getIntensity();
	}
	
	@Override
	public Vector getL(Point p) {
		return direction;
	}

	@Override
	public double getDistance(Point p) {
		return Double.POSITIVE_INFINITY;
	}

}
