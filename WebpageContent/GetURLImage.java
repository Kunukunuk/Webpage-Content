
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class GetURLImage {

	URL url = null;
	File outputImageFile = null;
	public static BufferedImage image = null;

	public static void fetchImageFromURL (URL url) {
		try {
		// Read from a URL
		image = ImageIO.read(url);
		} catch (IOException e) {
		} // catch

	} // fetchImageFromURL

    // Create a URL from the specified address, open a connection to it,
    // and then display information about the URL.
	//have url name, what kind of file is pass and the output file
    public static void main(String name, String last4, String jpeg, String output) 
         throws MalformedURLException, IOException    {
    //get the url
	URL url = new URL(name);
	File outputImageFile = new File(output);
	//create a new file to store the content of the url
	outputImageFile.createNewFile();
	//gets the image from url
	fetchImageFromURL(url);
	//if it ends with .jpg write it to .jpg
	if(last4.equals(".jpg")){
		ImageIO.write(image, "jpg", outputImageFile);
	}
	//if it ends with .jpeg write it to .jpeg
	else if( jpeg.equals(".jpeg")){
		ImageIO.write(image, "jpeg", outputImageFile);
	}
	//if it ends with .gif
	else if(last4.equals(".gif")){
		//open input and output stream
		InputStream reads = url.openStream();
		OutputStream writes = new FileOutputStream(outputImageFile);
		//store the animation
		byte[] b = new byte[1024];
		int length;
		//writes the animation to a file
		while( (length = reads.read(b)) != -1){
			writes.write(b,0, length);
		}
		reads.close();
		writes.close();
	}
    } // main

} // GetURLImage
