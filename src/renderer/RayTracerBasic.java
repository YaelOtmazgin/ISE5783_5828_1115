package renderer;

import primitives.*;
import scene.Scene;
import lighting.*;

import java.util.List;

import geometries.Intersectable.GeoPoint;

/** A basic class responsible for tracking the ray that inherits from RayTracerBase
 * @author Menuha and Yael */
public class RayTracerBasic extends RayTracerBase {
	/** Maximum recursion depth */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	/** Stopping condition for the recursion */
	private static final double MIN_CALC_COLOR_K = 0.001;
	/** One's triad (1,1,1) */
	private static final Double3 INITIAL_K = Double3.ONE;

	
	/** constructor that operate its parent's constructor
	 * @param scene - shape that build from geometries shapes, color and ambientLight */
	public RayTracerBasic(Scene scene) {
		super(scene);
	}
	
	/** Search the closest GeoPoint in the scene to the ray
	 * @param ray - ray from the camera
	 * @return the closest GeoPoint to the ray */
	private GeoPoint findClosestIntersection(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

	@Override
	public Color traceRay(Ray ray) {
		GeoPoint closestPoint = findClosestIntersection(ray);
		return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
	}

	@Override
	public Color traceRay(List<Ray> rays) {
		if(rays == null)
			return scene.background;
	    Color color = scene.background;
	    for (Ray ray : rays) 
	    	color = color.add(traceRay(ray));     
	    color = color.add(scene.ambientLight.getIntensity());
	    int size = rays.size();
	    return color.reduce(size);
	}
	
	/** Calculates the color of a given point, including the effect of the light sources (helper function for calcColor)
	 * @param gp - geometry shape with a point on it
	 * @param ray - ray from the camera
	 * @return the color of the geometry shape */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
				.add(scene.ambientLight.getIntensity());
	}

