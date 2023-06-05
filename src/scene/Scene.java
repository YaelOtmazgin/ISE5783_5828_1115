package scene;

import lighting.*;

import java.util.List;
import java.util.LinkedList; 

import geometries.Geometries;
import primitives.Color;

/** Scene that build from geometries shapes, color and ambientLight(strength of the color)
 * @author Menuha and Yael */
public class Scene {
	/** name of the scene (the shape) */
	public String name;
	
	/** the background color */
	public Color background = Color.BLACK;
	
	/** strength of the color */
	public AmbientLight ambientLight = AmbientLight.NONE;
	
	/** list of shapes that create our scene */
	public Geometries geometries = new Geometries();
	
	/** list of source lights */
	public List<LightSource> lights = new LinkedList<LightSource>();

	/** ctor: build empty shape with color black with the name given
	 * @param name - name of the scene (the "empty" shape) */
	public Scene(String name) {
		this.name = name;	
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
	/** ------------- setter -----------------
	 * @param lights - list of sources light
	 * @return the scene itself */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
		
	}
}