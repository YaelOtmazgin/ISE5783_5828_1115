package renderer;

import java.util.MissingResourceException;

import java.util.LinkedList;
import java.util.List;

import primitives.*;
/** Shoot rays from the center of projection through the view plane pixels for
 * "see" objects in the this 3D world 
 * @author Menuha and Yael */
public class Camera {
	private Point p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	private int antiAliasingFactor = 1;
	//boolean parameter if to operate the Adaptive Super Sampling acceleration
    private boolean isAdaptiveSuperSampling= false;

    //for the Adaptive Super Sampling acceleration decide the maximum depth of the recursion
    private int maximumAdaptiveDepth =4;

    private double printInterval = 1;
	
	/** Pixel manager for supporting:
	 * <ul>
	 * <li>multi-threading</li>
	 * <li>debug print of progress percentage in Console window/tab</li>
	 * <ul>*/
	 private PixelManager pixelManager;
	 
	//number of threads we are using in the operation. Initialize to 0
	    private int threadCount=0;

	/** Location of the camera lens
	 * @return the p0 a location of the camera lens */
	public Point getP0() {
		return p0;
	}

	/** The Vector starting at P0 and pointing upwards
	 *  @return the vUp */
	public Vector getvUp() {
		return vUp;
	}

	/** The Vector starting at P0 and pointing forward 
	 * @return the vTo */
	public Vector getvTo() {
		return vTo;
	}

	/** The Vector starting at P0 and pointing to the right
	 *  @return the vRight */
	public Vector getvRight() {
		return vRight;
	}
	
	/** Setter for number of rays
	 * @param antiAliasingFactor int value
	 * @return the object - this, for builder pattern */
	public Camera setAntiAliasingFactor(int antiAliasingFactor){
		if(antiAliasingFactor == 0)
			this.antiAliasingFactor = 1;
		else
			this.antiAliasingFactor = antiAliasingFactor;
		return this;
	}
	
	/**
     * Sets the boolean of the adaptive super sampaling  .
     *
     * @param  adaptiveSuperSampling amont of race
     * @return the camera instance with the updated boolean of adaptiveSuperSampeling
     */
    public Camera setAdaptiveSuperSampling(boolean adaptiveSuperSampling) {
        isAdaptiveSuperSampling = adaptiveSuperSampling;

        if (adaptiveSuperSampling==true) {
            //initialize the amoutRays to 1 that will help to use just one feature
        	antiAliasingFactor = 1;
        }

        return this;
    }

    /**
     * Sets the value of the maximumAdaptive.
     *
     * @param  maximumAdaptiveDepth amonut of the maximum adaptive super sampling
     * @return the camera instance with the updated boolean of adaptiveSuperSampeling
     */
    public Camera setMaximumAdaptiveDepth(int maximumAdaptiveDepth) {
        this.maximumAdaptiveDepth = maximumAdaptiveDepth;
        return this;
    }

    /**
     * Sets the value of the threadCount.
     *
     * @param  threadCount amonut for the multiThreading
     * @return the camera instance with the updated count of the multi-threads
     */
    public Camera setMultithreading(int threadCount) {
        this.threadCount = threadCount;
        return this;
    }

    /**
     * Sets the value print interval help to debug information.
     *
     * @param k The value to set the printInterval.
     * @return The updated Camera .
     */
    public Camera setDebugPrint(double k)
    {
        this.printInterval = k;
        return this;
    }
	
	
	/** A camera constructor that receives two vectors in the direction of the
	 * camera(up,to) and point3d for the camera lens 
	 * @param p0  - location of the camera lens
	 * @param vTo - starting at P0 and pointing forward
	 * @param vUp - starting at P0 and pointing upwards
	 * @throws IllegalArgumentException if the vectors are not vertical*/
	public Camera(Point p0, Vector vTo, Vector vUp) {
		if (!Util.isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("Vectors are not vertical");
		this.p0 = p0;
		this.vTo = vTo.normalize();
		this.vUp = vUp.normalize();
		this.vRight = vTo.crossProduct(vUp).normalize();
	}

	/** setter for size of view plane
	 * @param width  - a width of plane view
	 * @param height - a height of plane view
	 * @return the camera itself */
	public Camera setVPSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	/** setter for distance from camera to view plane
	 * @param distance - a distance from camera to view plane
	 * @return the camera itself */
	public Camera setVPDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/** sets imageWriter value
	 * @param imageWriter - new imageWriter value
	 * @return the camera itself */
	public Camera setImageWriter(ImageWriter imageWriter){
		this.imageWriter = imageWriter;
		return this;
	}
	
	/** sets rayTracer value
	 * @param rayTracer - new rayTracer value
	 * @return the camera itself */
	public Camera setRayTracer(RayTracerBase rayTracer){
		this.rayTracer = rayTracer;
		return this;
	}
	
	/** returns color of pixel in current tracing ray
	 * @param nX the x resolution
     * @param nY the y resolution
	 * @param j  - the pixel's index in the column
	 * @param i  - the pixel's index in the row
	 * @return */
	private void castRay(int nX, int nY, int j, int i) {
		Color rayColor;
		if (antiAliasingFactor == 1) {
			Ray ray = this.constructRay(nX, nY, j, i);
			rayColor = rayTracer.traceRay(ray);
			imageWriter.writePixel(j, i, rayColor);
		} 
		else {
			List<Ray> rays = this.constructRays(nX, nY, j, i);
			rayColor = rayTracer.traceRays(rays);
		}
		
		imageWriter.writePixel(j, i, rayColor);
		pixelManager.pixelDone();
	}
	
	/** function that calculates the pixels location
	 * @param nX - the size of width
	 * @param nY - the size of height
	 * @param j  - the index in the column
	 * @param i  - the index in the row
	 * @return the point */
	private Point findPixelLocation(int nX, int nY, int j, int i) {
		// ratio
		var rY = height / nY;
		var rX = width / nX;
		// pixel(i,j) center
	 	var yI = (i - (nY - 1) / 2.0) * rY;
		var xJ = (j - (nX - 1) / 2.0) * rX;
		// image center
		Point pIJ = p0.add(vTo.scale(distance));
			
		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(-yI));
		return pIJ;
	}
	
