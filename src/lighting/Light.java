package lighting;

import primitives.*;

/** The class represents all light sources at an abstract level
 * @author Menucha and Yael */
abstract class Light {
	/** the intensity of the light source */
	private Color intensity;
	
	/** A Ctor who gets the light source intensity.
	 * @param i0 - light source intensity */
	protected Light(Color i0) {
		this.intensity = i0;
	}

	/** Get original light intensity (i0)
	 * @return the intensity - ambient light source */
	public Color getIntensity() {
		return intensity;
	}
}
