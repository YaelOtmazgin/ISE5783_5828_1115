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
	
	/**Checks the color of the pixel with the help of individual rays and averages between
     * them and only if necessary continues to send beams of rays in recursion
     * @param centerP center pixl
     * @param Width Length
     * @param Height width
     * @param minWidth min Width
     * @param minHeight min Height
     * @param cameraLoc Camera location
     * @param Vright Vector right
     * @param Vup vector up
     * @param prePoints pre Points
     * @return Pixel color*/
    public abstract Color AdaptiveSuperSamplingHelper(Point centerP, double Width, double Height, double minWidth, double minHeight, Point cameraLoc, Vector Vright, Vector Vup, List<Point> prePoints);
}