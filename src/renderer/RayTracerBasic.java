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

	/** Calculates the color of a given point, including the effect of the light sources
	 * @param gp - geometry shape with a point on it
	 * @return the color of the geometry shape */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return scene.ambientLight.getIntensity()
				.add(calcLocalEffects(gp, ray));
	}

	/** helps to calculate "calcColor" - calculated light contribution from all light sources
	 * @param gp - geometry shape with point
	 * @param ray - ray from the camera
	 * @return calculated light contribution from all light sources */
	private Color calcLocalEffects(GeoPoint gp, Ray ray) {
		Color color = gp.geometry.getEmission(); //the color of the shape
		Vector v = ray.getDir (); //the direction of the ray
		Vector n = gp.geometry.getNormal(gp.point); //the normal vector of gp at point p
		double nv = Util.alignZero(n.dotProduct(v)); //the scalar between the direction and the normal (2 vectors)
		if (nv == 0) 
			return color;
		Material material = gp.geometry.getMaterial(); //the material shape of gp
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point); //the vector from a light source to the point of gp
			double nl = Util.alignZero(n.dotProduct(l)); //the scalar between the normal and the vector of light source
			if (nl * nv > 0) { // sign(nl) == sing(nv)
				Color iL = lightSource.getIntensity(gp.point); //the intensity color from the light source at a point of gp
				color = color.add(iL.scale(calcDiffusive(material, nl)),
						iL.scale(calcSpecular(material, n, l, nl, v))); //the color with diffusive and specular reflection of each light source
				}
		}
		return color;
	}

	/** calculate the specular light according to Phong's model
	 * @param material - material of the geometry
	 * @param n - normal to the point
	 * @param l - vector from light source
	 * @param nl - scalar product of n and l
	 * @param v - camera vector, the direction of the ray
	 * @return the specular light */
	private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
		Vector r = l.add(n.scale(-2*nl));
		double result = -Util.alignZero(v.dotProduct(r));
		if (result <= 0)
			return Double3.ZERO;
		return material.kS.scale(Math.pow(result, material.nShininess));
	}

	/** calculate the diffusive light according to Phong's model
	 * @param material - material of the geometry
	 * @param nl - scalar product of n and l
	 * @return the diffusive light */
	private Double3 calcDiffusive(Material material, double nl) {
		return material.kD.scale(Math.abs(nl));
	}
}