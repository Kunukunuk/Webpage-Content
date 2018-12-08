import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class WebpageWA {

         private static String webpage = null;
         public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.9.2) Gecko/20100115 Firefox/3.6";
         private static PrintWriter pw;
         public static InputStream getURLInputStream(String sURL) throws Exception {
               URLConnection oConnection = (new URL(sURL)).openConnection();
               oConnection.setRequestProperty("User-Agent", USER_AGENT);
               return oConnection.getInputStream();
         } // getURLInputStream

         public static BufferedReader read(String url) throws Exception {
               InputStream content = (InputStream)getURLInputStream(url);
               return new BufferedReader (new InputStreamReader(content));
         } // read

       public static void main (String name, File outfile, PrintWriter write, String last4) throws Exception{
    	   //if its a pdf url
    	   //create a url link and transfer the bits direct from website into a file
    	   if(last4.equals(".pdf")){
    	      URL site = new URL(name);
              ReadableByteChannel tran = Channels.newChannel(site.openStream());
              FileOutputStream output = new FileOutputStream(outfile);
              output.getChannel().transferFrom(tran, 0, Long.MAX_VALUE);
    	   	}
    	   //if its not a pdf which means its a txt, html, or htm file
    	   else{
    		   //get the name of the file
              webpage = name;
              //open the new output file
              pw = new PrintWriter(outfile);
              //write the content to the new outputfile
              pw.println("Contents of the following URL: "+webpage+"\n");
              //open the url
              BufferedReader reader = read(name);
              //read in the content
              String line = reader.readLine();
              int count = 1;
              //write all the content to a new outputfile
              while (line != null) {
                     pw.println(line);
                     line = reader.readLine();
                     count++;
              } // while
              //write the number of lines to output file
              write.println("Number of lines: " + count);
              pw.close();
    	   	}

       } // main
} // WebpageReaderWithAgent