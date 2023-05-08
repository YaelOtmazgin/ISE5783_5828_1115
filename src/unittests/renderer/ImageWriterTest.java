package unittests.renderer;

import org.junit.Test;
import java.awt.Color;
import renderer.ImageWriter;

/** An ImageWriter test module that contains an initial image build
 * @author Menuha and Yael */
public class ImageWriterTest {

	/** Test method for
	 * {@link renderer.ImageWriter#ImageWriter(java.lang.String, int, int)}. */
	@Test
	public void testImageWriter() {
	    var writer = new ImageWriter("firstImage", 800, 500);
	    for (int j = 0; j < 800; j++) {
			for (int i = 0; i < 500; i++) {
				if (j % 50 == 0 || i % 50 == 0 || j == 799 || i == 499 )
					writer.writePixel(j, i, new primitives.Color(Color.red));
				else 
					writer.writePixel(j, i, new primitives.Color(Color.yellow));
			}
		}
	    writer.writeToImage();
	}
}