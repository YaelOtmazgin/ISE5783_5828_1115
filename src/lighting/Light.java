/**
 * 
 */
package lighting;

import primitives.*;

/**The class represents all light sources at an abstract level
 * @author Menucha and Yael */
public abstract class Light {
	private Color intensity;
	
	/*** A Ctor who gets the color and power of light by AmbientLight.*/
	protected Light(Color intensity) {
		this.intensity = intensity;
	}

	/**
	 * get the ambient light source
	 * @return the intensity - ambient light source*/
	public Color getIntensity() {
		return intensity;
	}
}