	/** Recursive function to calculate the color of a given point, including the effect of the light sources
	 * @param gp - geometry shape with a point on it
	 * @param ray - ray from the camera
	 * @param level - amount of recursive calls
	 * @param kkt - the cumulative attenuation coefficient in recursion
	 * @return the color of the geometry shape */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		if (gp == null)
			return scene.background;
		//Color KaIa = myscene.ambientLight.getIntensity();
		Color color = calcLocalEffects(gp, ray, k);
		return 1 == level ? color : color.add(calcGlobalEffects(gp, ray, level, k));
	}

	/** helps to calculate "calcColor" - calculated light contribution from all light sources
	 * @param gp - geometry shape with point
	 * @param ray - ray from the camera
	 * @param kkt - the current attenuation level
	 * @return calculated light contribution from all light sources */
	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
		Color color = gp.geometry.getEmission(); //the color of the shape
		Vector v = ray.getDir (); //the direction of the ray
		Vector n = gp.geometry.getNormal(gp.point); //the normal vector of gp at point p
		double nv = Util.alignZero(n.dotProduct(v)); //the scalar between the camera direction and the normal (2 vectors)
		if (nv == 0) 
			return color;
		Material material = gp.geometry.getMaterial(); //the shape material of gp
		for (LightSource lightSource : scene.lights) {
			Vector l = lightSource.getL(gp.point); //the vector from a light source to the point of gp
			double nl = Util.alignZero(n.dotProduct(l)); //the scalar between the normal and the vector of light source
			if (nl * nv > 0) {// sign(nl) == sing(nv) - light&camera have the same direction
				Double3 ktr = transparency(gp, lightSource, l, n, nl);
				if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
					Color iL = lightSource.getIntensity(gp.point).scale(ktr); //the intensity color from the light source at a point of gp
					color = color.add(iL.scale(calcDiffusive(material, nl)),
							iL.scale(calcSpecular(material, n, l, nl, v))); //the color with diffusive and specular reflection of each light source
				}
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
		Vector r = l.add(n.scale(-2*nl)); //vector of specular
		double result = -Util.alignZero(v.dotProduct(r)); //specular value to the camera
		if (result <= 0) //sign(v) == sign(r) - specular&camera have the same direction 
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
	
	/** For shading test between point and light source
	 * @param gp    - point in geometry shape
	 * @param light - light source
	 * @param l     - vector from light
	 * @param n     - normal of shape
	 * @param nl    - scalar product of n and l	 
	 * @return
	 *         <li>true - if unshaded
	 *         <li>false - if shaded
	 */
	@SuppressWarnings("unused")
	private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection, n); //get light ray closer to the light
		double d = light.getDistance(lightRay.getP0());
		var intersections = scene.geometries.findGeoIntersections(lightRay, d);
		if (intersections == null)
			return true;
		for (GeoPoint geopoint : intersections) {
			if (geopoint.geometry.getMaterial().kT == Double3.ZERO) //the shape is not transparent
				return false;
		}
		return true;
	}
	
	/**calculates light contribution with consideration for transparency and reflection
	 * @param gp    - point in geometry shape
	 * @param ray          - ray from the camera
	 * @param level        - level of Recursion.
	 * @param k            - the current attenuation level
	 * @return with consideration for transparency and reflection*/
	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = Color.BLACK;
		Vector n = gp.geometry.getNormal(gp.point); //the normal vector of gp at point p
		Vector v = ray.getDir (); //the direction of the ray
		double nv = Util.alignZero(n.dotProduct(v)); //the scalar between the camera direction and the normal (2 vectors)
		
		Double3 kr = gp.geometry.getMaterial().kR, kkr = kr.product(k);
		if (!kkr.lowerThan(MIN_CALC_COLOR_K)) {
			Ray reflectedRay = constructReflectedRay(n, v, nv, gp.point);
			GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
			color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
		}
		
		Double3 kt = gp.geometry.getMaterial().kT, kkt = kt.product(k);
		if (!kkt.lowerThan(MIN_CALC_COLOR_K)) {
			Ray refractedRay = constructRefractedRay(n, v, gp.point);
			GeoPoint refractedPoint = findClosestIntersection(refractedRay);
			color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
		}
		return color;
	}
	
	/** calculates the amount of shadow in the point sometimes we need light shadow and sometimes not 
	 * @param gp    - point in geometry shape
	 * @param light - light source
	 * @param l     - vector from light
	 * @param n     - normal of shape
	 * @param nl    - scalar product of n and l	 
	 * @return amount of shadow */
	private Double3 transparency(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
		Vector lightDirection = l.scale(-1); // from point to light source
		Ray lightRay = new Ray(gp.point, lightDirection, n); //get light ray closer to the light	
		double d = light.getDistance(lightRay.getP0());
		var intersections = scene.geometries.findGeoIntersections(lightRay, d);
		if (intersections == null) 
			return INITIAL_K;
		
		Double3 ktr = INITIAL_K;
		for (GeoPoint geopoint : intersections) {
			ktr = ktr.product(geopoint.geometry.getMaterial().kT);
			if (ktr.lowerThan(MIN_CALC_COLOR_K))
				return Double3.ZERO;
		}
		return ktr;
	}
	
	/** calculate the reflected ray 
	 * @param n  - normal to the point on geometry
	 * @param v  - camera vector
	 * @param nv - equal to n.dotProduct(v)
	 * @param p  - point on geometry shape
	 * @return reflected ray */
	private Ray constructReflectedRay(Vector n, Vector v, double nv, Point p) {
		Vector r = v.add(n.scale(-2 * nv));
		return new Ray(p, r, n);
	}
	
	/** calculate the refracted ray
	 * @param n  - normal to the point on geometry
	 * @param v  - camera vector
	 * @param p  - point on geometry body
	 * @return refracted ray */
	private Ray constructRefractedRay(Vector n, Vector v, Point p) {
		return new Ray(p, v, n);
	}
	
}