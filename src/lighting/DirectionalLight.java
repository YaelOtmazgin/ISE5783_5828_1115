/**
 * 
 */
package lighting;

import primitives.*;

/**
 * @author Dell
 *
 */
public class DirectionalLight extends Light implements LightSource {
	private Vector direction;
	public DirectionalLight(Color iA, Vector direction  ) {
		super (iA, new Double3(1));
		this.direction = direction.normalize();
	}
	@Override
	public Color getIntensity(Point p) {
		return super.getIntensity();
	}
	@Override
	public Vector getL(Point p) {
		return direction.normalize();
	}

	

}
