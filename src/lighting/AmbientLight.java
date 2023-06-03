package lighting;

import primitives.Color;
import primitives.Double3;

/** An ambient light source represents a fixed intensity and fixed 
 * color light source that affects all objects in the scene equally.
 * @author Menuha and Yael */
public class AmbientLight extends Light{

	/** AmbientLight(BLACK, ZERO) */
	public static final AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);

	/** A Ctor who gets the color and power of light call the Ctor of Light
	 * @param Ia - Fill the light intensity according to RGB
	 * @param Ka - Coefficient of attenuation of filler light */
	public AmbientLight(Color Ia, Double3 Ka) {
		super(Ia.scale(Ka));
	}
	
	/** A Ctor who gets the color and power of light and call the Ctor of Light
	 * @param Ia - Fill the light intensity according to RGB
	 * @param Ka - Coefficient of attenuation of filler light */
	public AmbientLight(Color Ia, double Ka) {
		super(Ia.scale(Ka));
	}
}
