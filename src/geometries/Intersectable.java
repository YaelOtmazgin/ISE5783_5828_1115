package geometries;

import java.util.List;
import primitives.*;

/** The class is responsible for intersections between a ray and shape.
 * @author Menuha and Yael */
public abstract class Intersectable {
	/** represent geometry shape and a point on it */
	public static class GeoPoint {
		/** value of the geometry shape */ 
		public Geometry geometry;
		/** value of the point on the geometry shape */ 
		public Point point;
		
		/** Ctor - build the GeoPoint
		 * @param geo - the geometry shape
		 * @param point - the point on the geometry shape */
		public GeoPoint(Geometry geo, Point point) {
			geometry = geo;
			this.point = point;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			return (obj instanceof GeoPoint other) 
					&& geometry.equals(other.geometry)
					&& point.equals(other.point);
		}
		
		@Override
		public String toString() {
			return "GeoPoint [geometry=" + geometry + ", point=" + point + "]";
		}
	}
	
	/** Function for finding intersections GeoPoints
	 * @param ray - The ray that crosses the shape
	 * @return list of intersections GeoPoints (geometry/point pairs) */
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
	}
	
	/**Function for finding intersections between GeoPoints and ray with length
	 * @param ray - The ray that crosses the shape
	 * @param maxDistance - the length of the ray
	 * @return list of intersections between GeoPoints and ray with length */
	public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
		 return findGeoIntersectionsHelper(ray, maxDistance);
	}
	
	/** Function that helps finding intersections between GeoPoints and ray with length
	 * @param ray - The ray that crosses the shape
	 * @param maxDistance - the length of the ray
	 * @return list of intersections between GeoPoints and ray with length */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);
	
	/** Function for finding intersection points.
	 * @param ray - The ray that crosses the shape
	 * @return list of intersections points. */
	public List<Point> findIntersections(Ray ray) {
		 var geoList = findGeoIntersections(ray);
		 return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

}