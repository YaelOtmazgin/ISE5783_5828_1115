package unittests;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/** test the improvement of mini project 1 - Anti-Aliasing
 * @author Menuha and Yael */
class MiniProject1 {

	private Scene scene = new Scene("Test scene");

	/** produce a picture without Anti-Aliasing */
	@Test
	public void noAntiAliasing() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
		scene.geometries.add(
				new Sphere(new Point(0, 0, 0), 80) //
					.setEmission(new Color(java.awt.Color.yellow)) //
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		ImageWriter imageWriter = new ImageWriter("before", 600, 600);
		camera.setImageWriter(imageWriter) //
			.setRayTracer(new RayTracerBasic(scene))
			.renderImage()
			.writeToImage();
	}

	/** produce a picture with Anti-Aliasing */
	@Test
	public void antiAliasing() {
		int numOfRays = 100;
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) 
				.setVPSize(200, 200).setVPDistance(1000);
		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
		scene.geometries.add(
				new Sphere(new Point(0, 0, 0), 80) //
					.setEmission(new Color(java.awt.Color.yellow)) //
					.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));
		ImageWriter imageWriter = new ImageWriter("after", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(scene)).setNumOfRays(numOfRays)
				.renderImage()
				.writeToImage();
	}
}
