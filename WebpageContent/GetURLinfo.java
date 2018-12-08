import java.net.*;
import java.io.*;
import java.util.*;

public class GetURLinfo {
    public static void printURLinfo(URLConnection uc, PrintWriter write) throws IOException {
         // Display the URL address, and information about it.
         write.println(uc.getURL().toExternalForm() + ":");
         write.println("  Content Type: " + uc.getContentType());
         write.println("  Content Length: " + uc.getContentLength());
         write.println("  Last Modified: " + new Date(uc.getLastModified()));
         write.println("  Expiration: " + uc.getExpiration());
         write.println("  Content Encoding: " + uc.getContentEncoding());
    } // printURLinfo
    
    // Create a URL from the specified address, open a connection to it,
    // and then display information about the URL.
    //pass in the printwriter to write the urlinfo to outputfile
    public static void main(String name, PrintWriter write) 
         throws MalformedURLException, IOException    {
    URL url = new URL(name);
	URLConnection connection = url.openConnection();
	printURLinfo(connection, write);
    } // main
} // GetURLInfo
