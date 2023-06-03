package geometries;

import java.util.List;
import primitives.*;

/** The interface implements a function for intersection between a ray and shape.
 * @author Menuha and Yael */
public abstract class Intersectable {
	/** represent point and the geometry shape */
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
	 * @return list of intersections GeoPoints */
	public List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray);
	}
	
	/** Function that helps finding intersections GeoPoints
	 * @param ray - The ray that crosses the body
	 * @return list of intersections GeoPoints (geometry/point pairs) */
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
	
	/** Function for finding intersection points.
	 * @param ray.
	 * @return list of intersections points. */
	public List<Point> findIntersections(Ray ray) {
		 var geoList = findGeoIntersections(ray);
		 return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}

}
