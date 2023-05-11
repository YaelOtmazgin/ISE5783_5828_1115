package renderer;

import primitives.*;
import scene.Scene;

/** An abstract base class responsible for tracking the ray
 * @author Menuha and Yael */
public abstract class RayTracerBase {
	/** scene value */
	protected Scene scene;

	/** Ctor - get scene and set it
	 * @param scene - shape that build from geometries shapes, color and ambientLight */
	public RayTracerBase(Scene scene) {
		this.scene = scene;
	}
	
	/** returns color of pixel in current tracing ray
	 * @param ray - ray on tracing
	 * @return the color of pixel in current tracing ray */
	public abstract Color traceRay(Ray ray); 	
}