package scene;

import lighting.AmbientLight;
import geometries.Geometries;
import primitives.Color;

/** Scene that build from geometries shapes, color and ambientLight(strength of the color)
 * @author Menuha and Yael */
public class Scene {
	/** name of the scene (the shape) */
	public String name;
	
	/** the background color */
	public Color background;
	
	/** strength of the color */
	public AmbientLight ambientLight;
	
	/** list of shapes that create our shape */
	public Geometries geometries;
	
	

	/** ctor: build empty shape with color black with the name given
	 * @param name - name of the scene (the "empty" shape) */
	public Scene(String name) {
		this.name = name;
		background = Color.BLACK;
		ambientLight = AmbientLight.NONE;
		geometries = new Geometries();
	}

	/** ------------- setter -----------------
	 * @param background the background to set
	 * @return the scene itself */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/** ------------- setter -----------------
	 * @param ambientLight the ambientLight to set
	 * @return the scene itself */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/** ------------- setter -----------------
	 * @param geometries the geometries to set
	 * @return the scene itself */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
}