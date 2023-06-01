package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

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
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return scene.background;
		GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
		return calcColor(closestPoint, ray);
	}

	/** Calculates the color of a given point 
	 * @param point - point on image
	 * @return the color in this point */
	private Color calcColor(GeoPoint geo, Ray ray) {
		return scene.ambientLight.getIntensity();
	}
}