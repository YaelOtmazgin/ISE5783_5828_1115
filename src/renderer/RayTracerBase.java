package renderer;

import java.util.List;

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
	
	/** function that traces ray and return color of the closest point
     * @param ray ray to trace
	 * @return the color of pixel in current tracing ray */
	public abstract Color traceRay(Ray ray); 
	
	/** function that traces rays and return average color of pixel
	 * @param rays The list of rays that was constructed in a beam of rays
	 * @return The color of the starting-point of the given main-ray */
	public abstract Color traceRays(List<Ray> rays);
}