	/** The function builds a ray through a given pixel (j,i) within the grid of nX and nY 
	 * @param nX - the size of width
	 * @param nY - the size of height
	 * @param j  - the index in the column
	 * @param i  - the index in the row
	 * @return ray that passes in given pixel in the grid */
	public Ray constructRay(int nX, int nY, int j, int i) {	
		Point pIJ = findPixelLocation(nX, nY, j, i);
		Vector vIJ = pIJ.subtract(p0);
		return new Ray(p0, vIJ);
	}
	
	/** function that returns the rays from the camera to the point
     * @param nX - the size of width
	 * @param nY - the size of height
	 * @param j  - the index in the column
	 * @param i  - the index in the row
     * @return the rays */
    public List<Ray> constructRays(int nX, int nY, int j, int i) {
        List<Ray> rays = new LinkedList<>();
        Point centralPixel = findPixelLocation(nX, nY, j, i);
        double rY = height / nY / antiAliasingFactor; //height distance between the rays
        double rX = width / nX / antiAliasingFactor; //width distance between the rays
        double x, y;
        Point pIJ;
        for (int rowNumber = 0; rowNumber < antiAliasingFactor; rowNumber++) {
            for (int colNumber = 0; colNumber < antiAliasingFactor; colNumber++) {
                y = -(rowNumber - (antiAliasingFactor - 1d) / 2) * rY;
                x = (colNumber - (antiAliasingFactor - 1d) / 2) * rX;
                pIJ = centralPixel;
                if (y != 0) 
                	pIJ = pIJ.add(vUp.scale(y));
                if (x != 0) 
                	pIJ = pIJ.add(vRight.scale(x));
                rays.add(new Ray(p0, pIJ.subtract(p0)));
            }
        }
        return rays;
    }
    
	/** The function transfers beams from camera to pixel, tracks the beam
	 *  and receives the pixel color from the point of intersection.
	 * @throws MissingResourceException if one of the fields is empty
	 * @return the camera itself */
	public Camera renderImage() {
		if (p0 == null || vTo == null || vUp == null || vRight == null || distance == 0 || height == 0 || width == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
		final int nX = imageWriter.getNx();
        final int nY = imageWriter.getNy();
        
        //print time interval in seconds, 0 if printing is not required
        pixelManager = new PixelManager(nY, nX, printInterval);
        int threadsCount =Runtime.getRuntime().availableProcessors();
        if(threadsCount==0){
        	for (int i = 0; i < nX; i++)
        		for (int j = 0; j < nY; j++) {
        			castRay(nX, nY, j, i);
			}
        }
        else {
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    PixelManager.Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null){
                    	castRay(nX, nY, pixel.col(), pixel.row());
                    }
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try {
                for (var thread : threads) thread.join();
            }
            catch (InterruptedException ignore) {}
        }
        return this;
	}	
	
	
	/** The function transfers beams from camera to pixel, tracks the beam
	 *  and receives the pixel color from the point of intersection.
	 * @throws MissingResourceException if one of the fields is empty
	 * @return the camera itself */
	/*public Camera renderImageB() {
		if (p0 == null || vTo == null || vUp == null || vRight == null || distance == 0 || height == 0 || width == 0 || imageWriter == null || rayTracer == null)
            throw new MissingResourceException("", "", "Camera is not initialized");
		int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        Color rayColor;
		for (int i = 0; i < nX; i++)
			for (int j = 0; j < nY; j++) {
				rayColor = castRay(nX, nY, j, i);
				imageWriter.writePixel(j, i, rayColor);
			}
		return this;
	}*/
	
	
	/** A function that creates a grid of lines
	 * @param interval int value
	 * @param color Color value */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all fields", "ImageWriter", "imageWriter");
		
		for (int i = 0; i < imageWriter.getNx(); i++) {
			for (int j = 0; j < imageWriter.getNy(); j++) {
				if(i % interval == 0 || j % interval == 0)
					imageWriter.writePixel(i, j, color); 
			}
		}
	}
	
	/** A function that finally creates the image.
	 * This function delegates the function of a class imageWriter */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("this function must have values in all fields", "ImageWriter", "imageWriter");
		
		imageWriter.writeToImage();
		}  
	}