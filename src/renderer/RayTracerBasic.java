package renderer;

import primitives.*;
import scene.Scene;
import lighting.*;
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
	private Color calcColor(GeoPoint intersection, Ray ray) {
		return scene.ambientLight.getIntensity()
				.add(calcLocalEffects(intersection, ray));
	}

	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Color color = gp.geometry.getEmmission();
		Vector v = ray.getDir (); Vector n = gp.geometry.getNormal(gp.point);
		double nv = Util.alignZero(n.dotProduct(v)); 
		if (nv == 0) 
			return color;
		Material material = gp.geometry.getMaterial();
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point);
			double nl = Util.alignZero(n.dotProduct(l));
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color iL = lightSource.getIntensity(gp.point);
				color = color.add(iL.scale(calcDiffusive(material, nl)),
						iL.scale(calcSpecular(material, n, l, nl, v)));
				}
		}
		return color;
	}

	private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
		// TODO Auto-generated method stub
		return null;
	}

	private Double3 calcDiffusive(Material material, double nl) {
		// TODO Auto-generated method stub
		return null;
	}
}