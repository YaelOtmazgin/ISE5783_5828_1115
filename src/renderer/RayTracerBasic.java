package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

/** A basic class responsible for tracking the ray that inherits from RayTracerBase
 * @author Menuha and Yael */
public class RayTracerBasic extends RayTracerBase {

	/** constructor that operate its parent's constructor
	 * @param scene - shape that build from geometries shapes, color and ambientLight */
	public RayTracerBasic(Scene scene) {
		super(scene);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findIntersections(ray);
		if (intersections == null)
			return scene.background;
		Point closestPoint = ray.findClosestPoint(intersections);
		return calcColor(closestPoint);
	}

	/** Calculates the color of a given point 
	 * @param point - point on image
	 * @return the color in this point */
	private Color calcColor(Point point) {
		return scene.ambientLight.getIntensity();
	}
}