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
	public Color background;
	
	/** strength of the color */
	public AmbientLight ambientLight;
	
	/** list of shapes that create our shape */
	public Geometries geometries;
	
	public List<LightSource> lights;

	/** ctor: build empty shape with color black with the name given
	 * @param name - name of the scene (the "empty" shape) */
	public Scene(String name) {
		this.name = name;
		background = Color.BLACK;
		ambientLight = AmbientLight.NONE;
		geometries = new Geometries();
		lights = new LinkedList<LightSource>();
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
	/**
	 * ------------- setter -----------------
	 * @param lights - list of sources light
	 * @return itself scene
	 */
	public Scene setLights(List<LightSource> lights) {
		this.lights = lights;
		return this;
		
	}
}